FROM ubuntu:latest
RUN apt-get update
RUN apt-get install fontconfig -y
RUN apt-get install ttf-mscorefonts-installer -y
RUN fc-cache -fv
RUN apt-get -y install openjdk-11-jre
RUN apt-get -y install libreoffice --no-install-recommends
