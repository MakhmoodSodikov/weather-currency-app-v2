FROM openjdk:8-jdk-alpine
EXPOSE 8761
RUN mkdir eureka
COPY target/eureka-0.0.1-SNAPSHOT.jar /eureka
WORKDIR /eureka
CMD ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]