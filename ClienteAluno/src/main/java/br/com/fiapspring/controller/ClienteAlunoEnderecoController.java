/**
 * 
 */
package br.com.fiapspring.controller;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;
import br.com.fiapspring.service.ClienteAlunoEnderecoService;


/**
 * @author SaraRegina
 *
 */
@RestController
@RequestMapping("endereco")
public class ClienteAlunoEnderecoController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);
	private final ClienteAlunoEnderecoService clienteAlunoEnderecoService;

	/**
	 * 
	 */
	public ClienteAlunoEnderecoController(ClienteAlunoEnderecoService clienteAlunoEnderecoService) {
		this.clienteAlunoEnderecoService = clienteAlunoEnderecoService;
	}
	
	@PostMapping()
	public ResponseEntity<ClienteAlunoEnderecoDTO> createClienteAlunoEndereco(@RequestBody ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO , 
													UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoEnderecoDTO clienteAlunoEnderecoDTO =  new ClienteAlunoEnderecoDTO();

		try {
			Class<ClienteAlunoEnderecoCreateUpdateDTO> createDadosEndereco = ClienteAlunoEnderecoCreateUpdateDTO.class;
			Field[] campos = createDadosEndereco.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(clienteAlunoEnderecoCreateUpdateDTO);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados de endereco inconsistentes");
					return new ResponseEntity<ClienteAlunoEnderecoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}
			clienteAlunoEnderecoDTO = clienteAlunoEnderecoService.create(clienteAlunoEnderecoCreateUpdateDTO);
			headers.setLocation(builder.path("/clientealunoendereco/{id}").buildAndExpand(clienteAlunoEnderecoDTO.getId()).toUri());
			return new ResponseEntity<ClienteAlunoEnderecoDTO>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<ClienteAlunoEnderecoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PutMapping("{id}")
	public ResponseEntity<ClienteAlunoEnderecoDTO> upadateClienteAlunoEndereco(@PathVariable Long id,
														@RequestBody ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoEnderecoDTO clienteAlunoEnderecoDTO =  new ClienteAlunoEnderecoDTO();
		
		
		try {
			clienteAlunoEnderecoDTO = (ClienteAlunoEnderecoDTO) clienteAlunoEnderecoService.buscaEnderecoPorIdCliente(clienteAlunoCreateUpdateDTO.getIdCliente());
			if (clienteAlunoEnderecoDTO == null) {
				logger.error("Dados do Endereco do Cliente nao encontrados." + id);
				return new ResponseEntity<ClienteAlunoEnderecoDTO>(HttpStatus.BAD_REQUEST);
			} else {
				//atualizar os dados cliente com os dados recebidos
				logger.info("Atualizando os dados do endereco do aluno");
				clienteAlunoEnderecoService.update(id, clienteAlunoCreateUpdateDTO);
				return new ResponseEntity<ClienteAlunoEnderecoDTO>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ClienteAlunoEnderecoDTO> removerEnderecoClienteAluno(@PathVariable Long id) {
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoEnderecoDTO clienteAlunoEnderecoDTO =  new ClienteAlunoEnderecoDTO();
		try {
			clienteAlunoEnderecoDTO = clienteAlunoEnderecoService.findById(id);
			if (clienteAlunoEnderecoDTO == null ) {
				logger.error("Dados do Cliente/Aluno nao encontrados" + id);
				return new ResponseEntity<ClienteAlunoEnderecoDTO>(HttpStatus.BAD_REQUEST);
			} else {
				clienteAlunoEnderecoService.delete(id);
				logger.info("Removendo Dados do cliente");
				return new ResponseEntity<ClienteAlunoEnderecoDTO>(headers, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ClienteAlunoEnderecoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "{idCliente}")
	public List<ClienteAlunoEnderecoDTO> listarClienteEndereco(@PathVariable Long idCliente) {
		return clienteAlunoEnderecoService.buscaEnderecoPorIdCliente(idCliente);
	}

}
