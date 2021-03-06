package pl.jrobertgardzinski.servicetags.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.jrobertgardzinski.servicetags.model.Tag;
import pl.jrobertgardzinski.servicetags.repository.JdbcTagRepository;

@RestController
public class TagController {
	
	@Autowired
	private JdbcTagRepository jdbcTagRepository;

	@GetMapping() 
	public List<Tag> getAllTags(){
		return jdbcTagRepository.findAll();
	}
	
	@GetMapping("/image/{id}") 
	public List<String> getByImageId(@PathVariable("id") Integer id){
		return jdbcTagRepository.findByImageId(id);
	}
	
	@PostMapping(value = "images", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<String>> getByImageIds(@RequestBody List<Integer> ids){
		return jdbcTagRepository.findByImageIds(ids);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postImageIdToTag(@RequestBody Tag tag) {
		jdbcTagRepository.appendImageIdToTag(tag.getImageId(), tag.getTag());
	}

	@DeleteMapping() 
	public void deleteImageIdFromTag(@RequestBody Tag tag) {
		jdbcTagRepository.deleteImageIdFromTag(tag.getImageId(), tag.getTag());
	}
	
	@DeleteMapping("image/{id}") 
	public void deleteTagsForId(@PathVariable Integer id) {
		jdbcTagRepository.deleteTagsForImageWithId(id);
	}
}
