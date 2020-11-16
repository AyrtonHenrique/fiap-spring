package br.com.fiapspring.controller;


import java.lang.reflect.Field;
import java.util.List;


import br.com.fiapspring.entity.ClienteAluno;
import org.slf4j.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.service.CartaoService;
import br.com.fiapspring.service.ClienteAlunoService;

/**
 * @author SaraRegina
 * Classe responsaval para cadastros do aluno
 */
@RestController
@RequestMapping("cliente")
public class ClienteAlunoController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);
	private final ClienteAlunoService clienteAlunoService;
	
	public ClienteAlunoController(ClienteAlunoService clienteAlunoService, CartaoService cartaoService) {
		this.clienteAlunoService = clienteAlunoService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteAlunoDTO create(@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO ,
											   UriComponentsBuilder builder){
		return clienteAlunoService.create(clienteAlunoCreateUpdateDTO);
	}

	@PutMapping("{idCliente}")
	public ClienteAlunoDTO upadateClienteAluno(@PathVariable Long idCliente,@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
		return clienteAlunoService.update(idCliente, clienteAlunoCreateUpdateDTO);
	}
	
	@DeleteMapping("{idCliente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerClienteAluno(@PathVariable Long idCliente) {
		clienteAlunoService.delete(idCliente);
	}

	@PutMapping("{idCliente}/ativar")
	public ClienteAlunoDTO ativarClienteAluno(@PathVariable Long idCliente){
		return clienteAlunoService.updateAlunoToCliente(idCliente);
	}
		 
	@GetMapping()
	public List<ClienteAlunoDTO> findAll(){
		return clienteAlunoService.findAll();
	}
	
	@GetMapping("{idCliente}")
	public ClienteAlunoDTO buscaClienteAlunoPorID(@PathVariable Long idCliente){
		return clienteAlunoService.findById(idCliente);
	}
}

