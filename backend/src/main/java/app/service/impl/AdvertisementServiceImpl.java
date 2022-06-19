package app.service.impl;


import app.email_sender.EmailSenderFactory;
import app.email_sender.GmailEmailSender;
import app.email_sender.MailServiceEnum;
import app.model.Advertisement;
import app.model.AdvertisementConfig;
import app.model.Subscription;
import app.repository.AdvertisementRepository;
import app.service.AdvertisementService;
import app.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final SubscriptionService subscriptionService;
    private final EmailSenderFactory emailSenderFactory;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository,
                                    SubscriptionService subscriptionService,
                                    EmailSenderFactory emailSenderFactory) {
        this.advertisementRepository = advertisementRepository;
        this.subscriptionService = subscriptionService;
        this.emailSenderFactory = emailSenderFactory;
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteById(long id) {
        advertisementRepository.deleteById(id);
    }

    @Override
    public Advertisement findByName(String name) {
        return advertisementRepository.findByName(name);
    }

    @Override
    public Optional<Advertisement> findById(long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public Collection<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public void send(Long id, String mailService) {
        Advertisement advertisement = findById(id).orElseThrow();
        AdvertisementConfig config = advertisement.getConfig();
        JavaMailSenderImpl mailSender = emailSenderFactory.createEmailSender(mailService);
        mailSender.setUsername(config.getEmail());
        mailSender.setPassword(config.getPassword());
        List<Subscription> subscriptions = subscriptionService.findAll();
        String[] recipients = subscriptions.stream()
                .map(Subscription::getEmail)
                .toArray(String[]::new);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject(advertisement.getSubject());
        message.setText(advertisement.getContent());
        mailSender.send(message);
    }
}
