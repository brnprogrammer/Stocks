FROM java:8-jdk-alpine
COPY ./build/libs/stock-manager-0.0.1-SNAPSHOT.jar/usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", stock-manager-0.0.1-SNAPSHOT.jar"]