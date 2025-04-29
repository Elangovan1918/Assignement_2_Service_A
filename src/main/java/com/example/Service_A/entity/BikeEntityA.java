package com.example.Service_A.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BikeEntityA {

	
	@Id
	private int bikeNumber;
	private String bikeName;
	private String bikeModel;
	private String bikeType;

}
