package br.com.fiap.Transaction.dto;

import java.time.LocalDate;

public class ClienteAlunoRemoteDTO {
    private long id;
    private String rm;
    private String turma;
    private String cpf;
    private String rg;

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getCliente() {
        return isCliente;
    }

    public void setCliente(Boolean cliente) {
        isCliente = cliente;
    }

    private String dataNascimento;
    private Boolean isCliente;
}
