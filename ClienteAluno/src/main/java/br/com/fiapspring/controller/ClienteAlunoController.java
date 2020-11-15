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


import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
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
	public ResponseEntity<Void> create(@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO , 
													UriComponentsBuilder builder){
		
		HttpHeaders headers = new HttpHeaders();
		try {
			if (clienteAlunoCreateUpdateDTO.getClienteAlunoEnderecos().size() == 0) {
				logger.info("Cliente/Aluno deve possuir um endereco.");
					return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
			} else {
				List<ClienteAlunoEndereco> novaLista = new ArrayList<ClienteAlunoEndereco>();
				// Limpa os endere�os pra depois adicionar
				for (ClienteAlunoEndereco enderecoCliente : clienteAlunoCreateUpdateDTO.getClienteAlunoEnderecos()) {
					novaLista.add(enderecoCliente);
				}
				clienteAlunoCreateUpdateDTO.getClienteAlunoEnderecos().clear();
				
				// Adiciona o cliente e pega o cliente Managed
				ClienteAlunoDTO novoCliente = clienteAlunoService.create(clienteAlunoCreateUpdateDTO);

				headers.setLocation(builder.path("/clientealuno/{id}").buildAndExpand(novoCliente.getRm()).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("{id}/updateDadosClienteAluno")
	public ResponseEntity<Void> upadateClienteAluno(@PathVariable Long id,@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
		if (clienteAlunoCreateUpdateDTO == null) {
			logger.error("Dados do Client/Aluno nao encontrados");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			//atualizar os dados cliente com os dados recebidos
			logger.info("Atualizando dados aluno");
			clienteAlunoService.update(id, clienteAlunoCreateUpdateDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PutMapping("{id}/ativarDesativarClienteAluno")
	public ResponseEntity<Void> ativarClienteAluno(@PathVariable String rm,@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
			logger.info("Ativar os dados do Aluno para cliente");
			clienteAlunoService.update(Long.valueOf(rm), clienteAlunoCreateUpdateDTO);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		 
	@DeleteMapping("{id}/removerCliente")
	public ResponseEntity<Void> removerClienteAluno(@PathVariable Long id) {
			HttpHeaders headers = new HttpHeaders();
				cartaoService.delete(id);
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@GetMapping(value = "listaClientesAtivos")
	public ResponseEntity<List<ClienteAluno>> listarClienteAlunoAtivos(){
		logger.info("Listar Todos os clientes cadastrados" );
		List<ClienteAluno> listagemCliente = this.clienteAlunoService.findAllByIsClienteIsTrue();
		return new ResponseEntity<List<ClienteAluno>>(listagemCliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "listarTodos")
	public List<ClienteAlunoDTO> findAll(@RequestParam(required = false) Long id){
		logger.info("Listar Todos os clientes cadastrados" );
		return clienteAlunoService.findAll(id);
				

	}
	
	@GetMapping(value = "{id}/buscaClienteAlunoId")
	public ResponseEntity<ClienteAlunoDTO> buscaClienteAlunoPorID(@PathVariable Long id){
		logger.info("C�digo Cliente: " + id);
		ClienteAlunoDTO cliente = this.clienteAlunoService.findById(id);
		if (cliente != null) {
			ClienteAlunoDTO clienteAluno = cliente;
			return new ResponseEntity<ClienteAlunoDTO>(clienteAluno, HttpStatus.OK);
		} else {
			logger.info("Dados do Cliente/Aluno n�o encontrados");
			return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.NO_CONTENT);
		}
	}
}

