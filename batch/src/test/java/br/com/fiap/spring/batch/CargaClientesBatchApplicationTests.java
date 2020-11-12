package br.com.fiap.spring.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CargaClientesBatchApplicationTests {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private DataSource datasource;

	/**
	 * Método que limpa as tabelas antes de executar o teste
	 * @throws SQLException Algum erro ao limpar a tabela
	 */
	@Before
	void preparaAmbiente() throws SQLException {
		datasource.getConnection().prepareStatement("DELETE FROM TB_CLIENTE_ALUNO").executeQuery();
		ResultSet resultSet = datasource.getConnection()
				.prepareStatement("select count(*) from TB_CLIENTEALUNO")
				.executeQuery();
		resultSet.last();
		Assertions.assertEquals(0, resultSet.getInt(1));
	}
	
	/**
	 * Método que testa a execução do Batch. Atentar para o arquivo de entrada e para o Assert esperado na carga dos registros.
	 * No diretorio /resources há o arquivo de entrada definitivo e um arquivo de entrada com apenas 3 registros para testes
	 * @throws JobParametersInvalidException
	 * @throws JobExecutionAlreadyRunningException
	 * @throws JobRestartException
	 * @throws JobInstanceAlreadyCompleteException
	 * @throws SQLException
	 */
	@Test
	void carregaClientesEmPotencialNoBanco() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, SQLException {
	
		ResultSet resultSet = datasource.getConnection()
				.prepareStatement("select count(*) from TB_CLIENTEALUNO")
				.executeQuery();
		
		resultSet.last();
		Assertions.assertEquals(3, resultSet.getInt(1));
	}
}