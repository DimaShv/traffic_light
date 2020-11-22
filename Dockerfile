FROM openjdk:11

WORKDIR /tmp
COPY . /tmp

RUN chmod +x gradlew
RUN ./gradlew clean build

EXPOSE 8080

CMD java -jar /tmp/build/libs/crossroads-1.0-SNAPSHOT.jar
