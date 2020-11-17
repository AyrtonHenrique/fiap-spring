package br.com.fiap.Transaction.dto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ClienteAlunoRemote {
    @GET("{idCliente}")
    Call<ClienteAlunoRemoteDTO> buscarAluno(@Path("idCliente") Long idCliente);

    @GET("{idCliente}/cartao")
    Call<List<CartaoRemoteDTO>> buscarCartao(@Path("idCliente") Long idCliente);
}
