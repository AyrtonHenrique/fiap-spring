package br.com.fiap.Transaction.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import okhttp3.ResponseBody;
import org.apache.tomcat.jni.Local;
import retrofit2.Call;
import retrofit2.http.GET;

public class ClienteAlunoDTO {
    private long id;
    private String rm;
    private String turma;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private Boolean isCliente;

    public ClienteAlunoDTO(){}
    public ClienteAlunoDTO(ClienteAlunoRemoteDTO clienteAlunoRemoteDTO){
        this.setId(clienteAlunoRemoteDTO.getId());
        this.setRm(clienteAlunoRemoteDTO.getRm());
        this.setCpf(clienteAlunoRemoteDTO.getCpf());
        this.setRg(clienteAlunoRemoteDTO.getRg());
        this.setIsCliente(clienteAlunoRemoteDTO.getCliente());
        this.setDataNascimento(LocalDate.parse(clienteAlunoRemoteDTO.getDataNascimento()));
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
