package com.energybox.backendcodingchallenge.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
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
import com.energybox.backendcodingchallenge.repos.GatewayRepository;

public class GatewayServiceTest {

	@InjectMocks
	private GatewayService gatewayService;
	
	@Mock
	private GatewayRepository gatewayRepository;

	@Mock
	private SensorService sensorService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreateGateway() {
		Gateway gateway = Mockito.mock(Gateway.class);
		doReturn(gateway).when(gatewayRepository).save(gateway);
		Assertions.assertEquals(gateway, gatewayService.createGateway(gateway));
		Mockito.verify(gatewayRepository).save(gateway);
	}
	
	@Test
	public void testGetAllGateways() {
		List<Gateway> gateways = List.of(Mockito.mock(Gateway.class));
		doReturn(gateways).when(gatewayRepository).findAll();
		Assertions.assertEquals(gateways, gatewayService.getAllGateways());
		Mockito.verify(gatewayRepository).findAll();
		
	}
	
	@Test
	public void testAssgnASensor() {
		String gatewayId = UUID.randomUUID().toString();
		String sensorId = UUID.randomUUID().toString();
		Optional<Sensor> sensor = Optional.of(Mockito.mock(Sensor.class));
		Gateway gatewayObj = Mockito.mock(Gateway.class);
		Optional<Gateway> gateway = Optional.of(gatewayObj);
		doReturn(sensor).when(sensorService).getSensor(sensorId);
		doReturn(gateway).when(gatewayRepository).findById(gatewayId);
		doReturn(new ArrayList<>()).when(gatewayObj).getSensors();
		doReturn(gatewayObj).when(gatewayRepository).save(gatewayObj);
		Assertions.assertEquals(gatewayObj, gatewayService.assignASensor(gatewayId, sensorId));
		Mockito.verify(gatewayRepository).save(gatewayObj);
	}
	
	@Test
	public void testGetGatewayBySensorType() {
		List<Gateway> gateways = List.of(Mockito.mock(Gateway.class));
		doReturn(gateways).when(gatewayRepository).findGatewayBySensorType(SensorType.TEMPARATURE.toString());
		Assertions.assertEquals(gateways, gatewayService.getGatewayBySensorType(SensorType.TEMPARATURE));
		Mockito.verify(gatewayRepository).findGatewayBySensorType(SensorType.TEMPARATURE.toString());
	}
	
}
