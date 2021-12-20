package com.energybox.backendcodingchallenge.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.service.SensorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sensors")
public class SensorController {

	private final SensorService service;

	public SensorController(SensorService service) {
		this.service = service;
	}

	@ApiOperation(value = "create a sensor", response = Gateway.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sensor> create(@RequestBody Sensor sensor) throws IOException, InterruptedException {
		return new ResponseEntity<>(service.createSensor(sensor), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Sensor>> getAllSensors() {
		return new ResponseEntity<List<Sensor>>(service.getAllSensors(), HttpStatus.OK);
	}
	
	@GetMapping("/byGatewayId")
	public ResponseEntity<List<Sensor>> getSensors(@RequestParam("gatewayId") String gatewayId) {
		return new ResponseEntity<List<Sensor>>(service.getAllSensors(gatewayId), HttpStatus.OK);
	}

	@GetMapping("/bySensorType")
	public ResponseEntity<List<Sensor>> getSensors(@RequestParam("sensorType") SensorType sensorType) {
		return new ResponseEntity<List<Sensor>>(service.getSensor(sensorType), HttpStatus.OK);
	}
}
