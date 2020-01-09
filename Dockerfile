FROM openjdk:11.0.5-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/comments-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8082

CMD ["java", "-jar", "pdfhelper-services-1.0.0-SNAPSHOT.jar"]
