FROM openjdk:8u111-jdk-alpine


# setup group and user, initialize home

WORKDIR app
COPY EurekaServerDemo-0.0.1-SNAPSHOT.jar /app

EXPOSE 8000 8080 9080 8761

CMD ["java", \
"-jar", \
"./EurekaServerDemo-0.0.1-SNAPSHOT.jar"]
