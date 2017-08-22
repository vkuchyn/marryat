FROM java:8-jre
WORKDIR /root
EXPOSE 9626
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
RUN apt-get update
COPY build/libs/marryat-*.jar /root/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar