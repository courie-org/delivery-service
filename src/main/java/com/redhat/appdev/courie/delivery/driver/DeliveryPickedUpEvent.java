package com.redhat.appdev.courie.delivery.driver;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryPickedUpEvent {

	private String deliveryId;
	private Date pickupDate;
	
	@JsonCreator
	public DeliveryPickedUpEvent(@JsonProperty("deliveryId") String deliveryId) {
		this.deliveryId = deliveryId;
		this.pickupDate = new Date();
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public Date getPickupDate() {
		return pickupDate;
	}
	
}
