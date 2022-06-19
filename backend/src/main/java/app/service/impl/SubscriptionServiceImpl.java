package app.service.impl;

import app.model.Subscription;
import app.repository.SubscriptionRepository;
import app.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }
}
