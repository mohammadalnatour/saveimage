package com.example.demo;

import java.sql.Types;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Images")
@Getter
@Setter
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String type;
	@Lob
	@JdbcTypeCode(Types.BLOB)
	private byte[] imageData;
	
	
	public Image(String originalFilename, String contentType, byte[] compressImage) {
		this.name = originalFilename;
		this.type = contentType;
		this.imageData = compressImage;
	}


	public byte[] getImageData() {
		
		return this.imageData;
	}


	public Object getId() {
		
		return this.getId();
	}
}
