package br.com.fiap.Transaction.service;

import br.com.fiap.Transaction.dto.CartaoDTO;
import br.com.fiap.Transaction.dto.ClienteAlunoDTO;
import br.com.fiap.Transaction.mail.MailConnectCreator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.List;

@Service
public class HttpClienteAlunoServiceImpl implements HttpClienteAlunoService {

    private final String urlAlunoCliente;
    private Environment environment;
    private MailConnectCreator _httpURLConnection;

    public HttpClienteAlunoServiceImpl(Environment environment, MailConnectCreator httpURLConnection) {
        this.environment = environment;
        this._httpURLConnection = httpURLConnection;
        this.urlAlunoCliente = environment.getProperty("clienteapi.app.url") + ':' + environment.getProperty("clienteapi.app.port") + environment.getProperty("clienteapi.app.context");
    }

    @Override
    public ClienteAlunoDTO getAluno(Long idCliente) {
        try {
            return _httpURLConnection.obterAluno(this.urlAlunoCliente, idCliente);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public CartaoDTO getCartao(Long idCliente, Long idCartao) throws Exception {
        List<CartaoDTO> lsCartaoDTO =_httpURLConnection.obterCartao(this.urlAlunoCliente, idCartao);

        CartaoDTO cartao =  lsCartaoDTO
                .stream()
                .filter( cartaoDTO -> cartaoDTO.getId() == idCartao )
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return cartao;
    }
}
