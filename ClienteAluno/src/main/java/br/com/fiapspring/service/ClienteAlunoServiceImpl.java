package br.com.fiapspring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiapspring.controller.ClienteAlunoController;
import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;
import br.com.fiapspring.security.SecurityPasswordRequest;
import br.com.fiapspring.security.SecurityRemote;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClienteAlunoServiceImpl implements ClienteAlunoService {
	@Value("${securityRemote}")
	private String remoteServer;

	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);

	private ClienteAlunoRepository clienteAlunoRepository;

	public ClienteAlunoServiceImpl(ClienteAlunoRepository clienteAlunoRepository) {
		this.clienteAlunoRepository = clienteAlunoRepository;
	}


	@Override
	public ClienteAlunoDTO create(ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO) {
		ClienteAluno clienteAluno = new ClienteAluno();
		
		clienteAluno.setRm(clienteAlunoCreateUpdateDTO.getRm());
		clienteAluno.setNome(clienteAlunoCreateUpdateDTO.getNome());
		clienteAluno.setTurma(clienteAlunoCreateUpdateDTO.getTurma());
		clienteAluno.setRg(clienteAlunoCreateUpdateDTO.getRg());
		clienteAluno.setCpf(clienteAlunoCreateUpdateDTO.getCpf());
		clienteAluno.setDataNascimento(clienteAlunoCreateUpdateDTO.getDataNascimento());
		clienteAluno.setIsCliente(clienteAlunoCreateUpdateDTO.getIsCliente());

		
		ClienteAluno salvarCliente = clienteAlunoRepository.save(clienteAluno);
		
		logger.info("Dados do Cliente cadastratados");
		criarSenha(salvarCliente);
		return new ClienteAlunoDTO(salvarCliente);
	}

	private void criarSenha(ClienteAluno clienteAluno) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(remoteServer)
				.addConverterFactory(GsonConverterFactory.create()).build();
		SecurityRemote service = retrofit.create(SecurityRemote.class);
		try {
			service.criarSenha(
					new SecurityPasswordRequest(clienteAluno.getCpf(), clienteAluno.getCpf().substring(0, 4)))
					.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ClienteAlunoDTO update(Long id, ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO) {
		ClienteAluno clienteAluno = getClienteAluno(id).get();
		
		clienteAluno.setRm(clienteAlunoCreateUpdateDTO.getRm());
		clienteAluno.setTurma(clienteAlunoCreateUpdateDTO.getTurma());
		clienteAluno.setNome(clienteAlunoCreateUpdateDTO.getNome());
		clienteAluno.setRg(clienteAlunoCreateUpdateDTO.getRg());
		clienteAluno.setCpf(clienteAlunoCreateUpdateDTO.getCpf());
		clienteAluno.setDataNascimento(clienteAlunoCreateUpdateDTO.getDataNascimento());
		clienteAluno.setIsCliente(clienteAlunoCreateUpdateDTO.getIsCliente());
		ClienteAluno atualizarCliente = clienteAlunoRepository.save(clienteAluno);
		
		criarSenha(clienteAluno);
		
		logger.info("Dados do Cliente atualizados");
		return new ClienteAlunoDTO (atualizarCliente);
	}

	@Override
	public void delete(Long id) {
		ClienteAluno cliente = getClienteAluno(id).get();
		logger.info("Dados do Cliente removidos");
		clienteAlunoRepository.deleteById(cliente.getIdCliente());
	}

	

    private Optional<ClienteAluno> getClienteAluno(Long id) {
        return clienteAlunoRepository.findById(id);
                
    }


	@Override
	public List<ClienteAlunoDTO> findAll(Long id) {
        return clienteAlunoRepository.findAllByIsClienteIsTrue()
                .stream()
                .map(clienteAluno -> new ClienteAlunoDTO(clienteAluno))
                .collect(Collectors.toList());
	}


	@Override
	public ClienteAlunoDTO findById(long id) {
		ClienteAluno clienteAluno = getClienteAluno(id).get();
		return new ClienteAlunoDTO(clienteAluno);
	}
	
	
	public ClienteAlunoDTO getClienteAlunoByRm(Integer rm) {
		ClienteAluno clienteAluno = clienteAlunoRepository.findAllByrm(rm);
		return new ClienteAlunoDTO(clienteAluno);
	}




}
