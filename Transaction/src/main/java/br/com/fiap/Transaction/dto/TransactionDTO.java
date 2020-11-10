package br.com.fiap.Transaction.dto;

import br.com.fiap.Transaction.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;

    private Long cliente;
    private Long cartao;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    public TransactionDTO(){}
    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.cliente = transaction.getCliente();
        this.cartao = transaction.getCartao();
        this.valor = transaction.getValor();
        this.dataTransacao = transaction.getDataTransacao();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Long getCartao() {
        return cartao;
    }

    public void setCartao(Long cartao) {
        this.cartao = cartao;
    }

}
