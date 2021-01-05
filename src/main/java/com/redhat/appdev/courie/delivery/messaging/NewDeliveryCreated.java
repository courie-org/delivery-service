package com.redhat.appdev.courie.delivery.messaging;

import java.util.Date;

import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.LatLng;

public class NewDeliveryCreated {

	private String id;
	private LatLng pickupLatLng;
	private String pickupAddress;
	private LatLng dropoffLatLng;
	private String dropoffAddress;
	private Date date;

	public NewDeliveryCreated(Delivery delivery) {
		this.id = delivery.getId();
		this.pickupLatLng = delivery.getPickupLatLng();
		this.pickupAddress = delivery.getPickupAddress();
		this.dropoffLatLng = delivery.getDropoffLatLng();
		this.dropoffAddress = delivery.getDropoffAddress();
		this.date = new Date();
	}

	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public LatLng getPickupLatLng() {
		return pickupLatLng;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public LatLng getDropoffLatLng() {
		return dropoffLatLng;
	}

	public String getDropoffAddress() {
		return dropoffAddress;
	}

}
