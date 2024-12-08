FROM amazoncorretto:23-alpine3.20-jdk

LABEL maintaner="JP"

# ENV URL_DB_TEAMS=jdbc:postgresql://db-teams/db-teams
# ENV USER_DB_TEAMS=postgres
# ENV PASSWORD_DB_TEAMS=123

EXPOSE 8081
ENV PORT=8081

COPY build/libs/teams-api*.jar teams-api.jar

CMD ["java", "-jar", "teams-api.jar"]