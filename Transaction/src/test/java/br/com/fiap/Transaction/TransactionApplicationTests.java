package br.com.fiap.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;
import br.com.fiap.Transaction.dto.TransactionCreateDTO;
import br.com.fiap.Transaction.entity.*;
import br.com.fiap.Transaction.mail.MailConnectCreator;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Transaction.repository.TransactionRepository;
import br.com.fiap.Transaction.service.HttpClienteAlunoService;
import br.com.fiap.Transaction.service.HttpClienteAlunoServiceImpl;
import br.com.fiap.Transaction.service.TransactionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class TransactionApplicationTests {

	@Mock
	private HttpClienteAlunoService _httpAluno;

	@Test
	void contextLoads() {

	}

	@Test
	void DeveCriaTransactionCorretamente() throws Exception {

		var transaction = new Transaction();
		transaction.setCliente(1L);
		transaction.setCartao(22L);
		transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		var alunodto = new ClienteAlunoDTO();
		alunodto.setCpf("12345678900");
		alunodto.setDataNascimento(LocalDate.parse("1957-11-12"));
		alunodto.setId(3L);
		alunodto.setIsCliente(true);
		alunodto.setRg("25484582");
		alunodto.setRm("3564402");
		alunodto.setTurma("642-42");

		var cartaoDTO = new CartaoDTO();
		cartaoDTO.setCodigoIdentificador(22L);
		cartaoDTO.setDatavalidade(LocalDate.parse("1957-11-12"));
		cartaoDTO.setId(2L);
		cartaoDTO.setNumerocartao(98989L);

		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.save(any())).thenReturn(transaction);

		when(_httpAluno.getAluno(any())).thenReturn(alunodto);
		when(_httpAluno.getCartao(any(),any())).thenReturn(cartaoDTO);

		var transactionService = new TransactionServiceImpl(transactionMock, _httpAluno);

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
	void NaoDeveCriaTransactionCorretamente() throws Exception {

		var transaction = new Transaction();
		transaction.setCliente(1L);
		transaction.setCartao(22L);
		transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		var alunodto = new ClienteAlunoDTO();
		alunodto.setCpf("12345678900");
		alunodto.setDataNascimento(LocalDate.parse("1957-11-12"));
		alunodto.setId(3L);
		alunodto.setIsCliente(false);
		alunodto.setRg("25484582");
		alunodto.setRm("3564402");
		alunodto.setTurma("642-42");

		var cartaoDTO = new CartaoDTO();
		cartaoDTO.setCodigoIdentificador(22L);
		cartaoDTO.setDatavalidade(LocalDate.parse("1957-11-12"));
		cartaoDTO.setId(2L);
		cartaoDTO.setNumerocartao(98989L);

		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.save(any())).thenReturn(transaction);

		when(_httpAluno.getAluno(any())).thenReturn(alunodto);
		when(_httpAluno.getCartao(any(),any())).thenReturn(cartaoDTO);

		var transactionService = new TransactionServiceImpl(transactionMock, _httpAluno);

		var transactionDto = new TransactionCreateDTO();
		transactionDto.setCliente(1L);
		transactionDto.setCartao(22L);
		transactionDto.setValor(BigDecimal.valueOf(50.00));
		transactionDto.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		assertThrows(ResponseStatusException.class, () -> transactionService.create(transactionDto));

	}

	@Test
	void DeveRetornarByIdTransactionCorretamente() {

		var transaction = new Transaction();
		transaction.setCliente(1L);
		transaction.setCartao(22L);
		transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));
		transaction.setId(1L);

		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.findFirstById(any())).thenReturn(Optional.ofNullable(transaction));

		var transactionService = new TransactionServiceImpl(transactionMock, _httpAluno);

		var transactionDto = new TransactionCreateDTO();
		transactionDto.setCliente(1L);
		transactionDto.setCartao(22L);
		transactionDto.setValor(BigDecimal.valueOf(50.00));
		transactionDto.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		var ret = transactionService.findById(1L);

		assertEquals(transaction.getId(),ret.getId()); 

	}

	@Test
	void DeveRetornarListaTransactionCorretamente() {

		var transaction = new Transaction();
		transaction.setCliente(1L);
		transaction.setCartao(22L);
		transaction.setValor(BigDecimal.valueOf(50.00));
		transaction.setDataTransacao(LocalDateTime.parse("2015-08-04T10:11:30"));

		var lista = new ArrayList<Transaction>();
		lista.add(transaction);
		lista.add(transaction);

		var transactionMock = mock(TransactionRepository.class);
		when(transactionMock.findAll()).thenReturn(lista);

		var transactionService = new TransactionServiceImpl(transactionMock, _httpAluno);

		transactionService.findAll();

		assertEquals(transactionService.findAll().size(), 2);

	}

	@Test
	void DeveObterAlunoCorretamente() throws Exception {
		var env = mock(Environment.class);
		when(env.getProperty(any())).thenReturn("urlll");
		
		var mailCreate = mock(MailConnectCreator.class);

		var alunodto = new ClienteAlunoDTO();
		alunodto.setCpf("12345678900");
		alunodto.setDataNascimento(LocalDate.parse("1957-11-12"));
		alunodto.setId(3L);
		alunodto.setIsCliente(false);
		alunodto.setRg("25484582");
		alunodto.setRm("3564402");
		alunodto.setTurma("642-42");
	
		when(mailCreate.obterAluno(any(), any())).thenReturn(alunodto);

		var httpCliente = new HttpClienteAlunoServiceImpl(env,mailCreate);
		var retorno = httpCliente.getAluno(3L);

		assertEquals(alunodto.getCpf(), retorno.getCpf());


	}

	@Test
	void DeveObterCartaoCorretamente() throws Exception {
		// var env = mock(Environment.class);
		// when(env.getProperty(any())).thenReturn("urlll");
		
		// var mailCreate = mock(MailConnectCreator.class);

		// var alunodto = new CartaoDTO();
		// alunodto.setCpf("12345678900");
		// alunodto.setDataNascimento(LocalDate.parse("1957-11-12"));
		// alunodto.setId(3L);
		// alunodto.setIsCliente(false);
		// alunodto.setRg("25484582");
		// alunodto.setRm("3564402");
		// alunodto.setTurma("642-42");
	
		// when(mailCreate.obterAluno(any(), any())).thenReturn(alunodto);

		// var httpCliente = new HttpClienteAlunoServiceImpl(env,mailCreate);
		// var retorno = httpCliente.getAluno(3L);

		// assertEquals(alunodto.getCpf(), retorno.getCpf());


	}

}
