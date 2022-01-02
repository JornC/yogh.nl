FROM maven:3-openjdk-8 as builder

WORKDIR /app

COPY . .
RUN mvn clean install

FROM jetty:11.0.7-jre11-slim

COPY --from=builder /app/target/card.war "${JETTY_BASE}/webapps/ROOT.war"

