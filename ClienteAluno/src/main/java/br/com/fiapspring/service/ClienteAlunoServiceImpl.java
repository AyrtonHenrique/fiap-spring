package br.com.fiapspring.service;

import java.util.List;
import java.util.Optional;

import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteAlunoServiceImpl implements ClienteAlunoService {
	
	private ClienteAlunoRepository clienteAlunoRepository;
	
	public ClienteAlunoServiceImpl(ClienteAlunoRepository clienteAlunoRepository) {
		this.clienteAlunoRepository = clienteAlunoRepository;
	}

	@Override
	public List<ClienteAluno> findAll() {
		return clienteAlunoRepository.findAll();
	}

	public Optional<ClienteAluno> findById(long id) {
		// TODO Auto-generated method stub
		return clienteAlunoRepository.findById(id);
	}

	@Override
	public ClienteAluno create(ClienteAluno clienteAluno) {
        return clienteAlunoRepository.save(clienteAluno); 

	}

	@Override
	public ClienteAluno update(Long rm, ClienteAluno clienteAluno) {
		return clienteAlunoRepository.save(clienteAluno);
	}

	@Override
	public void delete(Long id) {
		clienteAlunoRepository.deleteById(this.clienteAlunoRepository.findById(id).get());
		
	}
	
	private ClienteAluno getClienteAluno(long id) {
		return clienteAlunoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

}
