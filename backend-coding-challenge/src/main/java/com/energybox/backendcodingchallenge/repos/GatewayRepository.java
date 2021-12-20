package com.energybox.backendcodingchallenge.repos;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.energybox.backendcodingchallenge.domain.Gateway;

public interface GatewayRepository extends Neo4jRepository<Gateway, String> {

	@Query("MATCH(g:Gateway)<-[]-(s:Sensor) where s.sensorType=$sensorType return g")
	List<Gateway> findGatewayBySensorType(String sensorType);
}
