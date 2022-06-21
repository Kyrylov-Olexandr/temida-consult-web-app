package app.service;

import app.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findAll();

    Subscription save(Subscription subscription);
}
