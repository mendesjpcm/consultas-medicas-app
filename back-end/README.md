# Consultas Médicas Web Service API
Projeto contendo um Spring API RESTful para criar consultas médicas


# Pré-requisitos

-Java 8

-PostgreSQL

-Apache Maven

-Ambiente de desenvolvimento LINUX Ubuntu 18.04 (Opcional)

-IntelliJ IDEA (Opcional)

## Instalação dos pré-requisitos

As instruções seguintes são necessárias para configurar o ambiente e rodar este projeto
localmente.

### Java 8

Executar os seguintes comandos no terminal:

```
	sudo add-apt-repository ppa:openjdk-r/ppa
	sudo apt-get update
	sudo apt-get install openjdk-8-jdk
```
Setar o JAVA_HOME na variável de ambiente

### PostgreSQL

Executar os seguintes comandos no terminal:

	$ sudo apt-get install postgresql

Modificar senha para o usuário postgres:

	$ sudo -u postgres psql postgres
	
	postgres=# \password postgres
	Enter new password: 
	Enter it again: 

### Apache Maven

Executar os seguintes comandos no terminal:

	$ sudo apt update
	$ sudo apt install maven
	$ mvn -version

# Configurações do projeto

## Configurar login e senha do banco de dados PostgreSQL
Modificar as constantes spring.datasource.username e spring.datasource.password correspondentes, respectivamente, ao login e senha do PostgreSQL, que se encontram no arquivo application.properties cujo diretório é src->main->resources

## Criar base de dados no PostgreSQL
Criar base de dados, no PostgreSQL, com nome 'medical_appointment'

## Configurar plugin do Lombok no Intellij IDEA (Caso necessário)
Se a IDE utilizada for o IntelliJ, e o mesmo apresentar problemas com java (get e set  das entidades), será necessário baixar o plugin do Lombok em File ->  Settings -> plugins -> Marketplace -> ...procurar por Lombok e instalar.

# Executar projeto

## Intellij
Executar (Ctrl + Shift + F10) arquivo InvoicesApplication localizado em src -> main -> java -> com -> exampla -> medicalappointments

## Maven
Pelo terminal, ir no diretório raiz do projeto e executar o comando:

	$ mvn spring-boot:run

# Projeto executando

# ----> localhost:8081/api/v1

# Swagger UI ----> http://localhost:8081/api/v1/swagger-ui.html#/


