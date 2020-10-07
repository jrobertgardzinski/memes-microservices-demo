package pl.jrobertgardzinski.servicetags.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.jrobertgardzinski.servicetags.model.Tag;

@Repository
public class JdbcTagRepository {
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcTagRepository(JdbcTemplate jdbc) {
		
		this.jdbc = jdbc;
	}

	private Tag mapRowToTag(ResultSet rs, int rowNum) throws SQLException {
		
		return new Tag()
				.withTag(rs.getString("tag"))
				.withImageIds( (Integer[])rs.getArray("image_ids_array").getArray() );
	}
	
	private String mapRowToTagWithoutImageId(ResultSet rs, int rowNum) throws SQLException {
		
		return rs.getString("tag");
	}
	
	public List<Tag> findAll() {
		
		return jdbc.query("SELECT * FROM tags",
				this::mapRowToTag);
	}

	public List<String> findByImageId(Integer id) {
		
		return jdbc.query(String.format("SELECT tag FROM tags WHERE image_ids_array && '{%d}'", id), 
				this::mapRowToTagWithoutImageId);
	}
	
	public Map<String, List<String>> findByImageIds(List<Integer> ids) {
		
		Map<String, List<String>> result = new HashMap();	
		List<Tag> allTags = this.findAll();
		ids.forEach(id -> {
			List<String> tags = allTags.stream().filter(tag -> Arrays.asList(tag.getImageIds()).stream().anyMatch(currentId -> currentId == id)).map(Tag::getTag).collect(Collectors.toList());
			result.put(id.toString(), tags);
		});
		return result;
	}
	
	public void appendImageIdToTag(Integer id, String tag) {
		
		jdbc.update(
			String.format(
				"INSERT INTO tags (tag, image_ids_array) \r\n" + 
				"VALUES ('%s', '{%d}') \r\n" + 
				"ON CONFLICT (tag) \r\n" + 
				"DO UPDATE SET image_ids_array = array_append(tags.image_ids_array, '%d') WHERE tags.tag = '%s';", 
				tag, id, id, tag)
			);
	}
	
	public void deleteImageIdFromTag(Integer id, String tag) {

		try {
			jdbc.update(
				String.format( 
					"UPDATE tags SET image_ids_array = array_remove(image_ids_array, '%d') WHERE tag = '%s';", 
					id, tag)
				);
		}
		catch (DataAccessException ex) {
			jdbc.update(
				String.format(
					"DELETE FROM tags WHERE tag = '%s';", 
					tag)
				);
		}
	}
}
