#stage 1
FROM maven:3.10.0-eclipse-temurin-21-resolute AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

#stage 2
FROM bellsoft/liberica-openjdk-alpine:21 AS extractor

WORKDIR /source

COPY --from=builder /build/target/*.jar application.jar

#unpackage layered jar file
RUN java -Djarmode=tools -jar application.jar extract --layers --launcher --destination extracted

# stage 3
FROM bellsoft/liberica-openjre-alpine:21

RUN addgroup -S nonroot \
    && adduser -S nonroot -G nonroot

VOLUME /tmp

WORKDIR /usr/share/app

COPY --chown=nonroot:nonroot --from=extractor /source/extracted/dependencies/ ./
COPY --chown=nonroot:nonroot --from=extractor /source/extracted/spring-boot-loader/ ./
COPY --chown=nonroot:nonroot --from=extractor /source/extracted/snapshot-dependencies/ ./
COPY --chown=nonroot:nonroot --from=extractor /source/extracted/application/ ./

USER nonroot

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
