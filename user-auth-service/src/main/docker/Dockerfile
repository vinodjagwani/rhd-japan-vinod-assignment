FROM openjdk:11-jdk-slim

EXPOSE 8081
EXPOSE 5005


COPY user-auth-service-1.0.jar /app.jar

CMD ["/usr/local/openjdk-11/bin/java", "-jar", "-Dspring.profiles.active=docker", "-Djdk.tls.client.protocols=TLSv1.2", "/app.jar"]


