# Cartões de Crédito – Alunos FIAP

## Introdução

O presente projeto tem o intuito de apresentar a solução adotada para a criação de uma solução que emula uma opção de uma faculdade começar a emitir cartões de crédito para os seus alunos. 

Nesta solução foi construída a estrutura de backend do sistema, exposta através de três microsserviços, cada qual com sua responsabilidade. Um destes  microsserviços se integra a um servidor de e-mail SMTP configurado para envio de email. 

Também foi construído uma aplicação batch utilizando Spring Batch que carrega um arquivo inicial com os clientes em potencial (alunos da faculdade) para a estrutura de dados da aplicação.

Como a solução trata de quatro partes distintas, todas elas devem estar conectadas a um banco de dados comum conforme seus arquivos de configuração para que compartilhem os mesmos dados. Mas não necessariamente há a necessidade de ser a mesma instância, visto que cada microsserviço é responsável pelo tratamento dos seus dados e das suas responsabilidades.

## Desenho Básico da Solução

![trabalho_final_spring](https://user-images.githubusercontent.com/67294168/99154992-f9070680-2692-11eb-8cd8-bb225f1b4e1d.png)

## Estruturação do Projeto no GitHub

Foram criados nesta solução quatro projetos isolados disponíveis no GitHub, sendo: 

a.	**ClienteAluno** → Microsserviço responsável por gerenciar o CRUD de Clientes/Alunos e dos Cartões que estes possuem ou não
    
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
    
Este microsserviço é uma Sprint Boot Aplication. Para iniciá-la, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiapspring.ClienteAlunoApplication e acompanhar**_ os logs.   
      

b.	**Transaction** → Microsserviço responsável por gerenciar a recepção e tratativa das transações de cartão de crédito provindas das autorizadoras

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

Este microsserviço é uma Sprint Boot Aplication. Para iniciá-la, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiap.Transaction.TransactionApplication**_ e acompanhar os logs. 

c.	**batch** → Microsserviço responsável por executar o batch de carga do arquivo de Clientes em potencial para dentro do banco de dados da solução

    Este microsserviço é uma Spring Batch Application. Sua função é executar uma carga de um arquivo de entrada com os clientes fictícios em potencial respeitando um layout posicional e incluí-los no banco de dados. 
    
    Este serviço pressupõe que haja uma tabela criada. Esta tabela deverá será descrita abaixo e é responsável por conter o Alunos/Clientes tratados pela solução em geral.

    Como configurar: 
      * Para que o Batch funcione, é necessário configurar inicialmente o arquivo application.yml presente em: src/main/resources.
        Neste arquivo, deverão ser definidos: string de conexão com o banco de dados H2 a ser carregado e o caminho do arquivo de entrada para carga. 
      
      * Layout do arquivo de entrada: 
      O arquivo de entrada possui o seguinte layout (nomes fictícios): 
      
      ---------------------------A---------------------------

      AARON FELIPE GRASSMANN                   3095564 100-11

      AARON PAPA DE MORAIS                     8610833 160-26

      ABNER GALLILEI MOREIRA BORGES            1494778 500-35
      
      ---------------------------B---------------------------

      BARBARA ANDRADE DE ROSSI                 6419200 235-55

      BARBARA ARAUJO DE CARVALHO               1623461 780-40
      
      <Continua...>

Este microsserviço é uma Sprint Batch Aplication. Para iniciá-la, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiap.spring.batch.CargaClientesBatchApplication**_ e acompanhar os logs.

   Para cada carga carregada com sucesso do arquivo, as linhas abaixo são impressas no Log: 
    
            INFO 32416 --- [main] b.c.f.s.b.CargaClientesBatchApplication  : Started CargaClientesBatchApplication in 1.503 seconds (JVM running for 1.819)
            INFO 32416 --- [main] o.s.b.a.b.JobLauncherApplicationRunner   : Running default command line with: []
            INFO 32416 --- [main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=Job - Processar arquivo de Clientes Potenciais]] launched with the following parameters: [{}]
            INFO 32416 --- [main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [Step Chunk - Processamento do arquivo de Clientes Potenciais]
            INFO 32416 --- [main] b.c.f.s.b.CargaClientesBatchApplication  : AARON FELIPE GRASSMANN processado.
            INFO 32416 --- [main] b.c.f.s.b.CargaClientesBatchApplication  : AARON PAPA DE MORAIS processado.
            INFO 32416 --- [main] b.c.f.s.b.CargaClientesBatchApplication  : ABNER GALLILEI MOREIRA BORGES processado.
            INFO 32416 --- [main] o.s.batch.core.step.AbstractStep         : Step: [Step Chunk - Processamento do arquivo de Clientes Potenciais] executed in 31ms
            INFO 32416 --- [main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=Job - Processar arquivo de Clientes Potenciais]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 50ms

d.	**sts-fiap** → Serviço que expõe uma API para geração do Token JWT utilizado para receber as transações com segurança

## Bancos de Dados da Aplicação

A solução criada se utiliza do banco de dados H2 para fins didáticos. Entretanto, qualquer banco relacional pode ser utilizado, considerando-se a adição das dependências das  bibliotecas respectivas dentro do arquivo "build.gradle" de cada projeto.

### Abaixo está o modelo básico das tabelas utilizadas (MER)

![MER_Spring](https://user-images.githubusercontent.com/67294168/99155690-ec85ac80-2698-11eb-8a76-78add5ba2b67.png)


