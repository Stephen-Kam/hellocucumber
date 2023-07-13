FROM maven:3-openjdk-17-slim@sha256:5ba3fae0f77cbe08deac4984dfa4f5397345d5ba8221871285a96e2ef8f16808
RUN apt-get update \
 && apt-get upgrade -y \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

ARG MVN_SETTINGS_FILE_SAAS

WORKDIR /

COPY src/test/java src/test/java
COPY src/test/resources src/test/resources
COPY pom.xml pom.xml
COPY ${MVN_SETTINGS_FILE_SAAS} /

CMD mvn verify ${MVN_OPTS} -Pui-component-tests -Dcucumber.filter.tags="${TEST_TAGS}" -s $MVN_SETTINGS_FILE_SAAS
