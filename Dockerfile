FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /data
COPY /data/* /data/
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]