package pl.jrobertgardzinski.reactive_images;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface ImageRepository extends ReactiveCrudRepository<Image, String> {
}