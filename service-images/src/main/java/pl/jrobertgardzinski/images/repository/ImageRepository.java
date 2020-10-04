package pl.jrobertgardzinski.images.repository;

import org.springframework.data.repository.CrudRepository;

import pl.jrobertgardzinski.images.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}