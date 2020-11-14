package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.TransactionCreateDTO;
import br.com.fiap.Transaction.dto.TransactionDTO;
import br.com.fiap.Transaction.entity.Transaction;
import br.com.fiap.Transaction.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository){ this.transactionRepository = transactionRepository; }
    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findByCliente(Long idCliente) {
        return transactionRepository.findAllByCliente(idCliente)
                .stream()
                .map(transaction -> new TransactionDTO((transaction)))
                .sorted(TransactionDTO::cartaoCompareTo)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO findById(Long id) {
        Transaction transaction = getTransaction(id);
        return new TransactionDTO(transaction);
    }

    @Override
    public TransactionDTO create(TransactionCreateDTO transactionCreateDTO) {
        Transaction transaction = new Transaction();
        transaction.setCliente(transactionCreateDTO.getCliente());
        transaction.setCartao(transactionCreateDTO.getCartao());
        transaction.setValor(transactionCreateDTO.getValor());
        transaction.setDataTransacao(transactionCreateDTO.getDataTransacao());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return new TransactionDTO(savedTransaction);
    }

    @Override
    public TransactionDTO update(Long id, TransactionCreateDTO transactionCreateDTO){
        Transaction transaction = getTransaction(id);

        transaction.setCartao(transactionCreateDTO.getCartao());
        transaction.setCliente(transactionCreateDTO.getCliente());
        transaction.setValor(transactionCreateDTO.getValor());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return new TransactionDTO(savedTransaction);
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = getTransaction(id);
        transactionRepository.delete(transaction);
    }

    private Transaction getTransaction(Long id){
        return transactionRepository.findFirstById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
