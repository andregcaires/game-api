# GameAPI - Quake Log Parser

Projeto desenvolvido em Java 11 que realiza o parse do arquivo [games.log], gerado pelo game Quake 3 Arena e expõe uma API RESTful para consulta dos dados de cada partida

## O projeto foi desenvolvido com uso de Spring Boot e Maven, e é dividido em quatro módulos:

* context: classes de repositório / acesso à dados
* core: classes de aplicação e serviço, onde são realizadas as serializações do arquivo games.log
* domain: classes de domínio / entidades, que representam as tabelas do banco de dados
* webapp: classes de recursos REST: a camada de entrada da aplicação para acesso aos dados serializados do arquivo games.log

## Solução do desafio: 

 Ao executar o projeto, o projeto webapp procura pelo arquivo na pasta resources, o lê e o envia para a classe de aplicação para iniciar a leitura por linha e o processo de parsing
 
 Esta classe de aplicação possui acesso a uma classe de palavras-chave que quando encontradas na linha atual que está processando, chama um método de parse encontrado em alguma das classes de serviço, através de suas interfaces injetadas na aplicação (DI /IoC)
 
 * InitGame: realiza o parse das informações de uma partida
 * ClientUserInfoChanged: realiza o parse e captura as informações de um jogador. Após sua captura, insere os dados em uma tabela caso já não o tenha salvo
 * Kill: realiza o parse e captura de uma morte, do jogador que matou e do jogador morto
 * Shutdown: encerra a partida atual salvando seus dados em uma tabela, juntamente a lista de jogadores participantes, uma lista de mortes por jogador e o total de mortes
 
  Observação: no arquivo há uma partida que se encerra sem o Shutdown ser registrado, portanto o processo de Shutdown também é realizado quando o parser encontra uma linha com apenas traços (ex: "-------) e quando um jogo estiver iniciado
  
 Após o processo de parse e persistência em um banco de dados em memória (foi utilizado H2), o projeto expõe uma API REST na porta 8080 com o endpoint /games para consulta dos dados das partidas. No console / prompt de comando, é exibida uma mensagem informando o endpoint do Swagger com a documentação da API, com os seguintes métodos: 
 
| Verbo HTTP  | Endpoint            | Retorno                                                |   |   |
|-------------|---------------------|--------------------------------------------------------|---|---|
| GET         | /games              | retorna uma lista com um resumo de todas as partidas   |   |   |
| GET         | /games/{id}         | retorna um resumo da partida com o ID informado        |   |   |
| GET         | /games/{id}/details | retorna dados detalhados da partida com o ID informado |   |   |

## Execução do projeto

Este projeto requer a instalação do [Docker](https://www.docker.com/) ou de alguma distribuição do JDK 11 (para desenvolvimento foi utilizada a distribuição [Amazon Corretto OpenJDK](https://aws.amazon.com/corretto/))

### Ambiente Windows - JDK 11

Executar o arquivo app-windows-run.cmd, localizado na raiz do projeto

### Ambiente Windows - Docker

Executar o arquivo docker-windows-cmd.sh, localizado na raiz do projeto

### Ambiente Linux - JDK 11

Executar o arquivo app-linux-run.sh, localizado na raiz do projeto

### Ambiente Linux - Docker

Executar o arquivo docker-linux-run.sh, localizado na raiz do projeto

[games.log]: https://github.com/andregcaires/game-api/blob/master/gameapi/webapp/src/main/resources/games.log
