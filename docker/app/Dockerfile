FROM openjdk:14

RUN rm -f /var/cache/apk/*

COPY challege-cats.jar /image/challege-cats.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=CHALLEGE", "/image/challege-cats.jar"]

EXPOSE 8041