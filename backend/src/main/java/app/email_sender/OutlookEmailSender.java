package app.email_sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class OutlookEmailSender extends JavaMailSenderImpl {
    public OutlookEmailSender() {
        super();
        setHost("smtp-mail.outlook.com");
        setPort(587);
        Properties props = getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }
}
