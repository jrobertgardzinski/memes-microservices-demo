package pl.jrobertgardzinski.images.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

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

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

	public Image(String title) {
		super();
		this.title = title;
	}
    
    
}