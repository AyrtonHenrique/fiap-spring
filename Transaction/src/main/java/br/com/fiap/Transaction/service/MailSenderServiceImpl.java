package br.com.fiap.Transaction.service;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import br.com.fiap.Transaction.dto.TransactionDTO;
import br.com.fiap.Transaction.mail.CardMailPayload;
import br.com.fiap.Transaction.mail.MailPayload;
import br.com.fiap.Transaction.mail.TransactionCardMailPayload;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    private JavaMailSender javaMailSender;
    private Environment environment;
    private final TransactionService transactionService;

    public MailSenderServiceImpl(JavaMailSender javaMailSender,
                                 Environment environment,
                                 TransactionService transactionService) {
        this.javaMailSender = javaMailSender;
        this.environment = environment;
        this.transactionService = transactionService;
    }

    @Override
    public JavaMailSender createConnection() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Read props from properties file
        try {
            String smtpHost = environment.getProperty("spring.mail.host");
            Integer smtpPort = Integer.parseInt(environment.getProperty("spring.mail.port"));
            String smtpUserName = environment.getProperty("spring.mail.username");
            String smtpPassword = environment.getProperty("spring.mail.password");
            String protocol = environment.getProperty("spring.mail.protocol");
			String auth = environment.getProperty("spring.mail.properties.mail.smtp.auth");
			String tls = environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable");

            // Create the JavaMailSenderImpl object
            try {
                mailSender.setHost(smtpHost);
                mailSender.setPort(smtpPort);
                mailSender.setUsername(smtpUserName);
                mailSender.setPassword(smtpPassword);

                // Other props
                Properties props = mailSender.getJavaMailProperties();
                props.put("mail.transport.protocol", protocol);
                props.put("mail.smtp.auth",  auth);
                props.put("mail.smtp.starttls.enable", tls);

            } catch (Exception e) {
                logger.error("Falha ao configurar MailSender");
                throw e;
            }
        } catch(NumberFormatException e) {
            logger.error("Erro ao ler arquivo de propriedades. Verifique os valores dos parâmetros de configuração do SMTP Server.");
            throw e;
        } catch(Exception e) {
            logger.error("Erro ao ler arquivo de propriedades");
            throw e;
        }
        return mailSender;
    }

    @Override
    public void sendEmail(MailPayload mailPayload) {
        SimpleMailMessage msg = new SimpleMailMessage();
        StringBuilder builder = new StringBuilder();
        String emailDestinoNotificacao = environment.getProperty("spring.mail.properties.mail.to");

        builder.append("************    Extrato de Transações!    ************").append("\n");
        builder.append("Nome do Cliente: " + mailPayload.getNomeCliente()).append("\n");
        builder.append("------------------------------------------------------").append("\n");

        mailPayload.getCartao().forEach(cardMailPayload -> {
            builder.append("Cartão numero:" + cardMailPayload.getIdCartao()).append("\n");
            builder.append("==========  Transacões realizadas  ==========").append("\n");

            cardMailPayload.getTransaction().forEach(transactionCardMailPayload -> {
                builder.append("Transação:" + transactionCardMailPayload.getIdTransaction().toString()).append("\n");
                builder.append("Data da transação: ")
                        .append(transactionCardMailPayload
                                .getDataTransacao()
                                .toLocalDate())
                        .append(" ")
                        .append(transactionCardMailPayload
                                .getDataTransacao()
                                .toLocalTime())
                        .append("     Valor")
                        .append(transactionCardMailPayload
                                .getValor()
                                .toString());
            });
        });

        msg.setTo(emailDestinoNotificacao);
        msg.setSubject("Extrato de transações Cliente" + mailPayload.getIdCliente().toString());
        msg.setText(builder.toString());

        JavaMailSenderImpl sender = (JavaMailSenderImpl) this.javaMailSender;
        
//        logger.info("Contem TLS?: " + sender.getJavaMailProperties().containsKey("mail.smtp.starttls.enable"));
//        logger.info("Properties : " + sender.getJavaMailProperties().toString());
//        logger.info("Porta      : " + sender.getPort());
//        logger.info("HOST       : " + sender.getHost());
     
        sender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
        sender.send(msg);
    }

    @Override
    public MailPayload getMailPayload(Long idCliente) {
        List<TransactionDTO> lsTransactions = this.transactionService.findByCliente(idCliente);
        MailPayload mailPayLoad = new MailPayload();
        mailPayLoad.setIdCliente(idCliente);
        CardMailPayload newCardMailPayload = null;
        TransactionCardMailPayload newTransaction = new TransactionCardMailPayload();

        Long idCartaoAtual = new Long(-1);

        for (TransactionDTO transactionDTO : lsTransactions) {

            if (idCartaoAtual.longValue() != transactionDTO.getCartao().longValue()) {
                if (idCartaoAtual > -1){
                    newCardMailPayload.addTransaction(newTransaction);
                    mailPayLoad.addCartao(newCardMailPayload);
                }
                newCardMailPayload = new CardMailPayload();
                newCardMailPayload.setIdCartao(transactionDTO.getCartao());
                idCartaoAtual = transactionDTO.getCartao();
            } else {
                newCardMailPayload.addTransaction(newTransaction);
            }

            newTransaction = new TransactionCardMailPayload();

            newTransaction.setIdTransaction(transactionDTO.getId());
            newTransaction.setDataTransacao(transactionDTO.getDataTransacao());
            newTransaction.setValor(transactionDTO.getValor());
        }

        return mailPayLoad;
    }

	@Override
	public void sendSimpleMail(String to, String subject, String body) {
		
		try {
			// Create Message 
			 SimpleMailMessage msg = new SimpleMailMessage();
	         StringBuilder builder = new StringBuilder();
	         builder.append(body);
	   
	         // Message Attributes
	         msg.setTo(to);
	         msg.setSubject(subject);
	         msg.setFrom("email@gmail.com");
	         msg.setText(builder.toString());

	         JavaMailSenderImpl sender = (JavaMailSenderImpl) this.javaMailSender;
	         sender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
	         // SEND!
	         sender.send(msg);			
		} catch( Exception e) {
			logger.error(e.getMessage());
		}
		
	}
}
