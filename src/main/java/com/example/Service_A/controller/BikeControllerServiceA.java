package com.example.Service_A.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service_A.ServiceInter.BikeServiceAServiceInterface;
import com.example.Service_A.dto.BikeDtoA;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BikeControllerServiceA {
	
	@Autowired
	BikeServiceAServiceInterface bikeServiceAServiceInterface;
	
	
	@PostMapping("/saveBikeA")
	public BikeDtoA saveBikeA(@RequestBody BikeDtoA bikeDtoA) {
		
        log.info("[SaveBikeA Method] - in Service_AController with BikeDto - {}", bikeDtoA);
        
		BikeDtoA savedBikeDtoA=bikeServiceAServiceInterface.saveBikeA(bikeDtoA);
		
		log.info("Bike saved successfully: {}",savedBikeDtoA );

		
        return bikeDtoA;
	}

}
