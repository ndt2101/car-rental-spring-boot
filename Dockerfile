FROM maven:latest
RUN mkdir /project
WORKDIR /project
COPY . .
CMD mvn spring-boot:run
