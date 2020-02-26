package com.mastercard.codechallenge.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class to represent connection graph and provides basic search functionality
 */
public class ConnectionGraph {

	private HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
	
	/**
	 * Adds bidirectional connection between supplied cities
	 * 
	 * @param cityOne
	 * @param cityTwo
	 */
	public void addConnection(String cityOne, String cityTwo) {			
		addUnidirectionalConnection(cityOne, cityTwo);
		addUnidirectionalConnection(cityTwo, cityOne);
	}
	
	/**
	 * Adds unidirectional connection in graph
	 */
	private void addUnidirectionalConnection(String cityFrom, String cityTo) {
		List<String> cityFromConnections = this.graph.get(cityFrom);

		if (cityFromConnections == null) {
			cityFromConnections = new LinkedList<String>();
			this.graph.put(cityFrom, cityFromConnections);
		}
		
		cityFromConnections.add(0,cityTo);			
	}
	
	/**
	 * Resets the connection graph by loading data from supplied file
	 * @param filepath Filepath to load data from
	 * @throws IOException
	 */
	public void prepareGraphFromFile(File file) throws IOException{
		//Reset the graph and load it from file
		this.graph = new HashMap<String, List<String>>();
		
		try(Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			
			stream.forEach(new Consumer<String>(){
				public void accept(String line) {
					String[] tokens = line.split("\\s*,\\s*");
					if (tokens.length != 2)
						throw new RuntimeException("Incorrect entry");
						
					addConnection(tokens[0], tokens[1]);
				}
			});
		}
		
	}	
	
	/**
	 * checks if the two supplied cities are connected
	 * 
	 * @param cityOne
	 * @param cityTwo
	 * @return boolean True if connected, false otherwise
	 */
	public boolean isConnected(String cityOne, String cityTwo) {
		return new GraphSearch(this.graph).isConnected(cityOne, cityTwo);
	}
		
}
