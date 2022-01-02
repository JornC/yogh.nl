FROM maven:3-openjdk-8 as builder

WORKDIR /app

COPY . .
RUN mvn clean install

FROM alpine

COPY --from=builder /app/target/card /web

