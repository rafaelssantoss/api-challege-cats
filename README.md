# API busca de raças de gatos

## Introdução

API para buscar informações de raças de gatos, com integração [Cat as a Service](https://thecatapi.com/).

## 🛠️ Arquitetura

O projeto foi desenvolvido em DDD (Domain-Driven Design), com a seguinte organização de pacotes:

````
api
    |____________ pacote de entrypoint do microservice, controllers e exception controllers
    
configuration
    |____________ pacote de configuração do projeto
    
domain
    |____________ pacote de dominios de entrada e saída do microservice
    
exception
    |____________ pacote de execeções
    
repostory
    |____________ pacote de conexão com a base de dados e suas entidades
    
services
    |____________ pacote de serviço onde está as regras de negócio
````

Na pasta `contrib` está a collection Postman do projeto, enquanto os Dockerfile e docker-compose estão na pasta `docker`.

O microservice usa como banco de dados `Postgres` e para criação de schema/tabelas é usado a migration `Flyway`,
sempre será executado ao executar o serviço.

## 🔧 Instalando local

Antes de tudo, é necessário que a máquina tenha instalado:
* Docker
* Make
* Java 11 ou superior
* Maven

Para executar o projeto localmente, é necessário que a máquina tenha o `make`, caso ao contrário precisará 
olhar o arquivo `Makefile` na raíz do projeto e executar os comandos na sequencia.

### Execução dos serviços:
* Executar
````
make up
````
* Parar
````
make stop
````
* Limpar base e containers
````
make clean
````

Caso precise executar o serviço em alguma IDEA, opte por fazer a execução simplificada do `make` para o banco de dados ficar disponível,
dessa forma a aplicação pode ser executada na IDEA sem problemas.


Serviço **ON**, é necessário efetuar uma carga na base com as informações das raças de gatos, para isso basta executar
um endpoint de carga que está na collection, `PUT - Update database`.

## 🔩 Splunk
Caso consiga executar o projeto com todas as imagens, o splunk pode ser acessado atráves do [link](http://localhost:8000).
Segue usuário e senha:
````
USERNAME=admin
PASSWORD=Splunk@123456
````

## ⌨ Testes Unitários
Os testes unitários foi desenvolvido em `JUnit`, e será executado sempre no build.
O projeto contém o plugin do `Jacoco`, com isso é possível visualizar o report de cobertura de testes através de um arquivo HTML.

O Arquivo HTML é gerado sempre na conclusão do build. Como o projeto usa `Maven`, o caminho do report é `target/site/jacoco/index.html`:
<image src="contrib/images/report3.PNG" alt="Localtion">

Ao abrir o arquivo `index.html` no navegador de sua preferência, essa é a visão geral de cobertura do projeto, que no exemplo abaixo 
está em `85%` de código:
<image src="contrib/images/report1.PNG" alt="General Report">

Navegando nas classes, é possivel visualizar as linhas que foram cobertas totalmente, parcialmente e não cobertas:
<image src="contrib/images/report2.PNG" alt="Class Report">