package com.fiap.sts.stsfiap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_clientealuno")
public class Usuario {
    @Id
    @Column(name = "id_clientealuno")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String cpf;
    private String senha;

    public Usuario() {
        super();
    }
    public String getCpf() {
        return cpf;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
}
