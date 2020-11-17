package br.com.fiap.Transaction.mail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class MailConnectCreator {
    public HttpURLConnection create(String urlAlunoCliente, Long idCliente) throws IOException {
        URL url = new URL(urlAlunoCliente + "/cliente/" + idCliente.toString() + "");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn;
    }
}
