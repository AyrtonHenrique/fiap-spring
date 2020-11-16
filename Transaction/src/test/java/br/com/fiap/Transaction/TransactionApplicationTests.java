package br.com.fiap.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.Transaction.dto.TransactionCreateDTO;
import br.com.fiap.Transaction.entity.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import br.com.fiap.Transaction.repository.TransactionRepository;
import br.com.fiap.Transaction.service.HttpClienteAlunoService;
import br.com.fiap.Transaction.service.HttpClienteAlunoServiceImpl;
import br.com.fiap.Transaction.service.TransactionServiceImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TransactionApplicationTests {

	@Mock
	private HttpClienteAlunoService _httpAluno;

	@InjectMocks
	private HttpClienteAlunoServiceImpl _httpCliente;
	@Test
	void contextLoads() {

	}

	@Test
	void DeveCriaTransactionCorretamente (){
		
		var transaction  = new Transaction();
        transaction.setCliente(1L);
        transaction.setCartao(22L);
        transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));
		
		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.save(any())).thenReturn(transaction);
		 
		var transactionService = new TransactionServiceImpl(transactionMock,_httpAluno);

		var transactionDto = new TransactionCreateDTO();
		transactionDto.setCliente(1L);
        transactionDto.setCartao(22L);
        transactionDto.setValor(BigDecimal.valueOf(50.00));
		transactionDto.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		transactionService.create(transactionDto);

		assertEquals(transactionService.create(transactionDto).getDataTransacao(), transaction.getDataTransacao());
		assertEquals(transactionService.create(transactionDto).getCliente(), transaction.getCliente());
		assertEquals(transactionService.create(transactionDto).getId(), transaction.getId());

	}

	@Test
	void DeveRetornarListaTransactionCorretamente (){
		
		var transaction  = new Transaction();
        transaction.setCliente(1L);
        transaction.setCartao(22L);
        transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		var lista =  new ArrayList<Transaction>();
		lista.add(transaction);
		lista.add(transaction);

		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.findAll()).thenReturn(lista);
		 
		var transactionService = new TransactionServiceImpl(transactionMock,_httpAluno);

		transactionService.findAll();

		assertEquals(transactionService.findAll().size(), 2);

	}

	@Test
	void DeveObterAlunoCorretamente(){
		var env = mock(Environment.class);
		when(env.getProperty(any())).thenReturn("urlll");
		
		// var httpCliente = new HttpClienteAlunoServiceImpl(env);


	}

}
