FROM gradle:7.5-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8081

# ARG JAR_FILE=build/libs/\*.jar
COPY --from=build /home/gradle/src/build/libs/saturn-0.0.1-SNAPSHOT.jar /app/saturn-be.jar
# COPY ./src/main/resources/templates/* /templates
# COPY ./build/libs/saturn-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar" ,"/app/saturn-be.jar"]
