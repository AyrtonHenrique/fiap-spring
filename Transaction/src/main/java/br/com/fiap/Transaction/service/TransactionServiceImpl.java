package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;
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
    private final HttpClienteAlunoService httpClienteAlunoService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  HttpClienteAlunoService httpClienteAlunoService){
        this.transactionRepository = transactionRepository;
        this.httpClienteAlunoService = httpClienteAlunoService;
    }

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
        this.verifyClienteCartao(transactionCreateDTO.getCliente(),transactionCreateDTO.getCartao());

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
        this.verifyClienteCartao(transactionCreateDTO.getCliente(),transactionCreateDTO.getCartao());

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

    private Boolean verifyClienteCartao(Long idCliente, Long idCartao){
        ClienteAlunoDTO clienteAlunoDTO = this.httpClienteAlunoService.getAluno(idCliente);
        if (clienteAlunoDTO == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado!");
        } else if (!clienteAlunoDTO.getIsCliente()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "O Cliente informado nao esta ativo!");
        } else {
            CartaoDTO cartaoDTO = this.httpClienteAlunoService.getCartao(idCliente, idCartao);

            if (cartaoDTO == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado!");
            } else {
                return true;
            }
        }
    }
}
