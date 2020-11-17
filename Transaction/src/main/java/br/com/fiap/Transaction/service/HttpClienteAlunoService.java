package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;

public interface HttpClienteAlunoService {
    public ClienteAlunoDTO getAluno(Long idCliente);
    public CartaoDTO getCartao(Long idCliente, Long idCartao);
}
