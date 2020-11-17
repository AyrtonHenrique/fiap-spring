package br.com.fiap.Transaction.mail;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;

@Component
public class MailConnectCreator {
    public ClienteAlunoDTO obterAluno(String urlAlunoCliente, Long idCliente) throws Exception {
        URL url = new URL(urlAlunoCliente + "/clientealuno/" + idCliente.toString() + "/buscaClienteAlunoId");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new Exception("Erro ao buscar o Cliente");
        }

        InputStream response = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ClienteAlunoDTO clienteAlunoDTO = mapper.readValue(response, ClienteAlunoDTO.class);
        return clienteAlunoDTO;
    }

    public CartaoDTO obterCartao(String urlAlunoCliente, Long idCliente) throws IOException {
        URL url = new URL(urlAlunoCliente + "/clientealuno/" + idCliente.toString() + "/buscaClienteAlunoId");
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
