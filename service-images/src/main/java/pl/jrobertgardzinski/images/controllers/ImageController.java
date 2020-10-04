package pl.jrobertgardzinski.images.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import pl.jrobertgardzinski.images.model.Image;
import pl.jrobertgardzinski.images.repository.ImageRepository;

@RestController
public class ImageController {
	@Autowired
	ImageRepository imageRepository;
	
	@PostMapping("/images/add")
	public String addImage(@RequestParam("title") String title, 
	  @RequestParam("image") MultipartFile image) 
	  throws IOException {
		Image newImage = new Image(title);
		
		newImage.setImage(image.getBytes());
		Image addedImage = imageRepository.save(newImage);
	    return "Added new image with ID: " + addedImage.getId();
	}

	@GetMapping("/images/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
	    Image image = imageRepository.findById(id).get();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(image.getImage().length);
	    	    
	    return ResponseEntity.ok().headers(headers).body(image.getImage());
	}
	
	@GetMapping("/images")
	public ResponseEntity<List<Image>> getAllImages() throws IOException {
	    List<Image> images = Lists.newArrayList(imageRepository.findAll());
	    	    
	    return ResponseEntity.ok().body(images);
	}
	
	@DeleteMapping("/images/{id}")
	public void deleteImage(@PathVariable Integer id) {
		this.imageRepository.deleteById(id);
	}
	
	@PatchMapping("/images/{id}")
	public void patchImage(@PathVariable Integer id, @RequestBody String title) {
		this.imageRepository.findById(id).ifPresent(image -> {
			image.setTitle(title);
			this.imageRepository.save(image);
		});		
	}
	
}
