# Spring Boot JMS with Durable PubSub

This project implements a simple chat to demonstrate how to use durable subscription with JMS and Spring Boot.

## Message Broker

[ActiveMQ Artemis](https://activemq.apache.org/components/artemis/) is used as the message broker. You can start it using Docker:

    docker run --name artemis --detach --rm -p 61616:61616 -p 8161:8161 -e ANONYMOUS_LOGIN=true \
               apache/activemq-artemis:latest-alpine

The admin console runs on port 8161

http://localhost:8161 (username/password: admin/admin)

## Running the Application

You must run the application twice that you can chat with yourself:
 
    ./mvnw spring-boot:run -Dspring-boot.run.arguments="--chat.user=One"

    ./mvnw spring-boot:run -Dspring-boot.run.arguments="--chat.user=Two"
