package com.redhat.appdev.courie.delivery.data;

import java.util.Optional;
import java.util.Set;

import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;

public interface DeliveryRepository {

	public Optional<Delivery> findById(String id);
	public Set<Delivery> all();
	public void add(Delivery newDelivery);
	public Set<Delivery> findAllWithStatusOf(DeliveryStatus status);
	public void save(Delivery d);
}
