package br.com.fiap.Transaction.dto;

import java.time.LocalDate;

public class CartaoRemoteDTO {
    private Long id;
    private Long numerocartao;
    private String datavalidade;
    private Long codigoIdentificador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(Long numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getDatavalidade() {
        return datavalidade;
    }

    public void setDatavalidade(String datavalidade) {
        this.datavalidade = datavalidade;
    }

    public Long getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(Long codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }
}
