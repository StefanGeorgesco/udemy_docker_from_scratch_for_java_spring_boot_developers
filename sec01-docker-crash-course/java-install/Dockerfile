FROM ubuntu:latest

WORKDIR /java

ADD https://download.java.net/java/GA/jdk25.0.2/b1e0dfa218384cb9959bdcb897162d4e/10/GPL/openjdk-25.0.2_linux-x64_bin.tar.gz java25.tar.gz

RUN tar -xvzf java25.tar.gz && rm java25.tar.gz

ENV PATH="$PATH:/java/jdk-25.0.2/bin"
