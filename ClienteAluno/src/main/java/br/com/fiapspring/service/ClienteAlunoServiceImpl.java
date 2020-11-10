package br.com.fiapspring.service;

import java.util.List;

import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClienteAlunoServiceImpl implements ClienteAlunoService {
	
	private ClienteAlunoRepository clienteAlunoRepository;
	
	public ClienteAlunoServiceImpl(ClienteAlunoRepository clienteAlunoRepository) {
		this.clienteAlunoRepository = clienteAlunoRepository;
	}

	@Override
	public List<ClienteAlunoDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteAlunoDTO findById(Long rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteAluno create(ClienteAluno clienteAluno) {
		
		clienteAluno.setNome(clienteAlunoCreateUpdateDTO.getNome());
		clienteAluno.setTurma(clienteAlunoCreateUpdateDTO.getTurma());
    
		ClienteAluno savedClienteAluno = clienteAlunoRepository.save(clienteAluno);

        return new ClienteAlunoDTO(savedClienteAluno);

	}

	@Override
	public ClienteAlunoDTO update(Long rm, ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO) {
		ClienteAluno clienteAluno = getClienteAluno(rm);
		
		clienteAluno.setNome(clienteAlunoCreateUpdateDTO.getNome());
		clienteAluno.setTurma(clienteAlunoCreateUpdateDTO.getTurma());
		
		ClienteAluno savedClienteAluno = clienteAlunoRepository.save(clienteAluno);
		return new ClienteAlunoDTO(savedClienteAluno);
	}

	@Override
	public void delete(Long rm) {
		// TODO Auto-generated method stub
		
	}
	
	private ClienteAluno getClienteAluno(Long rm) {
		return clienteAlunoRepository.findByRm(rm)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

}
