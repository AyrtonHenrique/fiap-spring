/**
 * 
 */
package br.com.fiapspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
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
	public ResponseEntity<Void> cadastarCartao(@RequestBody Cartao cartao, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		if (cartao.getClienteAluno() == null ) {
			logger.error("Cartao deve ter possuir um cliente cadastrado");
			return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
		} else {
			Cartao novoCartao = cartaoService.create(cartao);
			cartaoService.create(novoCartao);
			headers.setLocation(builder.path("/cartao/{id}").buildAndExpand(novoCartao.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("{id}/removerCartao")
	public ResponseEntity<Void> removerCartao(@PathVariable Long id) {
		HttpHeaders headers = new HttpHeaders();
		logger.info("Consultando dados do cartão para remoção.");
		Cartao dadosCartao = cartaoService.findById(id).get();
		 if (dadosCartao == null ) {
			 logger.error("Dados do cartao não localizados para remover");
			return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
		 } else {
			cartaoService.delete(id);
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		}
	}


}
