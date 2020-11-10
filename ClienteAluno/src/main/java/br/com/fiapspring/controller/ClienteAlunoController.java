package br.com.fiapspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.entity.ClienteAlunoEndereco;
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
	
	public ClienteAlunoController(ClienteAlunoService clienteAlunoService) {
		this.clienteAlunoService = clienteAlunoService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody ClienteAluno clienteAluno, UriComponentsBuilder builder){
		
		HttpHeaders headers = new HttpHeaders();
		try {
			if (ClienteAluno.getEnderecos().size() == 0) {
				logger.info("Cliente deve possuir um endereço.");
					return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
			} else {
				List<ClienteAlunoEndereco> novaLista = new ArrayList<ClienteAlunoEndereco>();
				// Limpa os endereços pra depois adicionar
				for (ClienteAlunoEndereco enderecoCliente : ClienteAluno.getEnderecos()) {
					novaLista.add(enderecoCliente);
				}
				ClienteAluno.getEnderecos().clear();
				
				// Adiciona o cliente e pega o cliente Managed
				ClienteAluno novoCliente = clienteAlunoService.create(clienteAluno);
				for (ClienteAlunoEndereco enderecoCliente : novaLista) {
					enderecoCliente.setClienteAluno(novoCliente);
					novoCliente.addEndereco(enderecoCliente);
				}

				//Salva o cliente com a sua lista de endereços dentro
				clienteAlunoService.create(novoCliente);
				
				headers.setLocation(builder.path("/clientealuno/{id}").buildAndExpand(novoCliente.getId()).toUri());
					return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Void> upadateClienteAluno(){
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	@GetMapping
	public ResponseEntity<List<ClienteAluno>> listarClienteAluno(){
		logger.info("Listar Todos os clientes cadastrados" );
		List<ClienteAluno> listagemCliente = this.clienteAlunoService.findAll();
		return new ResponseEntity<List<ClienteAluno>>(listagemCliente, HttpStatus.OK);

	}
	
}

