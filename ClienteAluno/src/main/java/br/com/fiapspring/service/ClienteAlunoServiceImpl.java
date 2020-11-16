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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Response;
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
		ClienteAluno clienteAluno =  this.getClienteAlunoByRm(clienteAlunoCreateUpdateDTO.getRm());

		if (clienteAluno == null){
			ClienteAluno novoClienteAluno = new ClienteAluno();

			novoClienteAluno.setRm(clienteAlunoCreateUpdateDTO.getRm());
			novoClienteAluno.setNome(clienteAlunoCreateUpdateDTO.getNome());
			novoClienteAluno.setTurma(clienteAlunoCreateUpdateDTO.getTurma());
			novoClienteAluno.setRg(clienteAlunoCreateUpdateDTO.getRg());
			novoClienteAluno.setCpf(clienteAlunoCreateUpdateDTO.getCpf());
			novoClienteAluno.setDataNascimento(clienteAlunoCreateUpdateDTO.getDataNascimento());
			novoClienteAluno.setIsCliente(clienteAlunoCreateUpdateDTO.getIsCliente());

			ClienteAluno clienteAlunoCriado = clienteAlunoRepository.save(novoClienteAluno);

			logger.info("Dados do Cliente cadastratados");
			criarSenha(clienteAlunoCriado);
			return new ClienteAlunoDTO(clienteAlunoCriado);
		} else {
			throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ja existe um aluno com o RM informado! Favor verificar.");
		}
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
	public ClienteAlunoDTO updateAlunoToCliente(Long id) {
		ClienteAluno clienteAluno = getAluno(id);
		clienteAluno.setIsCliente(true);
		ClienteAluno atualizarCliente = clienteAlunoRepository.save(clienteAluno);

		return new ClienteAlunoDTO(atualizarCliente);
	}

	@Override
	public ClienteAlunoDTO update(Long id, ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO) {
		ClienteAluno clienteAluno = getAluno(id);
		
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
		return new ClienteAlunoDTO(atualizarCliente);
	}

	@Override
	public void delete(Long id) {
		ClienteAluno cliente = getAluno(id);
		logger.info("Dados do Cliente removidos");
		clienteAlunoRepository.deleteById(cliente.getIdCliente());
	}

	@Override
	public List<ClienteAlunoDTO> findAll() {
        return clienteAlunoRepository.findAll()
                .stream()
                .map(clienteAluno -> new ClienteAlunoDTO(clienteAluno))
                .collect(Collectors.toList());
	}


	@Override
	public ClienteAlunoDTO findById(long id) {
		ClienteAluno clienteAluno = getAluno(id);
		return new ClienteAlunoDTO(clienteAluno);
	}

	private ClienteAluno getAluno(long id){
		return clienteAlunoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public ClienteAluno getClienteAlunoByRm(Integer rm) {
		return clienteAlunoRepository.findAllByRm(rm).orElse(null);
	}
}
