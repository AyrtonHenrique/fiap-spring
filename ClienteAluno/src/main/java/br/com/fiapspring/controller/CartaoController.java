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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;
import br.com.fiapspring.service.CartaoService;

/**
 * @author SaraRegina
 * Classe que uma controller que pode ser invocada via chamadas REST com JSON
 */
@RestController
@RequestMapping("cliente/{idCliente}/cartao")
public class CartaoController {
	
	
	private final Logger logger = LoggerFactory.getLogger(CartaoController.class);
	private final CartaoService cartaoService;

	/**
	 * Construtor para instanciar os medodos das classes service
	 */
	public CartaoController(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CartaoDTO cadastarCartao(@PathVariable(name="idCliente") Long idCliente,
									@RequestBody CartaoCreateUpdateDTO cartaoCreateUpdateDTO) {
		return cartaoService.create(idCliente, cartaoCreateUpdateDTO);
	}
	

	@PutMapping("{idCartao}")
	public CartaoDTO upadateCartao(@PathVariable(name="idCliente") Long idCliente,
								   @PathVariable(name="idCartao") Long idCartao,
								   @RequestBody CartaoCreateUpdateDTO cartaoCreateUpdateDTO){
		return  cartaoService.update(idCliente,idCartao,cartaoCreateUpdateDTO);
		
	}
	
	@DeleteMapping("{idCartao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCartao(@PathVariable(name="idCliente") Long idCliente,
												   @PathVariable Long idCartao) {
		cartaoService.delete(idCliente, idCartao);
	}

	@GetMapping()
	public List<CartaoDTO> listarCartoesDoCliente(@PathVariable(name="idCliente") Long idCliente){
		return cartaoService.buscaCartaoPorIdCliente(idCliente);
	}

}
