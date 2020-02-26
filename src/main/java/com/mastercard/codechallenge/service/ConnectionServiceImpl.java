package com.mastercard.codechallenge.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	private ConnectionGraph graph = new ConnectionGraph();
	

	@PostConstruct
	public void loadFile() throws IOException {
		this.graph.prepareGraphFromFile(ResourceUtils.getFile("classpath:city.txt"));
	}

	@Override
	public String isConnected(String origin, String destination) {
		// TODO Auto-generated method stub
		return graph.isConnected(origin, destination)? "yes":"no";
	}

}
