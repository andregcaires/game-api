docker image build -t games-log-parser-api .

docker container stop gameapi
docker container rm gameapi
docker container run --name gameapi games-log-parser-api