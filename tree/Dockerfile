FROM openjdk:8-alpine

COPY target/uberjar/tree.jar /tree/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/tree/app.jar"]
