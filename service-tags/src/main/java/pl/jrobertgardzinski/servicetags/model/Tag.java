package pl.jrobertgardzinski.servicetags.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {	
	@Id
	@Column(unique=true, nullable=false)
    private String tag;
    
    @Column(name="image_ids_array")
	private Integer[] imageIds;
    
    @Transient
    Integer imageId;

    public Tag withTag(String tag) {
    	this.setTag(tag);
    	return this;
    }
    
    public Tag withImageIds(Integer[] imageIds) {
    	this.setImageIds(imageIds);
    	return this;
    }
}