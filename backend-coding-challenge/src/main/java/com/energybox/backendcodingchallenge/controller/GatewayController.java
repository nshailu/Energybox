package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gateways")
public class GatewayController {

	private final GatewayService service;

	public GatewayController(GatewayService service) {
		this.service = service;
	}

	@ApiOperation(value = "create a gateway", response = Gateway.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gateway> create(@RequestBody Gateway gateway) throws IOException, InterruptedException {
		return new ResponseEntity<>(service.createGateway(gateway), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Gateway>> getAllGateways() {
		return new ResponseEntity<>(service.getAllGateways(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Gateway> assignASensor(@RequestParam("gatewayId")String gatewayId, 
			@RequestParam("sensorId") String sensorId) {
		return new ResponseEntity<Gateway>(service.assignASensor(gatewayId, sensorId), HttpStatus.OK);
	}
	
	@GetMapping("/bySensorType")
	public ResponseEntity<List<Gateway>> getGateway(@RequestParam("sensorType") SensorType sensorType) {
		return new ResponseEntity<List<Gateway>>(service.getGatewayBySensorType(sensorType), HttpStatus.OK);
	}
}
