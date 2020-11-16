/**
 * 
 */
package br.com.fiapspring.controller;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;
import br.com.fiapspring.service.CartaoService;

/**
 * @author SaraRegina
 * Classe que uma controller que pode ser invocada via chamadas REST com JSON
 */
@RestController
@RequestMapping("cartao")
public class CartaoController {
	
	
	private final Logger logger = LoggerFactory.getLogger(CartaoController.class);
	private final CartaoService cartaoService;

	/**
	 * Construtor para instanciar os médodos das classes service
	 */
	public CartaoController(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}
	
	@PostMapping
	public ResponseEntity<CartaoDTO> cadastarCartao(@RequestBody CartaoCreateUpdateDTO cartaoCreateUpdateDTO, 
													UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		try {
				Class<CartaoCreateUpdateDTO> createDadosCartao = CartaoCreateUpdateDTO.class;
				Field[] campos = createDadosCartao.getDeclaredFields();
				for (Field campo : campos) {
					campo.setAccessible(true);
					Object objeto = campo.get(cartaoCreateUpdateDTO);
					if (objeto == null || objeto.equals("") ) {
						logger.error("Dados do Cartao inconsistentes");
						return new ResponseEntity<CartaoDTO>(HttpStatus.BAD_REQUEST);
					} 	
				}		
				CartaoDTO cartaoDTO = cartaoService.findByNumeroCartao(cartaoCreateUpdateDTO.getNumerocartao());
				if (cartaoDTO != null) {
					logger.error("Cartao já cadastrado");
					return new ResponseEntity<CartaoDTO>(headers, HttpStatus.BAD_REQUEST);
				} else {
					CartaoDTO novoCartao = cartaoService.create(cartaoCreateUpdateDTO);
					cartaoService.create(cartaoCreateUpdateDTO);
					headers.setLocation(builder.path("/cartao/{idCliente}").buildAndExpand(novoCartao.getId()).toUri());
					return new ResponseEntity<CartaoDTO>(headers, HttpStatus.CREATED);
				}
		} catch (Exception e) {
			return new ResponseEntity<CartaoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@PutMapping("{idCartao}")
	public ResponseEntity<CartaoDTO> upadateCartao(@PathVariable Long id,@RequestBody CartaoCreateUpdateDTO cartaoCreateUpdateDTO){
		HttpHeaders headers = new HttpHeaders();
		CartaoDTO cartaoDTO = new CartaoDTO();
		try {
			
			Class<CartaoCreateUpdateDTO> createDadosCartao = CartaoCreateUpdateDTO.class;
			Field[] campos = createDadosCartao.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				Object objeto = campo.get(cartaoCreateUpdateDTO);
				if (objeto == null || objeto.equals("") ) {
					logger.error("Dados do Cartao inconsistentes");
					return new ResponseEntity<CartaoDTO>(HttpStatus.BAD_REQUEST);
				} 	
			}	
			
			cartaoDTO = cartaoService.findById(id);
			if (cartaoDTO == null) {
				logger.error("Dados do Cartao não encontrado" + id);
				return new ResponseEntity<CartaoDTO>(HttpStatus.BAD_REQUEST);
			} else {
				//atualizar os dados cliente com os dados recebidos
				logger.info("Atualizando do cartao");
				cartaoService.update(id, cartaoCreateUpdateDTO);
				return new ResponseEntity<CartaoDTO>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("{idCartao}")
	public ResponseEntity<CartaoDTO> removerCartao(@PathVariable Long idCartao) {
		 HttpHeaders headers = new HttpHeaders();
		 try {	
				 if (idCartao.equals(null) || idCartao == 0 ) {
					 logger.error("Dados do cartao não localizados para remover");
					return new ResponseEntity<CartaoDTO>(headers, HttpStatus.NO_CONTENT);
				 } else {
					cartaoService.delete(idCartao);
					return new ResponseEntity<CartaoDTO>(headers, HttpStatus.OK);
				}
		 	} catch (Exception e) {
			 return new ResponseEntity<CartaoDTO>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		 	}	 
		}
	
	@GetMapping
	public CartaoDTO listarCartao(@RequestParam(required = false) Long idCartao){
		return (CartaoDTO) cartaoService.findAll(idCartao);
	}
	
	@GetMapping("{idCliente}")
	public List<CartaoDTO> listarCartaoPeloCliente(@PathVariable Long idCliente){
		return  cartaoService.buscaCartaoPorIdCliente(idCliente);
	}

}
