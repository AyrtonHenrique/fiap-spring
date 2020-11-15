package br.com.fiapspring.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import br.com.fiapspring.controller.ClienteAlunoController;
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
		criarSenha(clienteAluno);
		return clienteAlunoRepository.save(clienteAluno);
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
	public ClienteAluno update(Integer rm, ClienteAluno clienteAluno) {
		logger.info("Dados do Cliente atualizados");
		criarSenha(clienteAluno);
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
