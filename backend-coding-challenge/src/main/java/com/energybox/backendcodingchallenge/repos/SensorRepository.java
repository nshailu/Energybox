package com.energybox.backendcodingchallenge.repos;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.energybox.backendcodingchallenge.domain.Sensor;

public interface SensorRepository extends Neo4jRepository<Sensor, String> {

	@Query("MATCH(s:Sensor)-[]->(g:Gateway) where g.identifier=$gatewayId return s")
	List<Sensor> getAllSensors(String gatewayId);

	@Query("MATCH(s:Sensor) where s.sensorType=$sensorType return s")
	List<Sensor> findBySensorType(String sensorType);

}
