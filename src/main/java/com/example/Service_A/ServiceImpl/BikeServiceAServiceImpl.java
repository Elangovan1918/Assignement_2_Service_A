package com.example.Service_A.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.Service_A.ServiceInter.BikeServiceAServiceInterface;
import com.example.Service_A.dto.BikeDtoA;
import com.example.Service_A.entity.BikeEntityA;
import com.example.Service_A.repository.BikeRespositoryServiceA;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BikeServiceAServiceImpl implements BikeServiceAServiceInterface {
	
	
	@Autowired
	BikeRespositoryServiceA bikeRespositoryServiceA;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public BikeDtoA saveBikeA(BikeDtoA bikeDtoA) {	
		
        log.info("Starting bike save operation for: {}", bikeDtoA);

		bikeDtoA =  convertEntityToDto(bikeRespositoryServiceA.save(convertDtoToEntity(bikeDtoA)));
		
        log.info("Bike entity saved to database: {}", bikeDtoA);

		try {
			String Key=String.valueOf(bikeDtoA.getBikeNumber());
			
			kafkaTemplate.send("BikeService",Key,objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bikeDtoA));
			
            log.info("Published bike to Kafka with key {}: {}", Key, bikeDtoA);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
        log.info("Completed bike save operation for: {}", bikeDtoA);

		return bikeDtoA;
	}
	
	public BikeDtoA convertEntityToDto(BikeEntityA bikeEntity) {
		return modelMapper.map(bikeEntity,BikeDtoA.class);
	}
	public BikeEntityA convertDtoToEntity(BikeDtoA bikeDto) {
		return modelMapper.map(bikeDto, BikeEntityA.class);
	}


}
