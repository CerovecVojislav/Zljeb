FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /data
COPY /data/* /data/
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]