package com.example.demo;

import java.io.IOException;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;

	public String uploadImage(MultipartFile imageFile) throws IOException {
		// Image imageToSave =
		// Image.builder().name(imageFile.getOriginalFilename()).type(imageFile.getContentType())
		// .imageData(ImageUtils.compressImage(imageFile.getBytes())).build();

		Image imageToSave = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
				ImageUtils.compressImage(imageFile.getBytes()));
		this.imageRepository.save(imageToSave);
		return "file uploaded successfully : " + imageFile.getOriginalFilename();
	}
	
	
	public byte[] downloadImage(String imageName) {
        Optional<Image> dbImage = imageRepository.findByName(imageName);
        
      
        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID",  image.getId())
                        .addContextValue("Image name", imageName);
            }
        }).orElse(null);
    }
	
	

}
