package pl.jrobertgardzinski.reactive_images;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "images")
public class Image {
    @Id
    private String id;
     
    private String title;
         
    private Binary image;

	public Image(String title) {
		super();
		this.title = title;
	}
    
    
}