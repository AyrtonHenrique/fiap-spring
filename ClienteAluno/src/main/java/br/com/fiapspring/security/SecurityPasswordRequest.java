package br.com.fiapspring.security;

public class SecurityPasswordRequest {
    public String cpf;
    public String password;

    public SecurityPasswordRequest(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }
}
