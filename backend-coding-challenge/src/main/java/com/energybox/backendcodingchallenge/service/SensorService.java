package com.energybox.backendcodingchallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.repos.SensorRepository;

@Service
public class SensorService {

	@Autowired
	private SensorRepository sensorRepository;
	
	public Sensor createSensor(Sensor sensor) {
		return sensorRepository.save(sensor);
	}
	
	public List<Sensor> getAllSensors() {
		return sensorRepository.findAll();
	}
	
	public List<Sensor> getAllSensors(String gatewayId) {
		return sensorRepository.getAllSensors(gatewayId);
	}

	public Optional<Sensor> getSensor(String sensorId) {
		return sensorRepository.findById(sensorId);
	}
	
	public List<Sensor> getSensor(SensorType sensorType) {
		return sensorRepository.findBySensorType(sensorType.toString());
	}
}
