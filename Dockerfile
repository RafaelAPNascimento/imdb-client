FROM maven:3.6.3-openjdk-8-slim

COPY src /app/src
COPY pom.xml /app/pom.xml
WORKDIR app

RUN mvn clean package

EXPOSE 8181

CMD ["mvn", "exec:java"]
