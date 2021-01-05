package com.redhat.appdev.courie.delivery.driver;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryDroppedOffEvent {

	private String deliveryId;
	private Date deliveredDate;
	
	@JsonCreator
	public DeliveryDroppedOffEvent(@JsonProperty("deliveryId") String deliveryId) {
		this.deliveryId = deliveryId;
		this.deliveredDate = new Date();
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}
	
}
