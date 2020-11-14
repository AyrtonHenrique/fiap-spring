package br.com.fiap.Transaction.mail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionCardMailPayload {
    private Long idTransaction;
    private BigDecimal valor;

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
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

    private LocalDateTime dataTransacao;
}
