package com.mastercard.codechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.codechallenge.service.ConnectionService;

/** 
 * ideally there would be interface and controller impl, however just avoiding it for the test
 * 
 *
 */
@RestController
public class ConnectionController {

	@Autowired(required=true)
	private ConnectionService connectionService;
	
	@GetMapping("/connected")
	public String isConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		return connectionService.isConnected(origin, destination);
	}
}
