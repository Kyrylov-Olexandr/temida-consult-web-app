package app.email_sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class GmailEmailSender extends JavaMailSenderImpl{

    public GmailEmailSender() {
        super();
        setHost("smtp.gmail.com");
        setPort(587);
        Properties props = getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }
}
