FROM maven:3.6.0-jdk-8 as build
WORKDIR /app
COPY . /app
RUN _JAVA_OPTIONS=-Djdk.net.URLClassPath.disableClassPathURLCheck=true mvn clean package

FROM openjdk:8-jre
WORKDIR /root/
COPY --from=build /app/target/weddingsite-*.jar ./weddingsite.jar
EXPOSE 8088
CMD ["/usr/bin/java", "-jar", "./weddingsite.jar"]
