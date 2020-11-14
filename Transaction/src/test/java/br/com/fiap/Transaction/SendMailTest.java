package br.com.fiap.Transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.Transaction.service.MailSenderService;

@SpringBootTest
public class SendMailTest {

	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Test
	void sendMail() {
		mailSenderService.sendSimpleMail("ceduardo.roque@gmail.com", "Email Test", "Este é um email de teste.");
	}
	
}
