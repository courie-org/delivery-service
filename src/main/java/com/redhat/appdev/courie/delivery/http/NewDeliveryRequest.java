package com.redhat.appdev.courie.delivery.http;

import com.redhat.appdev.courie.delivery.domain.LatLng;

public class NewDeliveryRequest {
	
	private LatLng pickupLatLng;
	private String pickupAddress;
	private LatLng dropoffLatLng;
	private String dropoffAddress;
	
	public NewDeliveryRequest(LatLng pickupLatLng, String pickupAddress, LatLng dropoffLatLng, String dropoffAddress) {
		this.pickupLatLng = pickupLatLng;
		this.pickupAddress = pickupAddress;
		this.dropoffLatLng = dropoffLatLng;
		this.dropoffAddress = dropoffAddress;
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
