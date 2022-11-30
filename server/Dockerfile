FROM openjdk:8-jdk-alpine
RUN mkdir logs

RUN addgroup -S novel && adduser -S novel -G novel
RUN chown novel:novel logs
USER novel:novel
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} novel.jar
ENTRYPOINT ["java","-jar","/novel.jar"]