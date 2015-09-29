FROM java/openjdk-8u66

MAINTAINER Nikolaus Piccolotto

COPY target/bunte-welt.jar /

EXPOSE 8080
ENV HTTP_PORT=8080

CMD java -jar /bunte-welt.jar
