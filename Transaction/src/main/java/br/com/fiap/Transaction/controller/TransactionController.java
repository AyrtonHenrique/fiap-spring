package br.com.fiap.Transaction.controller;

import br.com.fiap.Transaction.dto.TransactionCreateDTO;
import br.com.fiap.Transaction.dto.TransactionDTO;
import br.com.fiap.Transaction.entity.Transaction;
import br.com.fiap.Transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDTO> listAll(){
        return transactionService.findAll();
    }

    @GetMapping("{id}")
    public TransactionDTO listOneTransaction(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@RequestBody TransactionCreateDTO transactionCreateDTO){
        return transactionService.create(transactionCreateDTO);
    }

    @PutMapping("{id}")
    public TransactionDTO update(@PathVariable Long id,
                                 @RequestBody TransactionCreateDTO transactionCreateDTO){
        return transactionService.update(id, transactionCreateDTO);
    }

    @GetMapping("cliente/{idCliente}")
    public List<TransactionDTO> listByCliente(@PathVariable Long idCliente){
        return transactionService.findByCliente(idCliente);
    }

    @DeleteMapping("{idTransacao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idTransacao){
        transactionService.delete(idTransacao);
    }

}
