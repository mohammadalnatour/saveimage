package com.example.demo;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/image")

public class ImageController {
	@Autowired
	private ImageService imageService;

	@PostMapping("/file")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			System.out.println(file);
			String uploadImage = imageService.uploadImage(file);
			return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
		}
		return null;
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
		byte[] imageData = imageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(IMAGE_PNG_VALUE)).body(imageData);
	}

}
