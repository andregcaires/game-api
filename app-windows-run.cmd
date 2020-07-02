cd gameapi
call ./mvnw clean package

cd webapp\target
dir
java -jar webapp-0.0.1-SNAPSHOT.jar
pause