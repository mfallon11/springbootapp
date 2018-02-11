# springbootapp

Thank you for allowing me a chance to take the coding assignment.

The project was build and test on Ubuntu 17.10

The github respository is at https://github.com/mfallon11/springbootapp.git

You will need maven installed.

To build and run the spring boot application enter "mvn package && java -jar target/gs-spring-boot-0.1.0.jar

You can use the following curl commands to interact with the endpoints:

Hello World
curl localhost:8080/hello

Expected Results: Hello World

Text Block Parser
curl -H "Content-Type application/json" -X POST -d '{"text":"A random text string of any text"}' localhost:8080/parsetext

Example results base on above input
[{"word":"A","occurrences":1},{"word":"any","occurrences":1},{"word":"of","occurrences":1},{"word":"random","occurrences":1},{"word":"string","occurrences":1},{"word":"text","occurrences":2}]

Fibonacci Sequence
curl localhost:8080/fibonacci?number=10

Example results based on above input
[1,1,2,3,5,8,13,21,34,55]

Deadlock
curl localhost:8080/deadlock

Expected Results
{"success":true} if there was a discovered deadlock
{"success":false} if it did not discover a deadlock

Employee (database)
Add Employee
curl -H "Content-Type: application/json" -X POST -d '{"employeeNumber":"1","firstName":"Matt","lastName":"Fallon"}' localhost:8080/employee/add

Expected Results
{"success":true} if employee was successfully added 
{"success":false} if employee was not successfully added

Get Employee
curl -H "Content-Type: application/json" -X POST -d '{"employeeNumber":"1"}' localhost:8080/employee/get

Expected Results
{"success":true,"employees":[{"employeeNumber":1,"firstName":"Matt","lastName":"Fallon"}]} if the employee was found
{"success":false,"employees":[]} if the employee was not found

Get All Employees
curl localhost:8080/employee/getall

Expected Results
{"success":true,"employees":[{"employeeNumber":1,"firstName":"Matt","lastName":"Fallon"},{"employeeNumber":2,"firstName":"John","lastName":"Doe"},{"employeeNumber":3,"firstName":"Jane","lastName":"Doe"}]} if successful
{"success":false,"employees":[]} if the employee was not found

Delete Employee
curl -H "Content-Type: application/json" -X POST -d '{"employeeNumber":"2"}' localhost:8080/employee/delete

Expected Results
{"success":true} if employee was successfully deleted 
{"success":false} if employee was not successfully deleted 

Typicode Post (Spring RestTemplate)
curl localhost:8080/typicode

ExpectedResults
[{"userId":1,"id":1,"title":"sunt aut facere repellat provident occaecati excepturi optio reprehenderit","body":"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"},...}]

Thank you for your time.

Expected Results: 
