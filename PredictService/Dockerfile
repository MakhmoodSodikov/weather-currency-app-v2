FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN mkdir predictService
COPY target/predictservice-0.0.1-SNAPSHOT.jar /predictService
WORKDIR /predictService
CMD ["java", "-jar", "predictservice-0.0.1-SNAPSHOT.jar"]