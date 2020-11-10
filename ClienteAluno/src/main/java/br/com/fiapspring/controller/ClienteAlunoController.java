package br.com.fiapspring.controller;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.fiapspring.entity.ClienteAluno;
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
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteAlunoDTO create(@RequestBody ClienteAluno clienteAluno){
		return clienteAlunoService.create(clienteAluno);
	}
}
