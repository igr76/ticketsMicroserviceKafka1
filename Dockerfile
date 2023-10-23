FROM maven:3-eclipse-temurin-17 AS MAVEN
WORKDIR /build/
COPY pom.xml /build
COPY src /build/src
RUN mvn clean install -DskipTests=true

FROM eclipse-temurin:17-jdk
COPY --from=MAVEN /build/target/stm-labs.jar /opt/stm-labs.jar
ENTRYPOINT java -Xms512m -Xmx1024m -Duser.timezone=UTC -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9999 -jar -Djava.security.egd=file:/dev/./urandom /opt/stm-labs.jar
