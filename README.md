# Cartões de Crédito – Alunos FIAP

## Introdução

O presente projeto tem o intuito de apresentar a solução adotada para a criação de uma solução que emula uma opção de uma faculdade começar a emitir cartões de crédito para os seus alunos. 

Nesta solução foi construída a estrutura de backend do sistema, exposta através de quatro microsserviços, cada qual com sua responsabilidade. 
Dois destes microsserviços tem funções de CRUD. Um outro se integra a um servidor de e-mail SMTP configurado para envio de emails vinculados a regras de negócio. 

Também foi construída uma aplicação Batch utilizando Spring Batch que carrega um arquivo inicial previamente definido com os clientes em potencial (alunos da faculdade) para uma das estruturas de dados da aplicação.

Como a solução trata de quatro partes distintas, todas elas devem estar conectadas a bancos de dados em comum conforme seus arquivos de configuração para que compartilhem os mesmos dados. Inicialmente dois dos microsserviços foram concebidos para terem um banco de dados apartado, isolando suas regras e execuções. 
Não necessariamente há a necessidade de se utlizar uma mesma instância, visto que cada microsserviço é responsável pelo tratamento dos seus dados e das suas responsabilidades.

## Desenho Básico da Solução

![estrutura_final_spring](https://user-images.githubusercontent.com/67294168/99196957-2a083980-276e-11eb-835c-9e216f21b433.png)

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
    
Este microsserviço é uma Sprint Boot Aplication. Para iniciá-la antes iniciar o projeto **sts-fiap**, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiapspring.ClienteAlunoApplication**_ e acompanhar os logs.   

Tabelas criadas por este microsserviço: **tb_clientealuno, tb_cartao, tb_clientealunoendereco, tb_clientealuno_cliente_aluno_enderecos**


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

Este microsserviço é uma Sprint Boot Aplication. Para iniciá-la antes iniciar projeto **sts-fiap**, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiap.Transaction.TransactionApplication**_ e acompanhar os logs. 

Tabela criada por este microsserviço: **tb_transaction**

c.	**batch** → Microsserviço responsável por executar o batch de carga do arquivo de Clientes em potencial para dentro do banco de dados da solução

Este microsserviço é uma Spring Batch Application. Sua função é executar uma carga de um arquivo de entrada com os clientes fictícios em potencial respeitando um layout posicional e incluí-los no banco de dados. 
    
Este serviço pressupõe que haja uma tabela já criada pelo projeto ClienteAluno. Para isso, é necessário que as estruturas de dados previstas no módulo ClienteAluno já estejam criadas. 

Esta tabela está descrita no MER mais abaixo e é responsável por conter o Alunos/Clientes tratados pela solução de maneira geral. A tabela criada deve ser chamada tb_clientealuno.

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

Este microsserviço é uma Sprint Batch Aplication. Para iniciá-la antes inciar o app _**ClienteAluno**_, após configurar o arquivo de propriedades corretamente, basta dar o start da Spring Application inicial _**br.com.fiap.spring.batch.CargaClientesBatchApplication**_ e acompanhar os logs.

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

Tabela carregada por este microsserviço batch: **tb_clientealuno**

d.	**sts-fiap** → Serviço que expõe uma API para geração do Token JWT utilizado para receber as transações com segurança e também inclui usuários válidos para poder gerar transações com tokens válidos.

Todos os clientes possuem seu usuario como cpf e a senha os 4 primeiros digitos do CPF.

    Como configurar:   
          * Para que ele funcione corretamente, é necessário configurar inicialmente o arquivo application.yml presente em: src/main/resources 
             Neste arquivo, devem ser configurados: 
                Porta do Servidor Web, context-path da aplicação, string de conexão do banco de dados H2 e usuário e senha do mesmo, segredo e tempo de expiração o Jason Web Token. 
          * Exemplo do arquivo:
            spring:
              datasource:
                url: jdbc:h2:mem:userdb;DB_CLOSE_ON_EXIT=FALSE
                username: 
                password: 
              h2:
                console:
                  enabled: true
                  path: "/h2" 
            server:
              port: 8082
            jwt:
              secret: 
              expiration: 5
            springfox:
              documentation:
                swagger-ui:
                  enabled: true

Tabela criada por este microsserviço: **tb_usuario**

## Bancos de Dados da Aplicação

A solução criada se utiliza do banco de dados H2 para fins didáticos. 
Entretanto, qualquer banco relacional pode ser utilizado, considerando-se a adição das dependências das  bibliotecas respectivas dentro do arquivo "build.gradle" de cada projeto.

### Abaixo está o modelo básico das tabelas utilizadas (MER)

Todas as estruturas e relacionamentos abaixo descritos são criados automaticamente ao serem iniciados os projetos ClienteAluno, sts-fiap e Transaction.

![mer_final_spring](https://user-images.githubusercontent.com/67294168/99196961-2d032a00-276e-11eb-9177-c66d6cc1a4e3.png)


## Swagger - Documentação e Testes

Os projetos Spring Boot Application descritos acima, após iniciados e rodando cada qual em uma porta distinta, expõem as suas interfaces através da biblioteca Swagger.
Com os mesmos já iniciados, basta adicionar a terminação da URL do Swagger conforme exemplo abaixo para cada Web API exposta: 

http://<host>:<porta>/{context-path}/swagger-ui/
    
Com isso, uma interface de documentação e testes é exposta para cada Web API, conforme imagem abaixo: 

![swagger](https://user-images.githubusercontent.com/67294168/99307344-1d024d80-2835-11eb-8c98-692ce5f338e0.png)

## Interfaces dos Endpoints com os JSONs para testes

1)	Pré-requisitos
    Para que a aplicação funcione corretamente, as aplicações devem estar iniciadas e o banco de dados H2 configurado. 
    A ordem de execução das aplicação é: 
    1.	ClienteAluno
    2.	Sts-fiap
    3.	Transaction
    4.	Batch

2)	Simulação de Uso dos Endpoints
    a)	Cadastrando um Cliente/Aluno (POST)
    
    /cliente

        Body da Requisição
        { 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "false" 
        } 

        Retorno do Cliente/Aluno criado: 
        { 
        "id":3, 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "false"
        } 

    b)	Atualizando um Cliente/Aluno (PUT)
    
    /cliente/{idCliente}

        Body da Requisição
        { 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "false" 
        } 
        
        Retorno do Cliente/Aluno criado: 
        { 
            "id":3, 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "false"
        } 
 
    c)	Apagando um Cliente/Aluno (DELETE)

    /cliente/{idCliente}
    Retorno: 200 OK 

    d)	Transformando um Aluno em Cliente/Aluno (PUT)
    
    /cliente/{idCliente}/ativar

        Retorno do Cliente/Aluno criado: 
        { 
            "id":3, 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "true"
        } 
 
    e)	Buscando Cliente/Aluno por ID (GET)

    /cliente/{id}

        Retorno de todos os Clientes/Aluno: 
        { 
            "id":2, 
            "rm":654321, 
            "nome": "199.33", 
            "turma":"600-25", 
            "cpf": "12345678900", 
            "rg":"", 
            "dataNascimento": "06/10/1988", 
            "isCLiente": "true"
        } 
 
    f)	Buscando todos os Clientes/Aluno cadastrados (GET)

    /cliente/listarTodos

        Retorno de todos os Clientes/Aluno: 
        [{ 
            "id":2, 
            "rm":654321, 
            "nome": "199.33", 
            "turma":"600-25", 
            "cpf": "12345678900", 
            "rg":"", 
            "dataNascimento": "06/10/1988", 
            "isCLiente": "true"
        },
        { 
            "id":3, 
            "rm":123456, 
            "nome": "304.33", 
            "turma":"500-35", 
            "cpf": "", 
            "rg":"", 
            "dataNascimento": "", 
            "isCLiente": "true"
        }] 
 
    g)	Atualizando um endereço de um Cliente/Aluno (PUT) 

    /endereço/{id}

        Body da Requisição
        { 
            "id":33, 
            "cep":"04337-090", 
            "cidade": "São Paulo", 
            "complemento":"CASA 4", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA MELO NUNES", 
            "numero": "110",
            "tipoEndereco": "ENTREGA" 
        } 
 
        Retorno do Endereço atualizado: 
        { 
            "id":33, 
            "cep":"04337-120", 
            "cidade": "São Paulo", 
            "complemento":"CASA 2", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA JORGE RUBENS", 
            "numero": "511",
            "tipoEndereco": "ENTREGA" 
        } 
 
    h)	Cadastrando um Endereço para um Cliente/Aluno (POST)
    
    /endereco

        Body da Requisição:
        { 
            "cep":"04337-120", 
            "cidade": "São Paulo", 
            "complemento":"CASA 2", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA MELO NUNES", 
            "numero": "110",
            "tipoEndereco": "ENTREGA" 
        } 
 
        Retorno do Endereço criado: 
        { 
        "id":33, 
            "cep":"04337-120", 
            "cidade": "São Paulo", 
            "complemento":"CASA 2", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA MELO NUNES", 
            "numero": "110",
            "tipoEndereco": "ENTREGA" 
        } 
 
    i)	Apagando um Endereço (DELETE)
    
    /endereco/{id}
    Retorno: 200 OK 

    j)	Retornando todos os endereços de um Cliente/Aluno (GET)
    
    /endereco/{idCliente}

        Retorno dos Endereços do Aluno: 
        [{ 
        "id":33, 
            "cep":"04337-120", 
            "cidade": "São Paulo", 
            "complemento":"CASA 2", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA MELO NUNES", 
            "numero": "110",
            "tipoEndereco": "ENTREGA" 
        },{ 
        "id":34, 
            "cep":"04337-090", 
            "cidade": "São Paulo", 
            "complemento":"CASA 1", 
            "estado": "SP", 
            "idCliente":"2", 
            "logradouro": "RUA JORGE RUBENS", 
            "numero": "511",
            "tipoEndereco": "ENTREGA" 
        }] 

    k)	Criando um cartão para um Cliente/Aluno (POST)
    
    /cartao

        Body da Requisição:
        { 
            "codigoIdentificador":"123", 
            "dataValidade": "2020-11-16 ", 
            "idCliente":"2", 
            "numeroCartao": "1234654954874587" 
        } 
 
        Retorno do Cartão criado: 
        { 
        "id":303, 
        "codigoIdentificador":"123", 
            "dataValidade": "2020-11-16 ", 
            "idCliente":"2", 
            "numeroCartao": "1234654954874587" 
        } 
 
    l)	Atualizando os dados de um Cliente/Aluno (PUT)
    
    /cartão/{idcartao}

        Body da Requisição:
        { 
            "codigoIdentificador":"123", 
            "dataValidade": "2020-11-16 ", 
            "idCliente":"2", 
            "numeroCartao": "1234654954874587" 
        } 
 
        Retorno do Cartão atualizado: 
        { 
        "id":303, 
        "codigoIdentificador":"123", 
            "dataValidade": "2020-11-16 ", 
            "idCliente":"2", 
            "numeroCartao": "1234654954874587" 
        } 
 
    m)	Recuperando Cartões de um Cliente/Aluno (GET) 
    
    /cartão/{idCliente}

        Retorno dos Cartões do Cliente: 
        [{ 
        "id":303, 
        "codigoIdentificador":"123", 
            "dataValidade": "2020-11-16 ", 
            "idCliente":"2", 
            "numeroCartao": "1234654954874587" 
        }] 


