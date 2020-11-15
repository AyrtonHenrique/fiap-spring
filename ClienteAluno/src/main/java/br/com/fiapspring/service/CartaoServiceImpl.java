/**
 * 
 */
package br.com.fiapspring.service;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;
import br.com.fiapspring.entity.Cartao;
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

    @Override
    public List<CartaoDTO> findAll(Integer numeroCartao) {
        return cartaoRepository.findAll()
                .stream()
                .map(cartao -> new CartaoDTO(cartao))
                .collect(Collectors.toList());
    }
    
    @Override
    public CartaoDTO findById(Long id) {
        Cartao cartao = getCartao(id);
        return new CartaoDTO(cartao);
    }

	@Override
	public CartaoDTO create(CartaoCreateUpdateDTO cartaoCreateUpdateDTO) {
		Cartao cartao = new Cartao();
		cartao.setClienteAluno(cartaoCreateUpdateDTO.getClienteAluno());
		cartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		cartao.setCodigoIdentificador(cartao.getCodigoIdentificador());
		cartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		Cartao salvarCartao = cartaoRepository.save(cartao);
		return new CartaoDTO(salvarCartao);
	}

	@Override
	public CartaoDTO update(Long id, CartaoCreateUpdateDTO cartaoCreateUpdateDTO) {
		Cartao cartao = getCartao(id);
		
		cartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		cartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		cartao.setCodigoIdentificador(cartaoCreateUpdateDTO.getCodigoIdentificador());
		cartao.setClienteAluno(cartaoCreateUpdateDTO.getClienteAluno());
		
		Cartao salvarCartao = cartaoRepository.save(cartao);
		
		return new CartaoDTO(salvarCartao);
	}

	@Override
	public void delete(Long id) {
		logger.info("Dados do Cliente removidos");
		this.cartaoRepository.delete(this.cartaoRepository.findById(id).get());
	}
	
    private Cartao getCartao(Long id) {
        return cartaoRepository.findById(id).get();
    }

	
 
}
