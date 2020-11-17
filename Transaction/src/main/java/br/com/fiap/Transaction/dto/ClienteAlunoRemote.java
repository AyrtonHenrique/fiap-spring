package br.com.fiap.Transaction.dto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ClienteAlunoRemote {
    @GET()
    Call<ClienteAlunoDTO> buscarAluno();

    @GET("cartao")
    Call<ResponseBody> buscarCartao();
}
