FROM maven:3-openjdk-8 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install

FROM nginx:alpine
COPY --from=builder /app/target/card /usr/share/nginx/html
