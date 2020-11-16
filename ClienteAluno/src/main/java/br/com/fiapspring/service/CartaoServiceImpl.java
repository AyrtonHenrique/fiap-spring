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
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.CartaoRepository;
import br.com.fiapspring.repository.ClienteAlunoRepository;

/**
 * @author SaraRegina
 *
 */
@Service
public class CartaoServiceImpl implements CartaoService {
	
	private final Logger logger = LoggerFactory.getLogger(CartaoServiceImpl.class);

	private CartaoRepository cartaoRepository;
	private ClienteAlunoRepository clienteAlunoRepository;
	
	public CartaoServiceImpl(CartaoRepository cartaoRepository, ClienteAlunoRepository clienteAlunoRepository) {
		this.cartaoRepository = cartaoRepository;
		this.clienteAlunoRepository = clienteAlunoRepository;
	}

    @Override
    public List<CartaoDTO> findAll(Long idCartao) {
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
    
    
    public CartaoDTO findByNumeroCartao(Long numeroCartao) {
    	Cartao cartao = cartaoRepository.findByNumerocartao(numeroCartao);
    	return new CartaoDTO(cartao);
    }
    

	@Override
	public CartaoDTO create(CartaoCreateUpdateDTO cartaoCreateUpdateDTO) {
		Cartao cartao = new Cartao();
		ClienteAluno cliAluno = getCliente(cartaoCreateUpdateDTO.getIdCliente());
		cartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		cartao.setCodigoIdentificador(cartao.getCodigoIdentificador());
		cartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		cartao.setClienteAluno(cliAluno);
		Cartao salvarCartao = cartaoRepository.save(cartao);
		return new CartaoDTO(salvarCartao);
	}

	@Override
	public CartaoDTO update(Long id, CartaoCreateUpdateDTO cartaoCreateUpdateDTO) {
		Cartao cartao = getCartao(id);
		
		ClienteAluno cliAluno = getCliente(cartaoCreateUpdateDTO.getIdCliente());
		
		cartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		cartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		cartao.setCodigoIdentificador(cartaoCreateUpdateDTO.getCodigoIdentificador());
		cartao.setClienteAluno(cliAluno);
		
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
    
    @Override
    public List<CartaoDTO> buscaCartaoPorIdCliente(Long idCliente) {
    	ClienteAluno cliente = getCliente(idCliente);
    	return cartaoRepository.findByClienteAluno(cliente)
    			.stream()
    			.map(cartao -> new CartaoDTO(cartao))
    			.collect(Collectors.toList());
    	
    }
 
	private ClienteAluno getCliente(Long id) {
		return clienteAlunoRepository.findById(id).get();	
	}
	
   
    
}
