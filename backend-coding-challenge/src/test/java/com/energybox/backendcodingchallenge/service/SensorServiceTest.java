package com.energybox.backendcodingchallenge.service;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.repos.SensorRepository;

public class SensorServiceTest {

	@InjectMocks
	private SensorService sensorService;

	@Mock
	private SensorRepository sensorRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateSensor() {
		Sensor sensor = Mockito.mock(Sensor.class);
		doReturn(sensor).when(sensorRepository).save(sensor);
		Assertions.assertEquals(sensor, sensorService.createSensor(sensor));
		Mockito.verify(sensorRepository).save(sensor);
	}

	@Test
	public void testGetAllSensors() {
		List<Sensor> sensors = List.of(Mockito.mock(Sensor.class));
		doReturn(sensors).when(sensorRepository).findAll();
		Assertions.assertEquals(sensors, sensorService.getAllSensors());
		Mockito.verify(sensorRepository).findAll();
	}
	
	@Test
	public void testGetAllSensorsByGatewayId() {
		List<Sensor> sensors = List.of(Mockito.mock(Sensor.class));
		String gatewayId = UUID.randomUUID().toString();
		doReturn(sensors).when(sensorRepository).getAllSensors(gatewayId);
		Assertions.assertEquals(sensors, sensorService.getAllSensors(gatewayId));
		Mockito.verify(sensorRepository).getAllSensors(gatewayId);
	}

	@Test
	public void testGetSensor() {
		Optional<Sensor> sensor = Optional.of(Mockito.mock(Sensor.class));
		String sensorId = UUID.randomUUID().toString();
		doReturn(sensor).when(sensorRepository).findById(sensorId);
		Assertions.assertEquals(sensor, sensorService.getSensor(sensorId));
		Mockito.verify(sensorRepository).findById(sensorId);
	}
	
	@Test
	public void testGetSensorBySensorType() {
		List<Sensor> sensors = List.of(Mockito.mock(Sensor.class));
		doReturn(sensors).when(sensorRepository).findBySensorType(SensorType.TEMPARATURE.toString());
		Assertions.assertEquals(sensors, sensorService.getSensor(SensorType.TEMPARATURE));
		Mockito.verify(sensorRepository).findBySensorType(SensorType.TEMPARATURE.toString());
	}
}
