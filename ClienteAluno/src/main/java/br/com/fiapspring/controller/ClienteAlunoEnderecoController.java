/**
 * 
 */
package br.com.fiapspring.controller;

import java.lang.reflect.Field;
import java.util.List;

import br.com.fiapspring.entity.ClienteAlunoEndereco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;
import br.com.fiapspring.service.ClienteAlunoEnderecoService;


/**
 * @author SaraRegina
 *
 */
@RestController
@RequestMapping("cliente/{idCliente}/endereco")
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
	public ClienteAlunoEndereco createEnderecoAluno(@PathVariable(name = "idCliente") Long idCliente,
													@RequestBody ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO ,
													UriComponentsBuilder builder){
		return clienteAlunoEnderecoService.create(idCliente, clienteAlunoEnderecoCreateUpdateDTO);
	}

	@PutMapping("{idEndereco}")
	public ClienteAlunoEndereco updateEnderecoAluno(@PathVariable(name = "idCliente") Long idCliente,
													@PathVariable(name = "idEndereco") Long idEndereco,
													@RequestBody ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO ,
													UriComponentsBuilder builder){
		return clienteAlunoEnderecoService.update(idCliente,idEndereco, clienteAlunoEnderecoCreateUpdateDTO);
	}

	@DeleteMapping("{idEndereco}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeEnderecoCliente(@PathVariable(name = "idCliente") Long idCliente,
									  @PathVariable(name = "idEndereco") Long idEndereco){
		clienteAlunoEnderecoService.delete(idCliente,idEndereco);
	}

	@GetMapping()
	public List<ClienteAlunoEnderecoDTO> listarClienteEndereco(@PathVariable Long idCliente) {
		return clienteAlunoEnderecoService.buscaEnderecoPorIdCliente(idCliente);
	}

}
