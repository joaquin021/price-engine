FROM openjdk:17-slim-bullseye

WORKDIR /app

RUN apt -y update && apt install -y curl && apt install -y jq

COPY acceptance-test/test.sh .
COPY src/main/resources/application.yml .
COPY src/main/resources/data.sql .
COPY target/price-engine.jar .
RUN chmod +x test.sh

CMD ["/bin/bash", "-c", "./test.sh"]