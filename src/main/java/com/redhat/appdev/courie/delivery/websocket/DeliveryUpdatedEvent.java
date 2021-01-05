package com.redhat.appdev.courie.delivery.websocket;

import java.util.Date;

import com.redhat.appdev.courie.delivery.domain.Delivery;

public class DeliveryUpdatedEvent {
	
	private Delivery delivery;
	private EventType type;
	private Date updateDate;
	
	public DeliveryUpdatedEvent(Delivery delivery, EventType type, Date updateDate) {
		this.delivery = delivery;
		this.type = type;
		this.updateDate = updateDate;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public EventType getType() {
		return type;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}
