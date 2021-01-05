package com.redhat.appdev.courie.delivery.domain;

public class LatLng {

	private String lat;
	private String lng;
	
	public LatLng(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}
	
}
