package pl.jrobertgardzinski.reactive_images;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private ImageRepository imageRepository;

    @GetMapping("/{id}")
    private Mono<Image> getEmployeeById(@PathVariable String id) {
        return imageRepository.findById(id);
    }

    @GetMapping
    private Flux<Image> getAllEmployees(@RequestParam long size, @RequestParam long page) {
		return imageRepository.findAll()
					.skip(page * size)
					.take(size);
    }
    
    @GetMapping("/number-of-pages")
    private Mono<Long> getNumberOfPages(@RequestParam long size) {
		return imageRepository.findAll().count().map(sum -> sum / size + ((sum % size == 0) ? 0 : 1));
    }

}
