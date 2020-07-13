package com.SpringBoot.MasterCard.WayneFang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.SpringBoot.MasterCard.WayneFang.controller.CityConnRestController;

class JUnitCityGraphAppTest {

	private static final Logger logger = LoggerFactory.getLogger(JUnitCityGraphAppTest.class);

	private static boolean initialized = false;
	static CityConnRestController myController;
	@BeforeClass
	public void initialize() { 
	   if (!initialized) {
		   myController = new CityConnRestController();
	       initialized = true;
	   }
	}
	
	// case 1
	@Test  
	public void testController1() {
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Boston");
		testParams.put("destination", "Newark");

		initialize();

        String result = myController.getConn( testParams );
        
        logger.debug("--test 2 Result: " + result );
        myController.getGraph().checkout();
        
        assertEquals(result, "yes");
	}
	
	@Test // case 2
	public void testController2() {	
		HashMap<String,String> testParams = new HashMap<>();	
		testParams.put("origin", "newark"); // lower case 
		testParams.put( "destination", "boston");

        String result = myController.getConn( testParams );
        
        logger.debug( "--test 2 Result: " + result );
        
        assertEquals(result, "yes");
	}
	
	@Test  // case 3
	public void testController3() {	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put( "origin", "Boston");
		testParams.put("destination", "Philadelphia");

		//CityConnRestController myController = new CityConnRestController();
        String result = myController.getConn( testParams );
        
        logger.debug( "--test 3 Result: " + result );
        
        assertEquals(result, "yes");
	}
	
	@Test  // case 4
	public void testController4() {
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Philadelphia");
		testParams.put("destination", "Albany");

        String result = myController.getConn( testParams );
        
        logger.debug( "--test 4 Result: " + result );
        
        assertEquals(result, "no");
	}
		
	@Test  // case 5
	public void testController5() {
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Albany");

        String result = myController.getConn( testParams );
        
        logger.debug( "--test 5 Result: " + result );
        
        assertEquals(result, "yes"); 	
	}
	
	@Test  // case 6
	public void testController6() {

		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Newark");

        String result = myController.getConn( testParams );
        
        logger.debug( "--test 6 Result: " + result );
        
        assertEquals(result, "no");  
	}

	@Test  // case 7
	public void testController7() {
	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Trenton");
	
	    String result = myController.getConn( testParams );
	    
	    logger.debug( "--test 7 Result: " + result );
	    
	    assertEquals(result, "yes");  
	}
	
	@Test  // case 7
	public void testController8() {
	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "NotCity");
		testParams.put("destination", "Trenton");
	
	    String result = myController.getConn( testParams );
	    
	    logger.debug( "--test 8 Result: " + result );
	    
	    assertEquals(result, "no");  
	}	

}
