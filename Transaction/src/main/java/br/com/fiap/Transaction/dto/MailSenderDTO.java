package br.com.fiap.Transaction.dto;

public class MailSenderDTO {
    private String usermail;
    private String userpassword;
    private String mailTo;
    private String hostSmtp;

    public MailSenderDTO(){ }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getHostSmtp() {
        return hostSmtp;
    }

    public void setHostSmtp(String hostSmtp) {
        this.hostSmtp = hostSmtp;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    private String smtpPort;

}
