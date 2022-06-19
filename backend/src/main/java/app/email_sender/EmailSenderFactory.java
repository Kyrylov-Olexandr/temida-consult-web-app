package app.email_sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderFactory {

    public JavaMailSenderImpl createEmailSender(String mailService) {
        MailServiceEnum mailServiceEnum = MailServiceEnum.valueOf(mailService);
        switch (mailServiceEnum) {
            case GMAIL -> {
                return new GmailEmailSender();
            }
        }
        return null;
    }
}
