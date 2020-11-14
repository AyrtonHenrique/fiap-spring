# Cartões de Crédito – Alunos FIAP

## Introdução

O presente projeto tem o intuito de apresentar a solução adotada para a criação de uma solução que emula uma opção de uma faculdade começar a emitir cartões de crédito para os seus alunos. 

Nesta solução foi construída a estrutura de backend do sistema, exposta através de três microsserviços, cada qual com sua responsabilidade. Um destes  microsserviços se integra a um servidor de e-mail SMTP configurado para envio de email. 

Também foi construído uma aplicação batch utilizando Spring Batch que carrega um arquivo inicial com os clientes em potencial (alunos da faculdade) para a estrutura de dados da aplicação.

Como a solução trata de quatro partes distintas, todas elas devem estar conectadas a um banco de dados comum conforme seus arquivos de configuração para que compartilhem os mesmos dados. Mas não necessariamente há a necessidade de ser a mesma instância, visto que cada microsserviço é responsável pelo tratamento dos seus dados e das suas responsabilidades.

## Estruturação do Projeto no GitHub

Foram criados nesta solução quatro projetos isolados disponíveis no GitHub, sendo: 

a.	ClienteAluno → Microsserviço responsável por gerenciar o CRUD de Clientes/Alunos e dos Cartões que estes possuem ou não
    
    Como configurar:   
      * Para que ele funcione corretamente, é necessário configurar inicialmente o arquivo application.yml presente em: src/main/resources 
         Neste arquivo, devem ser configurados: 
            Porta do Servidor Web, context-path da aplicação, string de conexão do banco de dados H2 e usuário e senha do mesmo
      * Exemplo do arquivo: 
      
        #Server properties
        server:
          port: 8088
          servlet:
            context-path: /clientealuno-app
        #Swagger Properties
        springfox:
          documentation:
            swagger-ui:
              enabled: true
        #SGBD Properties
        spring:
          datasource:
            url: jdbc:h2:D:\fiapclientealuno;DB_CLOSE_ON_EXIT=FALSE
            username: fiap
            password: fiap
          jpa:
            show-sql: true
          h2:
            console:
              enabled: true 
    
      Este microsserviço é uma Sprint Boot Aplication. Para iniciá-la, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial br.com.fiapspring.ClienteAlunoApplication e acompanhar os logs.   
      

b.	Transaction → Microsserviço responsável por gerenciar a recepção e tratativa das transações de cartão de crédito provindas das autorizadoras

    Como configurar:   
      * Para que ele funcione corretamente, é necessário configurar inicialmente o arquivo application.yml presente em: src/main/resources 
         Neste arquivo, devem ser configurados: 
            Porta do Servidor Web, context-path da aplicação, string de conexão do banco de dados H2 e usuário e senha do mesmo, configuracoes do servidor de email SMTP
      * Exemplo do arquivo:
      
        #Server properties
        server:
          port: 8089
          servlet:
            context-path: /transactions-app
        #Swagger properties
        springfox:
          documentation:
            swagger-ui:
              enabled: true
        #SGBD e Mail properties
        spring:
          datasource:
            url: jdbc:h2:C:/dev/db/dbbatchspringfinal;DB_CLOSE_ON_EXIT=FALSE
          jpa:
            database-platform: org.hibernate.dialect.H2Dialect
          mail:
            host: smtp.gmail.com
            port: 587
            username: 
            password: 
            protocol: smtp
            properties:
              mail:
                to: 
              smtp:
                auth: true
                connectiontimeout: 5000
                timeout: 5000
                writetimeout: 5000
                starttls:
                  enable: true

c.	batch → Microsserviço responsável por executar o batch de carga do arquivo de Clientes em potencial para dentro do banco de dados da solução


d.	sts-fiap → Serviço que expõe uma API para geração do Token JWT utilizado para receber as transações com segurança

