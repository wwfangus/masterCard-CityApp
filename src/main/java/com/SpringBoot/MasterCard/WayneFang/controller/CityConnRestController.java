package com.SpringBoot.MasterCard.WayneFang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap; // import the HashMap class
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.MasterCard.WayneFang.service.CityGraphService;


@RestController
public class CityConnRestController {
	private static final Logger logger = LoggerFactory.getLogger(CityConnRestController.class);
	private boolean initialized = false;
	private CityGraphService graph;
	
	public CityConnRestController() { 
	   if (!initialized) {
		   graph = new CityGraphService();
	       initialized = true;
	   }
	}
  
  public CityGraphService getGraph() {
		return graph;
	}

	public void setGraph(CityGraphService graph) {
		this.graph = graph;
	}

@RequestMapping(value="connected")
  public @ResponseBody String getConnection(@RequestParam HashMap<String,String> allParams) {
	    
	    String origCity=allParams.get("origin");
	    String destCity=allParams.get("destination");
	    
	    logger.debug("======web=============origin============" + origCity );
	    logger.debug("======web=============destin============" + destCity );
	    
	    return getConn( allParams );
	    
  }
 
 public String getConn(HashMap<String, String> testParams) {
		String origCity=testParams.get("origin");
		String destCity=testParams.get("destination");
		
	    return graph.breadthFirstSearch(origCity, destCity);
	}

}

