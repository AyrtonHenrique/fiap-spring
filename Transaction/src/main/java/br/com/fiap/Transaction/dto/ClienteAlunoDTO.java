package br.com.fiap.Transaction.dto;

import java.time.LocalDateTime;

public class ClienteAlunoDTO {
    private long id;
    private String rm;
    private String turma;
    private String cpf;
    private String rg;
    private LocalDateTime dataNascimento;
    private Boolean isCliente;

    public ClienteAlunoDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public Boolean getIsCliente() {
        return isCliente;
    }

    public void setIsCliente(Boolean cliente) {
        isCliente = cliente;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}
