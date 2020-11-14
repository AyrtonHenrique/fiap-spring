/**
 * 
 */
package br.com.fiapspring.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.CartaoRepository;

/**
 * @author SaraRegina
 *
 */
@Service
public class CartaoServiceImpl implements CartaoService {
	
	private final Logger logger = LoggerFactory.getLogger(CartaoServiceImpl.class);

	private CartaoRepository cartaoRepository;
	
	public CartaoServiceImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	public Cartao findByCliente(ClienteAluno clienteAluno) {
		return cartaoRepository.findByClienteAluno(clienteAluno);
	}

	@Override
	public Optional<Cartao> findById(long id) {
		return cartaoRepository.findById(id);
	}

	@Override
	public Cartao create(Cartao cartao) {
		return cartaoRepository.save(cartao);
	}

	@Override
	public Cartao update(Long id, Cartao cartao) {
		return cartaoRepository.saveAndFlush(cartao);
	}

	@Override
	public void delete(Long id) {
		logger.info("Dados do Cliente removidos");
		this.cartaoRepository.delete(this.cartaoRepository.findById(id).get());
	}
	
 
	
 
}
