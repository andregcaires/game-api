# GameAPI - Quake Log Parser

Projeto desenvolvido em Java 11 que realiza o parse do arquivo games.log, gerado pelo game Quake 3 Arena e expõe uma API RESTful para consulta dos dados de cada partida

## O projeto foi desenvolvido com uso de Spring Boot e Maven, e é dividido em quatro módulos:

1. context: classes de repositório / acesso à dados
2. core: classes de aplicação e serviço, onde são realizadas as serializações do arquivo games.log
3. domain: classes de domínio / entidades, que representam as tabelas do banco de dados
4. webapp: classes de recursos REST: a camada de entrada da aplicação para acesso aos dados serializados do arquivo games.log

## Solução do desafio: 

 Ao executar o projeto, o projeto webapp procura pelo arquivo na pasta resources, o lê e o envia para a classe de aplicação para iniciar a leitura por linha e o processo de parsing
 
 Esta classe de aplicação possui acesso a uma classe de palavras-chave que quando encontradas na linha atual que está processando, chama um método de parse encontrado em alguma das classes de serviço, através de suas interfaces injetadas na aplicação (DI /IoC)
 
 1. InitGame: realiza o parse das informações de uma partida
 2. ClientUserInfoChanged: realiza o parse e captura as informações de um jogador. Após sua captura, insere os dados em uma tabela caso já não o tenha salvo
 3. Kill: realiza o parse e captura de uma morte, do jogador que matou e do jogador morto
 4. Shutdown: encerra a partida atual salvando seus dados em uma tabela, juntamente a lista de jogadores participantes, uma lista de mortes por jogador e o total de mortes
 
  Observação: no arquivo há uma partida que se encerra sem o Shutdown ser registrado, portanto o processo de Shutdown também é realizado quando o parser encontra uma linha com apenas traços (ex: "-------) e quando um jogo estiver iniciado
  
 Após o processo de parse e persistência em um banco de dados em memória (foi utilizado H2), o projeto expõe uma API REST na porta 8080 com o endpoint /games para consulta dos dados das partidas. No console / prompt de comando, é exibida uma mensagem informando o endpoint do Swagger com a documentação da API, com os seguintes métodos: 
 
  1. GET /games - retorna uma lista com um resumo de todas as partidas
  2. GET /games/{id} - retorna um resumo da partida com o ID informado
  3. GET /games/{id}/details - retorna dados detalhados da partida com o ID informado
  
## Execução do projeto

TODO
