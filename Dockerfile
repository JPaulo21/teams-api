FROM openjdk:17-alpine3.12

LABEL maintaner="JP"

COPY build/libs/teams-api*.jar teams-api.jar

CMD ["java", "-jar","teams-api.jar"]