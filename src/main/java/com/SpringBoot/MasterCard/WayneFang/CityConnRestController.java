package com.SpringBoot.MasterCard.WayneFang;

import com.SpringBoot.MasterCard.WayneFang.CityGraph;
import java.util.HashMap; // import the HashMap class
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.junit.BeforeClass;

@RestController
public class CityConnRestController {
	
	private boolean initialized = false;
	CityGraph graph;
	
	public CityConnRestController() { 
	   if (!initialized) {
		   graph = new CityGraph();
	       initialized = true;
	   }
	}
  
  @RequestMapping(value="connected")
  public @ResponseBody String getConnection(@RequestParam HashMap<String,String> allParams) {
	    
	    String origCity=allParams.get("origin");
	    String destCity=allParams.get("destination");
	    
	    System.out.println("======web=============origin============" + origCity );
	    System.out.println("======web=============destin============" + destCity );
	    
	    return getConn( allParams );
	    
  }
 
 protected String getConn(HashMap<String, String> testParams) {
		String origCity=testParams.get("origin");
		String destCity=testParams.get("destination");
		
	    return graph.breadthFirstSearch(origCity, destCity);
	}

}

