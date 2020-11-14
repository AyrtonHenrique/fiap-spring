package br.com.fiap.Transaction.controller;


import br.com.fiap.Transaction.mail.MailPayload;
import br.com.fiap.Transaction.service.MailSenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("extract")
public class ExtractController {
    private final MailSenderService mailSenderService;
    public ExtractController(MailSenderService mailSenderService){
        this.mailSenderService = mailSenderService;
    }

    @GetMapping
    public List<MailPayload> getAllMailPayLoad(){
        return null;
    }

    @GetMapping("cliente/{idCliente}")
    public MailPayload getMailPayLoadByCliente(@PathVariable Long idCliente){
        return this.mailSenderService.getMailPayload(idCliente);
    }

    @PostMapping("cliente/{idCliente}/envio")
    public void sendMail(@PathVariable Long idCliente){
        this.mailSenderService.createConnection();
        this.mailSenderService.sendEmail(this.mailSenderService.getMailPayload(idCliente));
    }
}
