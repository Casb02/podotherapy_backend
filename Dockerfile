# Stage 1: Build the application
FROM gradle:7.6.3-jdk17 as build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . /home/gradle/src
# Dont run tests
RUN gradle build bootJar --no-daemon --exclude-task test --exclude-task check

# log all files in --from=build /home/gradle/src/build/libs/
RUN ls -la /home/gradle/src/build/libs/

# Stage 2: Setup the PostgreSQL database and run the application
FROM openjdk:17-jdk-slim

# Add Maintainer Info
LABEL maintainer="casb02"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the working directory in the container
WORKDIR /app

# Copy spring configuration file
COPY application-docker.properties /app/application.properties

# Copy the application's jar file from the build stage to the container
COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

#WRITE THE ENVIRONMENT VARIABLES TO THE FILE application.properties
RUN echo "spring.datasource.url=${SPRING_DATASOURCE_URL}" >> /app/application.properties
RUN echo "spring.datasource.username=${SPRING_DATASOURCE_USERNAME}" >> /app/application.properties
RUN echo "spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}" >> /app/application.properties

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar","--spring.config.location=file:/app/application.properties"]