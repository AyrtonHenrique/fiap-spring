# Cartões de Crédito – Alunos FIAP

## Introdução

O presente projeto tem o intuito de apresentar a solução adotada para a criação de uma solução que emula uma opção de uma faculdade começar a emitir cartões de crédito para os seus alunos. 

Nesta solução foi construída a estrutura de backend do sistema, exposta através de três microsserviços, cada qual com sua responsabilidade. Um destes  microsserviços se integra a um servidor de e-mail SMTP configurado para envio de email. 

Também foi construído uma aplicação batch utilizando Spring Batch que carrega um arquivo inicial com os clientes em potencial (alunos da faculdade) para a estrutura de dados da aplicação.

Como a solução trata de quatro partes distintas, todas elas devem estar conectadas a um banco de dados comum conforme seus arquivos de configuração para que compartilhem os mesmos dados. Mas não necessariamente há a necessidade de ser a mesma instância, visto que cada microsserviço é responsável pelo tratamento dos seus dados e das suas responsabilidades.

##Estruturação do Projeto no GitHub

Foram criados nesta solução quatro projetos isolados disponíveis no GitHub, sendo: 

a.	ClienteAluno → Microsserviço responsável por gerenciar o CRUD de Clientes/Alunos e dos Cartões que estes possuem ou não
    
    Como configurar:   
      * Para que ele funcione corretamente, é necessário configurar inicialmente o arquivo application.yml presente em: src/main/resources 
         Neste arquivo, devem ser configurados: servidor 
    
    
      Este microsserviço é uma Sprint Boot Aplication. 
      Para iniciá-la, basta dar o start da Spring Application inicial br.com.fiapspring.ClienteAlunoApplication
    
      

b.	Transaction → Microsserviço responsável por gerenciar a recepção e tratativa das transações de cartão de crédito provindas das autorizadoras


c.	batch → Microsserviço responsável por executar o batch de carga do arquivo de Clientes em potencial para dentro do banco de dados da solução


d.	sts-fiap → Serviço que expõe uma API para geração do Token JWT utilizado para receber as transações com segurança

