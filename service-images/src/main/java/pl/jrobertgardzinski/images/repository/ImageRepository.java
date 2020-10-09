package pl.jrobertgardzinski.images.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pl.jrobertgardzinski.images.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer>, PagingAndSortingRepository<Image, Integer> {

    Page<Image> findAllByOrderByIdDesc(Pageable pageable);
}