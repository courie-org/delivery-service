package com.redhat.appdev.courie.delivery.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.quarkus.redis.client.RedisClient;

public class DeliveryRepositoryFactory {
	
	@Produces
	@InMemory
	@ApplicationScoped
	public DeliveryRepository getInMemoryDeliveryRepo() {
		return new InMemoryDeliveryRepository();
	}
	
	@Produces
	@Redis
	@ApplicationScoped
	public DeliveryRepository getRedisDeliveryRepo(RedisClient client) {
		return new RedisDeliveryRepository(client);
	}
	

}
