package br.com.fiapspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;


@Service
public interface CartaoService {

	public CartaoDTO findById(Long id);

	public CartaoDTO create(CartaoCreateUpdateDTO cartaoCreateUpdateDTO);
	
	public CartaoDTO update(Long id, CartaoCreateUpdateDTO cartaoCreateUpdateDTO);
	
	public void delete(Long id);
	
	public List<CartaoDTO> findAll(Integer mumerocartao);

}
