# Bolsa de valores Rest API
Backend de uma aplicação de compra e venda de ações na bolsa de valores

Está aplicação foi desenvolvida em Java.

# Tecnologias utilizadas
* **Git** : Controle de versionamento do sistema.
* **Spirng Boot** : Esse framwork é o mais usado e o mais completo hoje em dia para desenvolvimento Web Java.
* **Spring Tool Suite**: É uma IDE para desenvolvimento Java feito para usar o Spring Boot
* **Maven 4** : o mavem disponibiliza várias bibliotecas e Api que facilitam o desenvimento.
* **Swagger**: Eu usei o Swagger para fazer a documentação da Api. Ele é muito útil e permite de ganhar bastante tempo ao respeito da documentação da API.
* **Postman**: ele foi usado nesse projeto para efetuar todos os testes. Ele é muito útil para testar uma API que sej backend ou front end.
* **JavaMail** : é uma Api que permite de mandar e receber e-mail. Sua utilização é bem simples, é por isso que ele foi adotado nesse projeto
* **Postgres** : é um SGBD. Um dos mais usado hoje, fácil de usar com a sua interface gráfica.
* **JsonView**: é uma anotação que permite de selecionar os campos que quero imprimir quando faço uma requisição. Com essa anotação eu consigo ocultar algumas propriedades dos meus obejtos.

# Instruções de como rodar e utilizar o sistema

## Pré-requisito

* Git
* Spring Tool Suite
* PgAdmin
* Postman

## Passos

* Abrir um terminal (Ctrl + Shift + T (No ubuntu)) ou linha de comando(Butão Windows + R, digitar cmd e apertar Enter (no Windows))
* Navegar até onde quiser baixar o repositório
* No terminal, colar essa linha e apertar Enter
  ```
  git clone https://github.com/gildardmhn/Bolsa.git
  ```
* Abrir O PgAdmin e criar um banco de dados com o nome **bolsa**
* Abrir o Spring Tool Suite e clicar em **File** e depois em **Import**
* Na nova janela que aparece, escrever no campo do pesquisa **maven**
* Uma pasta com o nome **Maven** aparece. Dentro dessa pasta, clicar em **Existing Maven Projects** e clicar em Next
* Na próxima página, clicar em **Browse...** e navegar até a pasta **Bolsa** e clicar abrir.
* Clicar em  **Finish**
* Esperar o download das dependências do Maven
* Clique direito no projeto e escolher **Run as** e depois escolher **Spring Boot App**
* O projeto está agora rodando.

Se ele apresentar um erro, abra o pacote **src/main/ressources** e depois abra o arquivo **application.property**.
Coloque o usuário e a senha que você atribuiu ao postgres
 ```
spring.datasource.username= seu usuario
spring.datasource.password= sua senha 
```

## Usando a aplicação

Os enpoints da aplicação estão documentado nesse [link.](http://localhost:9000/swagger-ui.html) (A aplicação deve estar rodando quando clicar nesse link).
**Pode se efetuar os testes dos controllers nessa página apertando no botão Try Out**
* Primeiramente devemos cadastrar uma empresa mandando para uma requisião **POST** a **/app/empresa** com o formato seguinte
```
{
  "nome": "Apple",
  "valorAcao": 10.56
}
```
**NB: Não pode se cadastrar duas empresas com mesmo nome. Um controle foi implementado para isso**

* Uma vez a empresa cadastrada, cadastramos a nossa conta com a requisição **POST** para **/app/conta**
```
{
  "email": "seuEmmail@mail.com",
  "saldo": 15000
}
```

* Agora temos que cadastrar um monitoramento ou vários para a nossa conta. Para isso precisamos mandar um requisição **POST** para
**/app/monitoramento/{id}**, onde **id** é o id da conta.
```
{
  "empresa": "Apple",
  "precoCompra": 10.60,
  "precoVenda": 10.70
}
```

**NB: Não pode se cadastrar mais um monitoramento referente a uma mesma empresa. 
Pode se cadastrar o monitoramento de uam empresa somente se existe uma empresa com o mesmo nome cadastrada Foi implementado um controle.**

* **INICAR A SIMULAÇÃO** : 
Para iniciar a simulação basta mandar um requisição POST para **/app/bolsa/start/{id}**, onde **id** é o id referente ao cliente desejado.
A aplicação roda e no final imprime um relatório de negociações que pode ser consultado a qualquer momento mandando uma 
requisição **GET** para **/app/bolsa/historico/{id}** onde **id** é o id referente ao cliente desejado.

* Lembrando que a documentação completa dos endpoints está disponível nesse [link.](http://localhost:9000/swagger-ui.html)

# Arquitetura

A aplicação segue o padrão MVC (Model View Controller)
## Model
Sempre que você pensar em manipulação de dados, pense em model. Ele é responsável pela leitura e escrita de dados, e também de suas validações.

## View
Simples: a camada de interação com o usuário. Ela apenas faz a  exibição dos dados, sendo ela por meio de um html ou xml.

## Controller
O responsável por receber todas as requisições do usuário. Seus métodos chamados actions são responsáveis por uma página, controlando qual model usar e qual view será mostrado ao usuário.

Como essa aplicação é somente o Backend, ela não renderisa os dados em página html.

A aplicação tem vários endpoint que estão bem detalhados com o Swagger. A documentação está disponível [aqui.](http://localhost:9000/swagger-ui.html) (A aplicação deve estar rodando quando clicar nesse link).
