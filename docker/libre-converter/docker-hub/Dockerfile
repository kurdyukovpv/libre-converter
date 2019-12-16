FROM qrdp/libre:0.3
COPY . /usr/src/libre-converter/
WORKDIR /usr/src/libre-converter/
RUN ./mvnw package
WORKDIR /usr/src/libre-converter/.artifacts
CMD java -jar -Dspring.profiles.active=docker libre-converter.jar
