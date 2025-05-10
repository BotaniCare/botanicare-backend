FROM eclipse-temurin:21-jre

COPY target/botanicare-0.0.1-SNAPSHOT.jar botanicare-0.0.1-SNAPSHOT.jar

EXPOSE 8443

ENTRYPOINT ["java","-jar","/botanicare-0.0.1-SNAPSHOT.jar"]