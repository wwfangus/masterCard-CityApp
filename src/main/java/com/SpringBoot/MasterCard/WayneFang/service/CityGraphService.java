package com.SpringBoot.MasterCard.WayneFang.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.SpringBoot.MasterCard.WayneFang.controller.CityConnRestController;

@Service
public class CityGraphService {

	private static final Logger logger = LoggerFactory.getLogger(CityGraphService.class);
	
	static String cityFile="city.txt"; // in /src/resource  
	HashMap<String, HashSet<String>> graphAjaMap;
	private static boolean initialized = false;
	
	public CityGraphService() {
		if( !initialized ) {
			graphAjaMap = new HashMap<>();
			populateGraph_fr_file();			
		}
		initialized = true;
	}
	
	public HashMap<String, HashSet<String>>  populateGraph_fr_file() {
		// Open this file.
	  logger.debug("--v-populateGraph_fr_file--v--");
      Resource resource = new ClassPathResource(cityFile);
      InputStream inputStream;
      try {
			inputStream = resource.getInputStream();
		
	    	      	   
	    	  BufferedReader reader = new BufferedReader(
	    	                      new InputStreamReader(inputStream, StandardCharsets.UTF_8));
	    	      
	    		reader.lines().forEach( line -> {
	    			String[] parts = line.split(", ");
	    			insert2Graph(parts[0], parts[1]);
	    			insert2Graph(parts[1], parts[0]);

	                }                
	              );
	  	} catch (IOException e1) {
			e1.printStackTrace();
		}		
		return graphAjaMap;     
	  }
	  
	  private boolean insert2Graph(String key, String adjCity) {
		  
		  	HashSet<String> adjCities = graphAjaMap.get( key );  
			if( adjCities == null ) {
				adjCities = new HashSet<>();
				adjCities.add( adjCity );
				graphAjaMap.put( key, adjCities);
			} else {
				adjCities.add( adjCity );
				graphAjaMap.put( key, adjCities);			
			}	  
			return true;
	  }
	  
	  public void checkout() {
		  
		    logger.debug("============= checkout graph content and feature ==================");
		  
			graphAjaMap.keySet().forEach( s -> 	logger.debug(s) );
		    
		    graphAjaMap.get( "Boston" ).forEach(s -> logger.debug(s));
		    
		    graphAjaMap.get( "Trenton" ).forEach(s -> logger.debug(s));	    
	  }
	  
	  // Graph BFS search the connection given two cities 
	  public String breadthFirstSearch(String originCity, String destCity) {

		    if (originCity == null)
		        return "no";
		    
		    if (destCity == null)
		        return "no";
		    
		    if( destCity.equalsIgnoreCase(originCity))
		    	return "yes";
		    
		    if ( Character.isLowerCase(originCity.charAt(0)) )
		    	originCity = originCity.substring(0, 1).toUpperCase() + originCity.substring(1);

		    
		    if ( Character.isLowerCase(destCity.charAt(0)) )
		    	destCity = destCity.substring(0, 1).toUpperCase() + destCity.substring(1);

		    // Creating the BFS queue, and adding the start city (step 1)
		    LinkedList<String> queue = new LinkedList<>();
		    queue.add(originCity);  //FIFO
		    HashSet<String> visitedCities = new HashSet<>();	    		
		    
		    while (!queue.isEmpty()) {
		    	String  currentFirst = queue.removeFirst();
		        //  we make sure to check and skip that city if we have encountered it before    	
		        if (visitedCities.contains(currentFirst) )
		        	continue;

		        // Mark the city as visited
		        visitedCities.add(currentFirst);
		        logger.debug(currentFirst + " ");

		        HashSet<String> allNeighbors = graphAjaMap.get(currentFirst);

		        // check whether the list of neighbors is null before proceeding, otherwise
		        // the for-each loop will throw an exception. 
		        if (allNeighbors == null)
		        	return "no";

		        for (String neighbor : allNeighbors) {
		            // We only add unvisited neighbors
		            if ( !visitedCities.contains(neighbor) ) {	
		            	if( neighbor.equalsIgnoreCase(destCity))
		            		return "yes";
		                queue.add(neighbor);
		            }
		        }
		    }
		    return "no";
		}
	  
	  
}

