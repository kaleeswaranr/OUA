Pre-Requesties
---------------
JAVA(1.7)
MAVAN (Tested with 3.1)
MYSQL (Tested with 5.7)

Please follow the step to change the configuration
----------------------------------------------
1. Create the Database with name of TODO_MGR or you can create with any name and change the configuration.
2. Open the pom.xml file from the root(parent), set the value for following tag value <db.url>,<db.username>,<db.password> and 
	a) Look at <db.url> and change the Database name, if you would have created newly insead of TODO_MGR. and verify the port number.
	b) Look at <db.username> and change
	c) Look at <db.password> and change
3. open Activity.sql from database folder and change DB name (if you would have created newly).
4. open following files,
	\oua-persistence\src\test\resources\db.properties, (It's for test from persistence layer)
	\oua-service\src\test\resources\db.properties (It's for test from service layer)
	\oua-web\src\main\resources\application.properties (It's for application)
	
5. Go to command promt or shell to the following dir of where the root(parent) pom.xml file located

6. Execute the mvn clean package, once build success go to next step

7. java -jar \target\dependency\webapp-runner.jar \oua-web\target\TODOManager.war

8. Application Default URL - http://localhost:8080/ It will lanch the Default Page

9. Click Activity Menu and You can the TODOManager Item, Add and Delete acivity you can do in this screen

