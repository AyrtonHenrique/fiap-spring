package br.com.fiap.Transaction.mail;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.fiap.Transaction.dto.ClienteAlunoRemote;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class MailConnectCreator {
    public ClienteAlunoDTO obterAluno(String urlAlunoCliente, Long idCliente) throws Exception {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlAlunoCliente + "/cliente/" + idCliente.toString())
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteAlunoRemote clienteAlunoRemote = retrofit.create(ClienteAlunoRemote.class);

        var responseBodyCall = clienteAlunoRemote.buscarAluno().execute();

        // URL url = new URL(urlAlunoCliente + "/cliente/" + idCliente.toString() );
        // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // conn.setRequestMethod("GET");
        // conn.setRequestProperty("Accept", "application/json");

        if (responseBodyCall.code() != 200) {
            throw new Exception("Erro ao buscar o Cliente");
        }

        // InputStream response = conn.getInputStream();
        // ObjectMapper mapper = new ObjectMapper();
        // ClienteAlunoDTO clienteAlunoDTO = mapper.readValue(response, ClienteAlunoDTO.class);
        return responseBodyCall.body();
    }

    public CartaoDTO obterCartao(String urlAlunoCliente, Long idCliente) throws IOException {
        URL url = new URL(urlAlunoCliente + "/cliente/" + idCliente.toString() + "/cartao");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            try {
                throw new Exception("Erro ao buscar o Cliente");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        InputStream response = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        CartaoDTO cartaoDTO = mapper.readValue(response, CartaoDTO.class);
        return cartaoDTO;
    }
}
