package br.com.fiap.Transaction.repository;

import br.com.fiap.Transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAll();

    Optional<Transaction> findFirstById(Long id);

    @Query("from Transaction " +
            "where cliente = :cliente ")
    List<Transaction> findAllByCliente(Long cliente);

}
