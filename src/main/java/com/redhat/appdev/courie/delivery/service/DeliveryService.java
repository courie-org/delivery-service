package com.redhat.appdev.courie.delivery.service;

import java.util.Optional;
import java.util.Set;

import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;
import com.redhat.appdev.courie.delivery.http.NewDeliveryRequest;

public interface DeliveryService {

	public Set<Delivery> findWithStatusOf(DeliveryStatus status);

	public Optional<Delivery> findDeliveryById(String id);

	public Delivery createDelivery(NewDeliveryRequest request);

}
