package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;
import br.com.fiap.Transaction.mail.MailConnectCreator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;

@Service
public class HttpClienteAlunoServiceImpl implements HttpClienteAlunoService {

    private final String urlAlunoCliente;
    private Environment environment;
    private MailConnectCreator _httpURLConnection;

    public HttpClienteAlunoServiceImpl(Environment environment, MailConnectCreator httpURLConnection) {
        this.environment = environment;
        this._httpURLConnection = httpURLConnection;
        this.urlAlunoCliente = environment
                .getProperty("clienteapi.app.url" + ':' + "clienteapi.app.port" + "clienteapi.app.context");
    }

    @Override
    public ClienteAlunoDTO getAluno(Long idCliente) {
        try {
            HttpURLConnection conn = _httpURLConnection != null
                    ? _httpURLConnection.create(this.urlAlunoCliente, idCliente)
                    : extracted(idCliente);
                    
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new Exception("Erro ao buscar o Cliente");
            } else {
                InputStream response = conn.getInputStream();

                ObjectMapper mapper = new ObjectMapper();
                ClienteAlunoDTO clienteAlunoDTO = mapper.readValue(response, ClienteAlunoDTO.class);

                return clienteAlunoDTO;
            }
        } catch (Exception e) {

        }
        return null;
    }

    private HttpURLConnection extracted(Long idCliente) throws MalformedURLException, IOException {
        URL url = new URL(this.urlAlunoCliente + "/clientealuno/" + idCliente.toString() + "/buscaClienteAlunoId");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn;
    }

    @Override
    public CartaoDTO getCartao(Long idCartao) {
        try {
            HttpURLConnection conn = extracted(idCartao);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new Exception("Erro ao buscar o Cliente");
            } else {
                InputStream response = conn.getInputStream();

                ObjectMapper mapper = new ObjectMapper();
                CartaoDTO cartaoDTO = mapper.readValue(response, CartaoDTO.class);

                return cartaoDTO;
            }
        } catch (Exception e) {

        }
        return null;
    }
}
