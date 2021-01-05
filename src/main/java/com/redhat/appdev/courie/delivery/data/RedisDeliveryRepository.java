package com.redhat.appdev.courie.delivery.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;

import io.quarkus.redis.client.RedisClient;

public class RedisDeliveryRepository implements DeliveryRepository {

	private static final String PREFIX = "delivery:";
	
	private RedisClient client;
	
	@Inject
	public RedisDeliveryRepository(RedisClient client) {
		this.client = client;
	}

	@Override
	public Optional<Delivery> findById(String id) {
		
		Gson gson = new GsonBuilder().create();
		String response = this.client.get(calculateId(id)).toString();
		
		Delivery d = gson.fromJson(response, Delivery.class);
		
		return Optional.ofNullable(d);
	}

	@Override
	public Set<Delivery> all() {
		Set<Delivery> deliveries = new HashSet<>();
		Gson gson = new GsonBuilder().create();
		
		this.client.keys(PREFIX+"*").forEach(r -> {
			
			String value = this.client.get(r.toString()).toString();
			Delivery d = gson.fromJson(value, Delivery.class);
			deliveries.add(d);
		});
		
		return deliveries;
	}

	@Override
	public void add(Delivery newDelivery) {
		Gson gson = new GsonBuilder().create();
		this.client.set(Arrays.asList(calculateId(newDelivery.getId()), gson.toJson(newDelivery)));
	}

	@Override
	public Set<Delivery> findAllWithStatusOf(DeliveryStatus status) {

		return all().stream()
			.filter(d -> d.getStatus().equals(status))
			.collect(Collectors.toSet());

	}

	@Override
	public void save(Delivery d) {
		Gson gson = new GsonBuilder().create();
		this.client.set(Arrays.asList(calculateId(d.getId()), gson.toJson(d)));
		
	}
	
	private String calculateId(String deliveryId) {
		return PREFIX + deliveryId;
	}

}
