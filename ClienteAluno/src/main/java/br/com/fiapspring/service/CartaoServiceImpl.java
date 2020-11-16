/**
 * 
 */
package br.com.fiapspring.service;


import java.util.List;
import java.util.stream.Collectors;

import br.com.fiapspring.entity.ClienteAlunoEndereco;
import ch.qos.logback.core.net.server.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;
import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.CartaoRepository;
import br.com.fiapspring.repository.ClienteAlunoRepository;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author SaraRegina
 *
 */
@Service
public class CartaoServiceImpl implements CartaoService {
	
	private final Logger logger = LoggerFactory.getLogger(CartaoServiceImpl.class);

	private CartaoRepository cartaoRepository;
	private ClienteAlunoService clienteAlunoService;
	
	public CartaoServiceImpl(CartaoRepository cartaoRepository, ClienteAlunoService clienteAlunoService) {
		this.cartaoRepository = cartaoRepository;
		this.clienteAlunoService = clienteAlunoService;
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
	public CartaoDTO create(Long idCliente,
							CartaoCreateUpdateDTO cartaoCreateUpdateDTO){
		ClienteAluno clienteAluno = clienteAlunoService.getAluno(idCliente);
		Cartao cartaoExistente = findCartaoByNumero(idCliente, cartaoCreateUpdateDTO.getNumerocartao());

		if (cartaoExistente != null){
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}

		Cartao novoCartao = new Cartao();

		novoCartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		novoCartao.setCodigoIdentificador(cartaoCreateUpdateDTO.getCodigoIdentificador());
		novoCartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		novoCartao.setClienteAluno(clienteAluno);

		Cartao cartaoSalvo = cartaoRepository.save(novoCartao);

		return new CartaoDTO(cartaoSalvo);
	}

	@Override
	public CartaoDTO update(Long idCliente,
							Long idCartao,
							CartaoCreateUpdateDTO cartaoCreateUpdateDTO){
		Cartao cartao = findCartaoById(idCliente, idCartao);
		Cartao cartaoExistente = findCartaoByNumero(idCliente, cartaoCreateUpdateDTO.getNumerocartao());

		if (cartaoExistente != null && cartaoExistente.getIdCartao() != idCartao){
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}

		cartao.setNumerocartao(cartaoCreateUpdateDTO.getNumerocartao());
		cartao.setDatavalidade(cartaoCreateUpdateDTO.getDatavalidade());
		cartao.setCodigoIdentificador(cartaoCreateUpdateDTO.getCodigoIdentificador());

		Cartao cartaoAtualizado = cartaoRepository.save(cartao);
		
		return new CartaoDTO(cartaoAtualizado);
	}

	@Override
	public void delete(Long idCliente, Long idCartao) {
		this.cartaoRepository.delete(findCartaoById(idCliente, idCartao));
	}
	
    private Cartao getCartao(Long id) {
        return cartaoRepository.findById(id).get();
    }
    
    @Override
    public List<CartaoDTO> buscaCartaoPorIdCliente(Long idCliente) {
    	return cartaoRepository.buscaCartaoPorCliente(idCliente)
    			.stream()
    			.map(cartao -> new CartaoDTO(cartao))
    			.collect(Collectors.toList());
    	
    }

    private Cartao findCartaoByNumero(Long idCliente, Long numeroCartao){
		return cartaoRepository.findByNumerocartao(numeroCartao);
	}

	private Cartao findCartaoById(Long idCliente, Long idCartao ){
		return cartaoRepository.findById(idCartao)
				.filter(cartao -> cartao.getClienteAluno().getIdCliente() == idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
    
}
