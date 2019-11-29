FROM ubuntu-jdk

MAINTAINER George Fouche "fouliex@gmail.com"

ENV version=aws-db-usage

#ENV dbuser=
#ENV dbpass=
#ENV jdbcurl=

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
