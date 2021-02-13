FROM adoptopenjdk/openjdk11
ADD target/dockerspringboot.jar dockerspringboot.jar
EXPOSE 6040
ENTRYPOINT ["java","-jar","dockerspringboot.jar"]