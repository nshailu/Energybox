package com.energybox.backendcodingchallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.repos.GatewayRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GatewayService {

	@Autowired
	private GatewayRepository gatewayRepository;

	@Autowired
	private SensorService sensorService;

	public Gateway createGateway(@NonNull Gateway gateway) {
		return gatewayRepository.save(gateway);
	}

	public List<Gateway> getAllGateways() {
		return gatewayRepository.findAll();
	}

	public Gateway assignASensor(String gatewayId, String sensorId) {
		Optional<Sensor> sensor = sensorService.getSensor(sensorId);
		Optional<Gateway> gateway = gatewayRepository.findById(gatewayId);
		if (sensor.isPresent() && gateway.isPresent()) {
			Gateway gatewayObj = gateway.get();
			gatewayObj.getSensors().add(sensor.get());
			return gatewayRepository.save(gatewayObj);
		} else {
			log.error(sensor.isPresent() ? "Gateway with id {} not found, so could not assign sensor id {}"
					: "Sensor with id {} not found, so could not assign to gateway id {}", gatewayId, sensorId);
			return null;
		}
	}
	
	public List<Gateway> getGatewayBySensorType(@NonNull SensorType sensorType) {
		return gatewayRepository.findGatewayBySensorType(sensorType.name());
	}
}
