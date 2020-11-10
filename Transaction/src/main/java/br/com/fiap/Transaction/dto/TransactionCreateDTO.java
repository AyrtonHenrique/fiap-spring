package br.com.fiap.Transaction.dto;

import br.com.fiap.Transaction.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionCreateDTO {
    private Long cliente;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;
    private Long cartao;

    public Long getCartao() { return cartao; }

    public void setCartao(Long cartao) { this.cartao = cartao; }

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
}

