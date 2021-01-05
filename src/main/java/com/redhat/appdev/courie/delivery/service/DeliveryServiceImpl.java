package com.redhat.appdev.courie.delivery.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.redhat.appdev.courie.delivery.data.DeliveryRepository;
import com.redhat.appdev.courie.delivery.data.Redis;
import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;
import com.redhat.appdev.courie.delivery.http.NewDeliveryRequest;
import com.redhat.appdev.courie.delivery.messaging.NewDeliveryCreated;
import com.redhat.appdev.courie.delivery.websocket.DeliveryUpdatedEvent;
import com.redhat.appdev.courie.delivery.websocket.DeliveryUpdatedEventSocket;
import com.redhat.appdev.courie.delivery.websocket.EventType;

@ApplicationScoped
public class DeliveryServiceImpl implements DeliveryService {

	private DeliveryRepository deliveryRepository;
	private Emitter<NewDeliveryCreated> newDeliveryEmitter;
	private DeliveryUpdatedEventSocket socket;
	
	@Inject
	public DeliveryServiceImpl(@Redis DeliveryRepository deliveryRepository, 
			@Channel("new-deliveries") Emitter<NewDeliveryCreated> newDeliveryEmitter,
			DeliveryUpdatedEventSocket socket) {
		this.deliveryRepository = deliveryRepository;
		this.newDeliveryEmitter = newDeliveryEmitter;
		this.socket = socket;
	}

	@Override
	public Set<Delivery> findWithStatusOf(DeliveryStatus status) {
		
		Set<Delivery> deliveries;
		
		if (status != null) {
			deliveries = this.deliveryRepository.findAllWithStatusOf(status);
		} else {
			deliveries = this.deliveryRepository.all();
		}
		return deliveries;
	}

	@Override
	public Optional<Delivery> findDeliveryById(String id) {
		return this.deliveryRepository.findById(id);
	}
	
	

	@Override
	@Transactional
	public Delivery createDelivery(NewDeliveryRequest request) {
		
		Delivery newDelivery = new Delivery(request.getPickupLatLng(), request.getPickupAddress(),
				request.getDropoffLatLng(), request.getDropoffAddress());
		
		this.deliveryRepository.add(newDelivery);
		
		DeliveryUpdatedEvent event = new DeliveryUpdatedEvent(newDelivery, EventType.NewDelivery, new Date());
		this.socket.broadcast(event);
		
		NewDeliveryCreated createdDeliveryEvent = new NewDeliveryCreated(newDelivery);
		this.newDeliveryEmitter.send(createdDeliveryEvent);
		
		return newDelivery;
	}
}
