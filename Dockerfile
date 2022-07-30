FROM nexus-ci.corp.dev.vtb/sutt-docker/openjdk/openjdk-11-rhel7:1.11-1

ADD target/*.jar app.jar

ENTRYPOINT ["java",\
    "-Djava.security.egd=file:/dev/./urandom",\
    "-jar",\
    "app.jar"]
