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

Then I also tested with the corresponding real URLs after the Spring Boot application starts. <br/>

After you load my project into your Eclipse or STS, then left-click the project, then go down to r"Run As" -> "Spring Boot App",
then after it starts, click the link below in your localhost:

test case 1: <br/>
http://localhost:8080/connected?origin=Boston&destination=Newark   <br/>
test case 2: test lower case first char <br/>
http://localhost:8080/connected?origin=newark&destination=boston  <br/>
test case 3:  <br/>
http://localhost:8080/connected?origin=Boston&destination=Philadelphia <br/>
test case 4:  <br/>
http://localhost:8080/connected?origin=Philadelphia&destination=Albany  <br/>
test case 5: <br/>
http://localhost:8080/connected?origin=Trenton&destination=Albany  <br/>
test case 6: <br/>
http://localhost:8080/connected?origin=Trenton&destination=Newark  <br/>
test case 7:<br/>
http://localhost:8080/connected?origin=Trenton&destination=Trenton<br/>
test case 8:<br/>
http://localhost:8080/connected?origin=NotCity&destination=Trenton<br/>

