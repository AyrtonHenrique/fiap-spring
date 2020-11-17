package br.com.fiap.Transaction.mail;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.Transaction.dto.*;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class MailConnectCreator {
    public ClienteAlunoDTO obterAluno(String urlAlunoCliente, Long idCliente) throws Exception {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlAlunoCliente + "/cliente/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteAlunoRemote clienteAlunoRemote = retrofit.create(ClienteAlunoRemote.class);

        Response<ClienteAlunoRemoteDTO> responseBodyCall = clienteAlunoRemote.buscarAluno(idCliente).execute();

        if (responseBodyCall.code() != 200) {
            throw new Exception("Erro ao buscar o Cliente");
        }
        ClienteAlunoDTO cliente = new ClienteAlunoDTO(responseBodyCall.body());
        return cliente;
    }

    public List<CartaoDTO> obterCartao(String urlAlunoCliente, Long idCliente) throws Exception {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlAlunoCliente + "/cliente/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteAlunoRemote clienteAlunoRemote = retrofit.create(ClienteAlunoRemote.class);

        Response<List<CartaoRemoteDTO>> responseBodyCall = clienteAlunoRemote.buscarCartao(idCliente).execute();

        if (responseBodyCall.code() != 200) {
            throw new Exception("Erro ao buscar o Cartao");
        }

        List<CartaoDTO> cartaoDTO = responseBodyCall.body()
                .stream()
                .map(cartaoRemoteDTO -> new CartaoDTO(cartaoRemoteDTO))
                .collect(Collectors.toList());

        return cartaoDTO;//responseBodyCall.body();
    }
}
