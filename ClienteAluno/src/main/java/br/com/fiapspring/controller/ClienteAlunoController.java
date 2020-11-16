package br.com.fiapspring.controller;


import java.lang.reflect.Field;
import java.util.List;


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
	public ResponseEntity<ClienteAlunoDTO> create(@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO , 
													UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoDTO clienteAlunoDTO = new ClienteAlunoDTO();

		try {
			
			Class<ClienteAlunoCreateUpdateDTO> createDadosCliente = ClienteAlunoCreateUpdateDTO.class;
			Field[] campos = createDadosCliente.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(createDadosCliente);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados do Cliente inconsistentes");
					return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}
			clienteAlunoDTO = clienteAlunoService.getClienteAlunoByRm(clienteAlunoCreateUpdateDTO.getRm());
			if (clienteAlunoDTO.getRm() == null ) {
				clienteAlunoDTO = clienteAlunoService.create(clienteAlunoCreateUpdateDTO);
				headers.setLocation(builder.path("/clientealuno/{id}").buildAndExpand(clienteAlunoDTO.getId()).toUri());
				return new ResponseEntity<ClienteAlunoDTO>(headers, HttpStatus.CREATED);
			} else {	
				logger.info("Aluno/Cliente já cadastrado na base" + clienteAlunoCreateUpdateDTO.getRm());
				return new ResponseEntity<ClienteAlunoDTO>(headers, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	
	
	@PutMapping("{idCliente}")
	public ResponseEntity<ClienteAlunoDTO> upadateClienteAluno(@PathVariable Long id,@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoDTO clienteAlunoDTO = new ClienteAlunoDTO();
		try {
			
			Class<ClienteAlunoCreateUpdateDTO> createDadosCliente = ClienteAlunoCreateUpdateDTO.class;
			Field[] campos = createDadosCliente.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(createDadosCliente);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados do Cliente inconsistentes");
					return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}
			clienteAlunoDTO = clienteAlunoService.findById(id);
			if (clienteAlunoDTO == null) {
				logger.error("Dados do Cliente/Aluno nao encontrados" + id);
				return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
			} else {
				//atualizar os dados cliente com os dados recebidos
				logger.info("Atualizando dados aluno");
				clienteAlunoService.update(id, clienteAlunoCreateUpdateDTO);
				return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("{idCliente}")
	public ResponseEntity<ClienteAlunoDTO> removerClienteAluno(@PathVariable Long id) {
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoDTO clienteAlunoDTO = new ClienteAlunoDTO();
		try {
			Class<ClienteAlunoCreateUpdateDTO> createDadosCliente = ClienteAlunoCreateUpdateDTO.class;
			Field[] campos = createDadosCliente.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(createDadosCliente);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados do Cliente inconsistentes");
					return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}
			clienteAlunoDTO = clienteAlunoService.findById(id);
			if (clienteAlunoDTO == null ) {
				logger.error("Dados do Cliente/Aluno nao encontrados" + id);
				return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
			} else {
				clienteAlunoService.delete(id);
				logger.info("Removendo Dados do cliente");
				return new ResponseEntity<ClienteAlunoDTO>(headers, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ClienteAlunoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("{idCliente}/ativar")
	public ResponseEntity<ClienteAlunoDTO> ativarClienteAluno(@PathVariable Long id,@RequestBody ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO){
		HttpHeaders headers = new HttpHeaders();
		ClienteAlunoDTO clienteAlunoDTO = new ClienteAlunoDTO();
		try {
			Class<ClienteAlunoCreateUpdateDTO> createDadosCliente = ClienteAlunoCreateUpdateDTO.class;
			Field[] campos = createDadosCliente.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(createDadosCliente);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados do Cliente inconsistentes");
					return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}
			clienteAlunoDTO = clienteAlunoService.findById(id);
			if (clienteAlunoDTO == null) {
				logger.error("Dados do Cliente/Aluno nao encontrados" + id);
				return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.BAD_REQUEST);
			} else {
			
			logger.info("Ativar os dados do Aluno para cliente");
			clienteAlunoService.update(id, clienteAlunoCreateUpdateDTO);
			return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ClienteAlunoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		 
	@GetMapping(value = "listarTodos")
	public List<ClienteAlunoDTO> findAll(@RequestParam(required = false) Long id){
		logger.info("Listar clientes Ativos" );
		return clienteAlunoService.findAll(id);
	}
	
	@GetMapping
	public ResponseEntity<ClienteAlunoDTO> buscaClienteAlunoPorID(@PathVariable Long id){
		logger.info("Cï¿½digo Cliente: " + id);
		ClienteAlunoDTO cliente = this.clienteAlunoService.findById(id);
		if (cliente != null) {
			ClienteAlunoDTO clienteAluno = cliente;
			return new ResponseEntity<ClienteAlunoDTO>(clienteAluno, HttpStatus.OK);
		} else {
			logger.info("Dados do Cliente/Aluno nï¿½o encontrados");
			return new ResponseEntity<ClienteAlunoDTO>(HttpStatus.NO_CONTENT);
		}
	}
}

