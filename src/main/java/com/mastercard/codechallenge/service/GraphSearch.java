package com.mastercard.codechallenge.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * This class represent graph search algorithm
 * It can be enriched to make use of strategy to replace best suitable algorithm
 *  
 */
public class GraphSearch {
	private final Collection<String> visited = new HashSet<String>();
	private HashMap<String, List<String>> graph;
	
	public GraphSearch(HashMap<String, List<String>> graph) {
		this.graph = graph;			
	}
	
	/*
	 * DFS vs BFS would depend on the nature of the data.
	 * This could also be converted to a concurrent search
	 * TODO: What if it has a single path via thousands of cities, recursion might cause stack overflow, will worry about this later.
	 */
	public boolean isConnected(String cityOne, String cityTwo) {
		List<String> cityOneConnections = this.graph.get(cityOne);
		
		if (cityOneConnections != null && this.graph.get(cityTwo) != null) {
			for (String city : cityOneConnections) {
				if (city.equals(cityTwo))
					return true;

				if (this.visited.add(city)) {
					if (this.isConnected(city, cityTwo))
						return true;
				}
			}
		}
		
		return false;
	}

}
