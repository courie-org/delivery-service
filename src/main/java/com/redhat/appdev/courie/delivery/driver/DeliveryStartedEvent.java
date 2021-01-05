package com.redhat.appdev.courie.delivery.driver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryStartedEvent {

	private String deliveryId;
	
	@JsonCreator
	public DeliveryStartedEvent(@JsonProperty("deliveryId") String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryId() {
		return deliveryId;
	}
	
	
}
