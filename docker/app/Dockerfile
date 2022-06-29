FROM openjdk:14

RUN rm -f /var/cache/apk/*

COPY challege-cats.jar /app/challege-cats.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=CHALLEGE", "/app/challege-cats.jar"]

EXPOSE 8041