package br.com.fiapspring.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;


@Service
public interface CartaoService {

	public CartaoDTO findById(Long id);

	public CartaoDTO create(Long idCliente, CartaoCreateUpdateDTO cartaoCreateUpdateDTO);
	
	public CartaoDTO update(Long idCliente, Long idCartao, CartaoCreateUpdateDTO cartaoCreateUpdateDTO);
	
	public void delete(Long idCliente, Long idCartao);
	
	public List<CartaoDTO> findAll(Long idCartao);
	
	public List<CartaoDTO> buscaCartaoPorIdCliente(Long idCliente);
	
	public CartaoDTO findByNumeroCartao(Long numeroCartao);



}
