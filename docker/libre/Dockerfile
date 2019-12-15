FROM kurdyukovpv/libre:0.2
COPY ./.artifacts /usr/src/libre-converter/.artifacts
WORKDIR /usr/src/libre-converter/.artifacts
CMD java -jar -Dspring.profiles.active=docker libre-converter.jar