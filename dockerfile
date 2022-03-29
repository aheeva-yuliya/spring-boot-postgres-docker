# Use base image with preinstalled JDK 11
FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine

# Add src folder to to the image
ADD . /src

# Set src directory as working dir
WORKDIR /src

# Run Maven build command
RUN /bin/sh ./mvnw -DskipTests=true package

# Application will use 8080 port by default
EXPOSE 8081

# Run executable container
ENTRYPOINT ["java","-jar","target/spring-boot-postgres-docker-1.jar"]
