package br.com.fiapspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;

@Service
public interface CartaoService {

	List<Cartao> findByCliente(Optional<ClienteAluno> cliente);

}
