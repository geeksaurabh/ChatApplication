# ChatApplication

This is a chat application developed using Spring Boot and JPA Repository.

# Features
### Real-time communication between clients and servers using WebSocket protocol
- Send, receive, update, and delete messages
- Store message data in a MySQL database using JPA Repository
- Basic authentication using Spring Security
## Technologies Used
 - Spring Boot
- WebSocket protocol
- JPA Repository
- MySQL database

## Requirements
- Java 11 or higher
- MySQL database
## Installation
- Clone the repository
- Configure the database connection in src/main/resources/application.properties
- Build the project using Maven: mvn clean install
- Run the application: mvn spring-boot:run
## Usage
- Open a web browser and go to http://localhost:8080
- Log in with the default credentials: username user and password password
- Enter a username to join the chat room
- Send and receive messages in real-time
- To send a message, type your message in the message input field and press Enter
- To update a message, double-click on the message you want to edit and make your changes
- To delete a message, right-click on the message you want to delete and choose "Delet
