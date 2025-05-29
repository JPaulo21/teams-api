FROM amazoncorretto:23-alpine3.20-jdk AS builder
WORKDIR /source
COPY . .
RUN ./gradlew clean bootJar -x test

FROM amazoncorretto:23-alpine3.20-jdk AS final
LABEL maintaner="JP"
WORKDIR /app
COPY --from=builder /source/build/libs/teams-api*.jar teams-api.jar
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-jar", "teams-api.jar"]