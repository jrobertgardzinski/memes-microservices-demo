package pl.jrobertgardzinski.images.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import pl.jrobertgardzinski.images.model.Image;
import pl.jrobertgardzinski.images.repository.ImageRepository;

@RestController
public class ImageController {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;
		if (randomNum == 3)
			sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@PostMapping()
	public String addImage(@RequestParam("title") String title, 
	  @RequestParam("image") MultipartFile image) 
	  throws IOException {
		Image newImage = new Image(title);
		
		newImage.setImage(image.getBytes());
		Image addedImage = imageRepository.save(newImage);
	    return "Added new image with ID: " + addedImage.getId();
	}

	@HystrixCommand(fallbackMethod = "buildFallbackImage")
	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
	    randomlyRunLong();
	    Image image = imageRepository.findById(id).get();
	    Stream.of(image.getImage()).forEach(bite -> { 
	    	System.out.print(bite);
	    	System.out.print(" ");
	    });
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(image.getImage().length);
	    	    
	    return ResponseEntity.ok().headers(headers).body(image.getImage());
	}
	private ResponseEntity<byte[]> buildFallbackImage(@PathVariable Integer id) throws IOException {
		byte[] mockImageData = {-1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 2, 0, 0, 1, 0, 1, 0, 0, -1, -37, 0, 67, 0, 8, 6, 6, 7, 6, 5, 8, 7, 7, 7, 9, 9, 8, 10, 12, 20, 13, 12, 11, 11, 12, 25, 18, 19, 15, 20, 29, 26, 31, 30, 29, 26, 28, 28, 32, 36, 46, 39, 32, 34, 44, 35, 28, 28, 40, 55, 41, 44, 48, 49, 52, 52, 52, 31, 39, 57, 61, 56, 50, 60, 46, 51, 52, 50, -1, -37, 0, 67, 1, 9, 9, 9, 12, 11, 12, 24, 13, 13, 24, 50, 33, 28, 33, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, -1, -64, 0, 17, 8, 0, 8, 0, 25, 3, 1, 34, 0, 2, 17, 1, 3, 17, 1, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, -13, 127, 11, 95, 105, -111, 120, -114, -34, 91, -119, 33, -5, 44, 118, 83, 6, 6, -44, 75, -74, 67, 30, 20, -108, 97, -122, 59, -10, -32, 19, -116, -11, 32, 87, -85, 120, 92, -37, -104, 109, -115, -94, -86, 91, 27, -105, -14, -107, 25, -104, 5, -13, 91, 0, 22, 1, -120, -57, 114, 1, -94, -118, -26, -10, 17, 117, 20, -82, -61, 20, -3, -76, -109, -106, -102, -33, 67, -82, -65, -78, -41, 110, -20, 33, -2, -57, 105, -42, 120, -18, -124, -110, 24, -89, 8, 85, 68, 50, -128, 112, 72, 15, -119, 54, 17, 25, 59, 88, -128, 27, 11, -110, 61, 7, 6, -118, 43, -74, 117, 28, -83, -90, -58, 52, 105, -86, 112, 81, 71, -1, -39};
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(mockImageData.length);
		 
	    return ResponseEntity.ok().headers(headers).body(mockImageData);
	}
	
	@GetMapping()
	public ResponseEntity<Page<Image>> getAllImages(Pageable pageable) throws IOException {
	    
	    Page<Image> images =  imageRepository.findAll(pageable);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>('[' + images.stream().map(image -> image.getId().toString()).collect(Collectors.joining(",")) + ']' ,headers);	    
    	ResponseEntity<Map> restExchange = 
	    		restTemplate.exchange(
	    				"http://service-tags/images",
	    				HttpMethod.POST,
	    				entity,
	    				Map.class);
    	
    	Map<String, List<String>> restResult = restExchange.getBody();
    	images.forEach(image -> image.setTags(restResult.get(image.getId().toString())));
	    	    
	    return ResponseEntity.ok().body(images);
	}
	
	@DeleteMapping("{id}")
	public void deleteImage(@PathVariable Integer id) {		
		this.imageRepository.deleteById(id);		

		restTemplate.delete("http://service-tags/image/" + id.toString());
	}
	
	@PatchMapping("{id}")
	public void patchImage(@PathVariable Integer id, @RequestBody String title) {
		this.imageRepository.findById(id).ifPresent(image -> {
			image.setTitle(title);
			this.imageRepository.save(image);
		});		
	}
	
}
