package com.energybox.backendcodingchallenge.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Node("Gateway")
@Data
@Builder
public class Gateway implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generatorClass = UUIDStringGenerator.class)
	private String identifier;
	
	private String name;
	
	@Default
	@Relationship(type = "CONNECTED_TO", direction = Direction.INCOMING)
	private List<Sensor> sensors = new ArrayList<>();
}
