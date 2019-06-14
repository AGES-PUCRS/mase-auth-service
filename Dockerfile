FROM openjdk:8-jdk-alpine
WORKDIR opt/app
COPY ./ /opt/app/
RUN ./gradlew clean build
WORKDIR build/libs
ENTRYPOINT ["java","-jar", "auth-service-0.0.1-SNAPSHOT.jar" ]
