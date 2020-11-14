package br.com.fiapspring.service;

import java.util.List;
import java.util.Optional;

import br.com.fiapspring.controller.ClienteAlunoController;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteAlunoServiceImpl implements ClienteAlunoService {
	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);
	
	private ClienteAlunoRepository clienteAlunoRepository;
	
	public ClienteAlunoServiceImpl(ClienteAlunoRepository clienteAlunoRepository) {
		this.clienteAlunoRepository = clienteAlunoRepository;
	}

	@Override
	public List<ClienteAluno> findAll() {
		return clienteAlunoRepository.findAll();
	}

	@Override
	public Optional<ClienteAluno> findById(long id) {
		return clienteAlunoRepository.findById(id);
	}

	@Override
	public ClienteAluno create(ClienteAluno clienteAluno) {
		logger.info("Dados do Cliente cadastratados");
        return clienteAlunoRepository.save(clienteAluno);
	}

	@Override
	public ClienteAluno update(Integer rm, ClienteAluno clienteAluno) {
		logger.info("Dados do Cliente atualizados");
		return clienteAlunoRepository.save(clienteAluno);
	}

	@Override
	public void delete(Integer rm) {
		ClienteAluno cliente = getClienteAlunoByRm(rm);
		logger.info("Dados do Cliente removidos");
		clienteAlunoRepository.deleteById(cliente.getId());
	}
	
	public ClienteAluno getClienteAlunoByRm(Integer rm) {
		return clienteAlunoRepository.findAllByRm(rm);
		
	}

	@Override
	public List<ClienteAluno> findAllByIsclientecartaoIsTrue() {
		return clienteAlunoRepository.findAllByIsclientecartaoIsTrue();
	}
	
	

}
