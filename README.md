<p>
  <img src="img/sw.jpg"/> 
</p>

# DESAFIO API STAR WARS B2W - PEDRO TEIXEIRA BISOGNIN

## Índice

 <ol>
  <li><a href="#Sobre">Sobre o projeto</a></li>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Config">Configurando a API</a></li>
  <li><a href="#Testes">Efetuando testes</a></li>
  <li><a href="#Funcionalidades">Funcionalidades</a>
    <ol>
      <li><a href="#Insere">Inserindo um planeta</a></li>
      <li><a href="#Lista">Listando todos os planetas</a></li>
      <li><a href="#buscaid">Fazendo busca por ID</a></li>
      <li><a href="#buscanome">Fazendo busca por NOME</a></li>
      <li><a href="#deleta">Deletando um planeta</a></li>
    </ol>
  </li>
</ol> 

<dl>
  
### <dt><a name="Sobre">1. Sobre o projeto</a></dt> 

O objetivo deste projeto é criar uma API rest ao qual será consumida por um jogo criado pela B2W que se aproveitará das informações da franquia Star Wars, dados como nome do planeta, clima, terreno e quantidade de aparições em filmes.

### <dt><a name="Tecnologias">2.Tecnologias utilizadas</a></dt> 
Para o presente projeto foi utilizado a linguagem Java na sua versão 8, Spring Boot 2.0 e a IDE Eclipse modificada para o framework Spring Boot(Spring Tools Suite). 
Para a persistência dos dados foi utilizado o banco de dados não relacional MongoDB e para testes da api o Postman junto com o framework  JUNIT.

### <dt><a name="Config">3.Configurando a API</a></dt>  
Para utilizar o projeto deverá ser instalado o <a href="http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html">Java SDK 8</a>,o Eclipse, 
preferencialmente modificado para o Spring Boot(<a href="https://spring.io/tools/sts/all">Spring Tools Suite</a>) e o 
<a href="https://www.mongodb.com/download-center?jmp=nav#community">MongoDB Community Server</a> baseado em seu sistema operacional.

Após isso, efetuar o download do projeto e inserir o mesmo no diretorio raiz do seu workspace do Eclipse, importar um novo projeto Maven pelo Eclipse, esperar o Maven baixar as dependências,
executar o mongoDB e dar Run em Spring Boot App no Eclipse.

Pronto, a API já estará funcionando, ela gera automaticamente o banco vazio no MongoDB. Caso ocorra algum problema da porta, você poderá entrar em application.properties e mudar server.port para alguma outra porta que desejar.

### <dt><a name="Testes">4.Efetuando testes</a></dt>  

Com o Eclipse aberto, ir em src/test/Java, escolher a parte do teste que deseja efetuar e dar run com JUNIT.

### <dt><a name="Funcionalidades">5.Funcionalidades</a></dt>

#### <dd><a name="Insere">I. Inserindo um planeta:</a></dd>  

<p>Para inserir um planeta deve ser feita uma requisição post em json para o endpoint "/planetas".<p/>

Ex:
http://localhost:8080/planetas
```JSON
{
   "name": "Yavin IV",
   "Clima": "Frio",
   "terrain": "jungle, rainforests"
}
```
<p>Será criado um novo planeta no banco de dados ao qual a ID será gerada automaticamente, não importando se o usuário setar uma id na hora da inserção.<p/>

#### <dd><a name="Lista">II. Listando todos os planetas:</a></dd>

Para listar todos os planetas basta fazer uma solicitação get para o endpoint "/planetas".

Ex:
http://localhost:8080/planetas

Será retornado a id dos planetas, nome, clima, terreno e aparições em filmes no formato json.

#### <dd><a name="buscaid">III. Fazendo busca por ID:</a></dd>

Para fazer uma busca por id você deverá fazer uma solicitação get para o endpoint "/planetas/" junto com a id que você quer pesquisar. 

Ex:
http://localhost:8080/planetas/5afcf2bf1d5bad0100e51bda

#### <dd><a name="buscanome">IV. Fazendo busca por NOME:</a></dd>

Para fazer uma busca por id você deverá fazer uma solicitação get para o endpoint "/planetas/buscanome?nome=" junto com o nome codificado que você quer pesquisar. 

Para codificar o valor que você quer, você pode abrir seu navegador, ir em ferramentas de desenvolvedor, depois console e digitar "encodeURIComponent("Nome do planeta")" o console retornará o nome do planeta codificado como no exemplo abaixo.

<p>
  <img src="img/Exemplo.png"/> 
</p>

Depois basta copiar o código gerado e inserir no final do endpoint informado acima o ""/planetas/buscanome?nome=".
Ex:
http://localhost:8080/planetas/buscanome?nome=Yavin%20IV

#### <dd><a name="deleta">V. Deletando um planeta:</a></dd>

Para deletar um planeta basta fazer uma solicitação delete para o endpoint "/planetas/" indicando a ID do planeta no final do endpoint.
Ex:
http://localhost:8080/planetas/1
</dl>
