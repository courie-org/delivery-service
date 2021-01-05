package com.redhat.appdev.courie.delivery.data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;

@InMemory
public class InMemoryDeliveryRepository implements DeliveryRepository {

	private Set<Delivery> deliverStore = new HashSet<>();
	
	@Override
	public Optional<Delivery> findById(String id) {
		return this.deliverStore.stream()
				.filter(d -> d.getId().equals(id))
				.findFirst();
	}

	@Override
	public Set<Delivery> all() {
		return this.deliverStore;
	}

	@Override
	public void add(Delivery newDelivery) {
		this.deliverStore.add(newDelivery);
		
	}

	@Override
	public Set<Delivery> findAllWithStatusOf(DeliveryStatus status) {
		
		return this.deliverStore.stream()
				.filter(d -> d.getStatus().equals(status))
				.collect(Collectors.toSet());
	}

	@Override
	public void save(Delivery d) {
		this.deliverStore.remove(d);
		this.deliverStore.add(d);
		
	}

}
