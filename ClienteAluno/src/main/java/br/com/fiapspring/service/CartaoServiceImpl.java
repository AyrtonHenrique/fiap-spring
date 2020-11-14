/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;
import java.util.Optional;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.CartaoRepository;

/**
 * @author SaraRegina
 *
 */
public class CartaoServiceImpl implements CartaoService {
	
	private CartaoRepository cartaoRepository;
	
	public CartaoServiceImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	@Override
	public List<Cartao> findByCliente(Optional<ClienteAluno> cliente) {
		return  cartaoRepository.findByCliente(cliente);
	} 
	
 
}
