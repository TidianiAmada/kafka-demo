FROM adoptopenjdk/openjdk16
ADD target/kakfa-first-demo-1.0-SNAPSHOT.jar kakfa-first-demo-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "kakfa-first-demo-1.0-SNAPSHOT.jar"]