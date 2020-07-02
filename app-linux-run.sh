cd gameapi
./mvnw clean package

cd webapp/target

java -jar webapp-0.0.1-SNAPSHOT.jar
