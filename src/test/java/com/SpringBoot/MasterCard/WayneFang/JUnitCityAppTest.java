package com.SpringBoot.MasterCard.WayneFang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JUnitCityAppTest {

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
        
        System.out.println( "test 1 Result: " + result );
        myController.graph.checkout();
        
        assertEquals(result, "yes");
	}
	
	@Test // case 2
	public void testController2() {	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put( "destination", "Boston");
		testParams.put("origin", "Newark");

        String result = myController.getConn( testParams );
        
        System.out.println( "test 2 Result: " + result );
        
        assertEquals(result, "yes");
	}
	
	@Test  // case 3
	public void testController3() {	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put( "origin", "Boston");
		testParams.put("destination", "Philadelphia");

		//CityConnRestController myController = new CityConnRestController();
        String result = myController.getConn( testParams );
        
        System.out.println( "test 3 Result: " + result );
        
        assertEquals(result, "yes");
	}
	
	@Test  // case 4
	public void testController4() {
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Philadelphia");
		testParams.put("destination", "Albany");

        String result = myController.getConn( testParams );
        
        System.out.println( "test 4 Result: " + result );
        
        assertEquals(result, "no");
	}
		
	@Test  // case 5
	public void testController5() {
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Albany");

        String result = myController.getConn( testParams );
        
        System.out.println( "test 5 Result: " + result );
        
        assertEquals(result, "yes"); 	
	}
	
	@Test  // case 6
	public void testController6() {

		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Newark");

        String result = myController.getConn( testParams );
        
        System.out.println( "test 6 Result: " + result );
        
        assertEquals(result, "no");  
	}

	@Test  // case 7
	public void testController7() {
	
		HashMap<String,String> testParams = new HashMap<>();
		testParams.put("origin", "Trenton");
		testParams.put("destination", "Trenton");
	
	    String result = myController.getConn( testParams );
	    
	    System.out.println( "test 7 Result: " + result );
	    
	    assertEquals(result, "yes");  
	}	

}
