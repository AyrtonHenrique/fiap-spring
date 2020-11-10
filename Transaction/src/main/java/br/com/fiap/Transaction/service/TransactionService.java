package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.TransactionCreateDTO;
import br.com.fiap.Transaction.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findAll();
    List<TransactionDTO> findByCliente(Long idCliente);
    TransactionDTO findById(Long id);
    TransactionDTO create(TransactionCreateDTO transactionCreateDTO);
    TransactionDTO update(Long id, TransactionCreateDTO transactionCreateDTO);

    void delete(Long id);
}
