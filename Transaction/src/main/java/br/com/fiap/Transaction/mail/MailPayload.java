package br.com.fiap.Transaction.mail;

import java.util.ArrayList;
import java.util.List;

public class MailPayload {
    public MailPayload(){
        this.cartao = new ArrayList<>();
    }

    private Long idCliente;
    private String nomeCliente;
    private List<CardMailPayload> cartao;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<CardMailPayload> getCartao() {
        return cartao;
    }

    public void setCartao(List<CardMailPayload> cartao) {
        this.cartao = cartao;
    }

    public void addCartao(CardMailPayload cardMailPayload){
        this.cartao.add(cardMailPayload);
    }
}
