package com.energybox.backendcodingchallenge.domain;

import java.io.Serializable;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.energybox.backendcodingchallenge.enums.SensorType;

import lombok.Builder;
import lombok.Data;

@Node("Sensor")
@Data
@Builder
public class Sensor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generatorClass = UUIDStringGenerator.class)
	private String identifier;
	
	private SensorType sensorType;
	
}
