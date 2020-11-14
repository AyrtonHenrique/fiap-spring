package br.com.fiapspring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;

@Service
public interface CartaoService {

	Cartao findByCliente(ClienteAluno cliente);
	
	public Optional<Cartao> findById(long id);

	public Cartao create(Cartao cartao);
	
	public Cartao update(Long id, Cartao cartao);
	
	public void delete(Long id);

}
