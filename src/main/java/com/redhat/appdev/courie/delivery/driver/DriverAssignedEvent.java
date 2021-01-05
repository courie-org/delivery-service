package com.redhat.appdev.courie.delivery.driver;

import com.redhat.appdev.courie.delivery.domain.LatLng;

public class DriverAssignedEvent {

	private String driverId;
	private String deliveryId;
	private LatLng pickupLatLng;
	private String pickupAddress;
	private LatLng dropoffLatLng;
	private String dropoffAddress;
	
	public DriverAssignedEvent(String driverId, String deliveryId, LatLng pickupLatLng, String pickupAddress,
			LatLng dropoffLatLng, String dropoffAddress) {
		this.driverId = driverId;
		this.deliveryId = deliveryId;
		this.pickupLatLng = pickupLatLng;
		this.pickupAddress = pickupAddress;
		this.dropoffLatLng = dropoffLatLng;
		this.dropoffAddress = dropoffAddress;
	}
	public String getDriverId() {
		return driverId;
	}
	public String getDeliveryId() {
		return deliveryId;
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
