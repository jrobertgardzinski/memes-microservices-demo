package pl.jrobertgardzinski.images;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
 /*
    @Autowired
    private ImageRepository photoRepo;
 
    public String addImage(String title, MultipartFile file) throws IOException { 
        Image image = new Image(title); 
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        image = photoRepo.insert(image); return image.getId(); 
    }
 
    public Image getImage(String id) { 
        return photoRepo.findById(id).get(); 
    }
    */
}