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
  
### <a name="Sobre">1. Sobre o projeto</a> 

&nbsp;&nbsp;&nbsp;&nbsp;O objetivo deste projeto é criar uma API rest que armazenará dados como nome de um planeta, clima e terreno. Retornando também a quantidade de aparições de cada planeta nos filmes de Star Wars registrada na API SWAPI. Esse projeto será consumido por um jogo criado pela B2W que aproveitará das informações da franquia.

### <a name="Tecnologias">2.Tecnologias utilizadas</a> 
&nbsp;&nbsp;&nbsp;&nbsp;Para o presente projeto foi utilizado a linguagem Java na sua versão 8, Spring Boot 2.0 e a IDE Eclipse modificada para o framework Spring Boot(Spring Tools Suite). 
Para a persistência dos dados foi utilizado o banco de dados não relacional MongoDB e para testes da api o Postman junto com o framework  JUNIT.

### <a name="Config">3.Configurando a API</a>  
&nbsp;&nbsp;&nbsp;&nbsp;Para utilizar o projeto deverá ser instalado o <a href="http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html">Java SDK 8</a>, o Eclipse, 
preferencialmente modificado para o Spring Boot(<a href="https://spring.io/tools/sts/all">Spring Tools Suite</a>) e o 
<a href="https://www.mongodb.com/download-center?jmp=nav#community">MongoDB Community Server</a> baseado em seu sistema operacional.

&nbsp;&nbsp;&nbsp;&nbsp;Após isso, efetuar o download do projeto e inserir o mesmo no diretorio raiz do seu workspace do Eclipse, importar um novo projeto Maven pelo Eclipse, esperar o Maven baixar as dependências,
executar o mongoDB e dar Run em Spring Boot App no Eclipse.

&nbsp;&nbsp;&nbsp;&nbsp;Pronto, a API já estará funcionando, ela gera automaticamente o banco vazio no MongoDB. Caso ocorra algum problema da porta, você poderá entrar em application.properties e mudar server.port para alguma outra porta que desejar.

### <a name="Testes">4.Efetuando testes</a>  

&nbsp;&nbsp;&nbsp;&nbsp;Com o Eclipse aberto, ir em src/test/Java, escolher a parte do teste que deseja efetuar e dar run com JUNIT.

### <a name="Funcionalidades">5.Funcionalidades</a>

#### <a name="Insere">I. Inserindo um planeta:</a>  

&nbsp;&nbsp;&nbsp;&nbsp;Para inserir um planeta deve ser feita uma requisição post em json para o endpoint "/planetas".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
http://localhost:8080/planetas
```JSON
{
   "name": "Yavin IV",
   "clima": "Frio",
   "terrain": "jungle, rainforests"
}
```
&nbsp;&nbsp;&nbsp;&nbsp;Será criado um novo planeta no banco de dados ao qual a ID será gerada automaticamente, não importando se o usuário setar uma id na hora da inserção. Se for inserido um nome, clima ou terreno com caractere vazio ou null na API, ela retornara o erro 400 de requisição inválida. Um planeta que não apareceu nos filmes também pode ser criado, porém seu numero de aparições será automaticamente 0.

#### <a name="Lista">II. Listando todos os planetas:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Para listar todos os planetas basta fazer uma solicitação get para o endpoint "/planetas".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
http://localhost:8080/planetas

&nbsp;&nbsp;&nbsp;&nbsp;Será retornado a id dos planetas, seu nome, seu clima, terreno e aparições em filmes no formato json. As aparições dos planetas são atualizadas a cada solicitação get, para caso haja alguma alteração no banco de dados do SWAPI ele retorne as aparições atualizadas. 

#### <a name="buscaid">III. Fazendo busca por ID:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Para fazer uma busca por id você deverá fazer uma solicitação get para o endpoint "/planetas/" junto com a id que você quer pesquisar. 

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
http://localhost:8080/planetas/5afcf2bf1d5bad0100e51bda

&nbsp;&nbsp;&nbsp;&nbsp;Caso seja inserida uma id inválida, será retornado o erro 404 não encontrado. 

#### <a name="buscanome">IV. Fazendo busca por NOME:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Para fazer uma busca por nome você deverá fazer uma solicitação get para o endpoint "/planetas/buscanome?nome=" junto com o nome codificado que você quer pesquisar. 

&nbsp;&nbsp;&nbsp;&nbsp;Para codificar o valor que você quer, você pode abrir seu navegador, ir em ferramentas de desenvolvedor, depois console e digitar "encodeURIComponent("Nome do planeta")" o console retornará o nome do planeta codificado como no exemplo abaixo.

<p>
  <img src="img/Exemplo.png"/> 
</p>

&nbsp;&nbsp;&nbsp;&nbsp;Depois basta copiar o código gerado e inserir no final do endpoint informado acima o ""/planetas/buscanome?nome=".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
http://localhost:8080/planetas/buscanome?nome=Yavin%20IV

#### <a name="deleta">V. Deletando um planeta:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Para deletar um planeta basta fazer uma solicitação delete para o endpoint "/planetas/" indicando a ID do planeta no final do endpoint.

&nbsp;&nbsp;&nbsp;&nbsp;Ex: http://localhost:8080/planetas/1

&nbsp;&nbsp;&nbsp;&nbsp;Caso esse planeta não exista ele retornará o erro 404 não encontrado.

