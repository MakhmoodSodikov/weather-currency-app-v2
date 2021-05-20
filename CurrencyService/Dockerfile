FROM openjdk:8-jdk-alpine
EXPOSE 8082
RUN mkdir currencyService
COPY target/currencyservice-0.0.1-SNAPSHOT.jar /currencyService
WORKDIR /currencyService
CMD ["java", "-jar", "currencyservice-0.0.1-SNAPSHOT.jar"]