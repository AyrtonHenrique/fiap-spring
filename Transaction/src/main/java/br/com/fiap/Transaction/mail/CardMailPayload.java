package br.com.fiap.Transaction.mail;

import java.util.ArrayList;
import java.util.List;

public class CardMailPayload {
    public CardMailPayload(){
        this.transaction = new ArrayList<>();
    }

    private Long idCartao;

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public List<TransactionCardMailPayload> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionCardMailPayload> transaction) {
        this.transaction = transaction;
    }

    public void addTransaction(TransactionCardMailPayload transactionCardMailPayload){
        this.transaction.add(transactionCardMailPayload);
    }

    private List<TransactionCardMailPayload> transaction;
}
