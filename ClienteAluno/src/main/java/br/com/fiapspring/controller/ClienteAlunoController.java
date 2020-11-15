package br.com.fiapspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.entity.ClienteAlunoEndereco;
import br.com.fiapspring.service.CartaoService;
import br.com.fiapspring.service.ClienteAlunoService;

/**
 * @author SaraRegina
 * Classe responsaval para cadastros do aluno
 */
@RestController
@RequestMapping("clientealuno")
public class ClienteAlunoController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);
	private final ClienteAlunoService clienteAlunoService;
	private final CartaoService cartaoService;
	
	public ClienteAlunoController(ClienteAlunoService clienteAlunoService, CartaoService cartaoService) {
		this.clienteAlunoService = clienteAlunoService;
		this.cartaoService = cartaoService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody ClienteAluno clienteAluno, UriComponentsBuilder builder){
		
		HttpHeaders headers = new HttpHeaders();
		try {
			if (clienteAluno.getClienteAlunoEnderecos().size() == 0) {
				logger.info("Cliente/Aluno deve possuir um endere�o.");
					return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
			} else {
				List<ClienteAlunoEndereco> novaLista = new ArrayList<ClienteAlunoEndereco>();
				// Limpa os endere�os pra depois adicionar
				for (ClienteAlunoEndereco enderecoCliente : clienteAluno.getClienteAlunoEnderecos()) {
					novaLista.add(enderecoCliente);
				}
				clienteAluno.getClienteAlunoEnderecos().clear();
				
				// Adiciona o cliente e pega o cliente Managed
				ClienteAluno novoCliente = clienteAlunoService.create(clienteAluno);
				for (ClienteAlunoEndereco enderecoCliente : novaLista) {
					enderecoCliente.setClienteAluno(novoCliente);
					novoCliente.addClienteAlunoEndereco(enderecoCliente);
				}

				//Salva o cliente com a sua lista de endere�os dentro
				clienteAlunoService.create(novoCliente);
				
				headers.setLocation(builder.path("/clientealuno/{id}").buildAndExpand(novoCliente.getId()).toUri());
					return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("{id}/updateDadosClienteAluno")
	public ResponseEntity<Void> upadateClienteAluno(@PathVariable String id,@RequestBody ClienteAluno clienteAluno){
		ClienteAluno cliAluno = new ClienteAluno();
		cliAluno = clienteAlunoService.getClienteAlunoByRm(Integer.valueOf(id));
		if (cliAluno == null) {
			logger.error("Dados do RM do aluno n�o encontrados");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			//atualizar os dados cliente com os dados recebidos
			logger.info("Atualizando dados aluno");
			clienteAlunoService.update(cliAluno.getRm(), clienteAluno);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PutMapping("{rm}/ativarDesativarClienteAluno")
	public ResponseEntity<Void> ativarClienteAluno(@PathVariable String rm,@RequestBody ClienteAluno clienteAluno){
			logger.info("Ativar os dados do Aluno para cliente");
			clienteAlunoService.update(Integer.valueOf(rm), clienteAluno);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		 
	@DeleteMapping("{id}/removerCliente")
	public ResponseEntity<Void> removerClienteAluno(@PathVariable Long id) {
			HttpHeaders headers = new HttpHeaders();
			logger.info("Consultado dados do Cliente para remover");
			ClienteAluno dadosClienteAluno = clienteAlunoService.findById(id).get();
			 if (dadosClienteAluno == null ) {
				 logger.error("Dados do cliente n�o localizados para remocao");
				return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
			 } else {
				cartaoService.delete(id);
				return new ResponseEntity<Void>(headers, HttpStatus.OK);
			}
		}
	
	@GetMapping(value = "listarTodos")
	public ResponseEntity<List<ClienteAluno>> listarClienteAluno(){
		logger.info("Listar Todos os clientes cadastrados" );
		List<ClienteAluno> listagemCliente = this.clienteAlunoService.findAll();
		return new ResponseEntity<List<ClienteAluno>>(listagemCliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "listarClientesAtivos")
	public ResponseEntity<List<ClienteAluno>> listarClienteAlunoAtivo(){
		logger.info("Listar Todos os clientes cadastrados" );
		List<ClienteAluno> listagemCliente = this.clienteAlunoService.findAllByIsclientecartaoIsTrue();
		return new ResponseEntity<List<ClienteAluno>>(listagemCliente, HttpStatus.OK);

	}

	@GetMapping(value = "{id}/listarClienteCartao") 
	public ResponseEntity<Cartao> listaClienteCartao(@PathVariable Long id) {
		ClienteAluno cliente = clienteAlunoService.findById(id).get();
		if (cliente != null ) {
			logger.info("Dados do Cliente encontrado");
			Cartao listagemClienteCartao = this.cartaoService.findByCliente(cliente);
			logger.info("Consultando dados do Cartao");
			return new ResponseEntity<Cartao>(listagemClienteCartao, HttpStatus.OK);
		} else {
			logger.info("Dados do Cliente n�o encontrados");
			return new ResponseEntity<Cartao>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "{id}/buscaClienteAlunoId")
	public ResponseEntity<ClienteAluno> buscaClienteAlunoPorID(@PathVariable Long id){
		logger.info("C�digo Cliente: " + id);
		Optional<ClienteAluno> cliente = this.clienteAlunoService.findById(id);
		if (cliente.isPresent()) {
			ClienteAluno clienteAluno = cliente.get();
			return new ResponseEntity<ClienteAluno>(clienteAluno, HttpStatus.OK);
		} else {
			logger.info("Dados do Cliente/Aluno n�o encontrados");
			return new ResponseEntity<ClienteAluno>(HttpStatus.NO_CONTENT);
		}
	}
}

