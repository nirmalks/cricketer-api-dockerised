FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

WORKDIR app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract --destination target/extracted
ARG EXTRACTED=target/extracted
COPY ${EXTRACTED}/dependencies/ ./
COPY  ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY  ${EXTRACTED}/application/ ./
EXPOSE 8080 5432

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]