package pl.jrobertgardzinski.images.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
    private Integer id;
     
    private String title;
    
    @Transient
    private List<String> tags;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

	public Image(String title) {
		super();
		this.title = title;
	}
    
    public Image withId(Integer id) {
    	this.setId(id);
    	return this;
    }
    
    public Image withTitle(String title) {
    	this.setTitle(title);
    	return this;
    }
    
    public Image withTags(List<String> tags) {
    	this.setTags(tags);
    	return this;
    }
    
    public Image withImage(byte[] image) {
    	this.setImage(image);
    	return this;
    }
}