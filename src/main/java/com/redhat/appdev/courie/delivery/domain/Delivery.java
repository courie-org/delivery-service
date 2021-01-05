package com.redhat.appdev.courie.delivery.domain;

import java.util.UUID;

public class Delivery {

	private String id;
	private String assignedDriverId;
	private LatLng pickupLatLng;
	private String pickupAddress;
	private LatLng dropoffLatLng;
	private String dropoffAddress;
	private DeliveryStatus status;
	
	protected Delivery() { }
	
	public Delivery(LatLng pickupLatLng, String pickupAddress, 
			LatLng dropoffLatLng, String dropoffAddress) {
		this.pickupLatLng = pickupLatLng;
		this.pickupAddress = pickupAddress;
		this.dropoffLatLng = dropoffLatLng;
		this.dropoffAddress = dropoffAddress;
		this.id = UUID.randomUUID().toString();
		this.status = DeliveryStatus.NEW;
	}
	
	public String getId() {
		return id;
	}

	public String getAssignedDriverId() {
		return assignedDriverId;
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

	public DeliveryStatus getStatus() {
		return status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return "Delivery [id=" + id + ", assignedDriverId=" + assignedDriverId + ", pickupLatLng=" + pickupLatLng
				+ ", pickupAddress=" + pickupAddress + ", dropoffLatLng=" + dropoffLatLng + ", dropoffAddress="
				+ dropoffAddress + ", status=" + status + "]";
	}

	public void assignDriver(String driverId) {
		this.assignedDriverId = driverId;
		this.status = DeliveryStatus.ASSIGNED;
		
	}

	public void start() {
		this.status = DeliveryStatus.ACTIVE;
	}

	public void pickup() {
		this.status = DeliveryStatus.PICKED_UP;
		
	}

	public void dropOff() {
		this.status = DeliveryStatus.FINISHED;
		
	}
	
}
