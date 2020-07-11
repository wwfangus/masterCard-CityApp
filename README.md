repository: masterCard-CityApp
# MasterCard Code Challenge by Wayne Fang
## Use Spring Boot and Java 1.8 to build application to determine city connected or not
Given a city input file, city.txt with lines of two city name separated by ','. The application should
be able to contruct graph from the file. Then the graph is used to search city connection paths available
between two city names supplied by Spring Boot RequestParameters in URL calls.

## Graph Undirected
I used Java HashMap with city-name as key, and all adjacent cities as value indicated by the input file for the key city.
The value is a HashSet containing adjacent cities. The graph is built as undirected by add adjacent relationship between
both the first and second city names in the file line.

## Graph Search given origin city and destination city
I used the well known Breadth First Search for the graph using the origin city as the graph starting point. The destination 
city is used as search target when the program visits the graph built for the cities. If the destionation city was found, the
connected query will be returned "yes". Otherwise, the connected query will be returned no.

## Spring Boot Web Service Architecture
The project is called "SpringBoot-MC-city", the same as the root directory. The application source code is under src/main with 
package named com.SpringBoot.MasterCard.WayneFang. There are 3 classes in the package: the Spring Boot App, the Controller, and
the graph model-service class, CityGraph.

### CityConnRestController
The SpringBoot controller is CityConnRestController. It has 3 methods:
initialize: initialize the cityGraph from provided file city.txt.
getConnection: a @RequestMapping method for handling http://localhost:8080/connected?origin=Boston&destination=Newark web call.
getConn: is a method calling CityGraph search method breadthFirstSearch. The layer is created to avoid JUnit complication during 
testing. This method is used by all JUnit test cases as well as the real web service call.

## JUnit Tests and  Browser URL Tests
I have used Junit tests to develop (refractoring and testing continuously) the CityGraph class, which is the core of the application.
All the Junit cases passed. 

Then I also tested with the corresponding real URLs after the Spring Boot application starts. When you do want to insert a <br />
test case 1: When you do want to insert a <br />
http://localhost:8080/connected?origin=Boston&destination=Newark
test case 2:
http://localhost:8080/connected?origin=Newark&destination=Boston
test case 3:
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
test case 4:
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
test case 5:
http://localhost:8080/connected?origin=Trenton&destination=Albany
test case 6:
http://localhost:8080/connected?origin=Trenton&destination=Newark
test case 4:
http://localhost:8080/connected?origin=Trenton&destination=Trenton

