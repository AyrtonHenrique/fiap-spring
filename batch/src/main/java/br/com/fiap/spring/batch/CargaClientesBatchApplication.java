package br.com.fiap.spring.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import br.com.fiap.spring.batch.model.ClientePotencial;
import br.com.fiap.spring.batch.skipping.CustomSkipPolicy;

@SpringBootApplication
@EnableBatchProcessing
public class CargaClientesBatchApplication {

	Logger logger = LoggerFactory.getLogger(CargaClientesBatchApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CargaClientesBatchApplication.class, args);
	}

	/**
	 * Método de configuração da leitura do arquivo flat configurado
	 * @param Objeto Resource correspondente ao arquivo Flat a ser lido
	 * @return Um FlatFileItemReader pra ser tratado pelo Job
	 */
	@Bean
	public FlatFileItemReader<ClientePotencial> fileReader(@Value("${input.file}") Resource resource) {
		logger.info("Criando o FlatFileItemReader...");
		return new FlatFileItemReaderBuilder<ClientePotencial>()
					.name("Leitura de Arquivo - Clientes Potenciais") // Nome do FlatFileItemReader
					.resource(resource)								  // Resource de origem					  
					.targetType(ClientePotencial.class)				  // Classe de destino dos dados do arquivo
					.addComment("-")								  // Linhas para serem ignoradas no arquivo
					.fixedLength() 									  // Arquivo de tamanho de colunas fixo 
						.columns(new Range(1,41),					  // Coluna 1 do arquivo
								new Range(42,48),					  // Coluna 2 do arquivo
								new Range(50,55))				      // Coluna 3 do arquivo
					.names("nome", "rm","turma")				  // Nomes das colunas do arquivo
					.build();
	}
	
	/**
	 * Item que processará linha a linha do arquivo de entrada
	 * @return O item Processor com cada ocorrencia de ClientePotencial ajustada à regra
	 */
    @Bean
    public ItemProcessor<ClientePotencial, ClientePotencial> itemProcessor(){
		logger.info("Criando o ItemProcessor...");
        return (clientePotencial) -> {
            logger.info(clientePotencial.getNome() + " processado.");
            clientePotencial.setNome(clientePotencial.getNome().trim());
        	clientePotencial.setRm(clientePotencial.getRm().trim());
        	clientePotencial.setTurma(clientePotencial.getTurma().trim());
            	
            return clientePotencial;
        };
    }
    
    /**
     * Método responsável por gravar linha a linha do arquivo lida no banco de dados. 
     * Aqui a estratégia é enviar um INSERT diretamente no SGBD  via JDBC para fins de performance
     * @param datasource O datasource de conexão com o banco de dados
     * @return Um JdbcBatchItemWriter responsável por gravar 
     */
    @Bean
    public JdbcBatchItemWriter<ClientePotencial> databaseWriter(DataSource datasource) {
		logger.info("Criando o ItemWriter...");
	    return new JdbcBatchItemWriterBuilder<ClientePotencial>()
		    .dataSource(datasource)
		    .sql("insert into ClienteAluno (rm, nome, cpf, rg, turma, dataNascimento) values (:rm, :nome, null, null, :turma, null)")
		    .beanMapped()
		    .build();
    }

    /**
     * Step responsável por processar efetivamente o arquivo de entrada de clientes potenciais
     * @param stepBuilderFactory StepBuilderFactory injetado pelo Spring
     * @param itemReader ItemReader injetado pelo Spring
     * @param itemProcessor ItemProcessor injetado pelo Spring
     * @param itemWriter ItemWriter injetado pelo Spring
     * @return Um Step a ser processado pelo Job
     */
    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<ClientePotencial> itemReader,
                     ItemProcessor<ClientePotencial, ClientePotencial> itemProcessor,
                     ItemWriter<ClientePotencial> itemWriter){
		logger.info("Recuperando STEP: Step Chunk - Processamento do arquivo de Clientes Potenciais");
        return stepBuilderFactory.get("Step Chunk - Processamento do arquivo de Clientes Potenciais")
                .<ClientePotencial, ClientePotencial>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipPolicy(new CustomSkipPolicy())
                .allowStartIfComplete(true)
                .build();
    }
	
    /**
     * Job a ser invocado pelo Spring Batch para processar o arquivo de entrada de clientes potenciais
     * @param jobBuilderFactory Um JobBuilderFactory injetado pelo Spring Batch
     * @param stepProcessamentoArquivoDeClientesPotenciais O Step injetado pelo Spring Batch
     * @return Um objeto Job a ser iniciado pelo Spring Batch
     */
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step stepProcessamentoArquivoDeClientesPotenciais){    	
        return jobBuilderFactory.get("Job - Processar arquivo de Clientes Potenciais")
                .start(stepProcessamentoArquivoDeClientesPotenciais)
                .build();
    }
    
}
