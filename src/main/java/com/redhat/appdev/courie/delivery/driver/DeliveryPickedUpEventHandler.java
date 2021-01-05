package com.redhat.appdev.courie.delivery.driver;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import com.redhat.appdev.courie.delivery.data.DeliveryRepository;
import com.redhat.appdev.courie.delivery.data.Redis;
import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.websocket.DeliveryUpdatedEvent;
import com.redhat.appdev.courie.delivery.websocket.DeliveryUpdatedEventSocket;
import com.redhat.appdev.courie.delivery.websocket.EventType;

@ApplicationScoped
public class DeliveryPickedUpEventHandler {

	private static final Logger LOG = Logger.getLogger(DeliveryPickedUpEventHandler.class);
	
	private DeliveryRepository deliveryRepository;
	private DeliveryUpdatedEventSocket socket;
	private ManagedExecutor executor;
	
	@Inject
	public DeliveryPickedUpEventHandler(@Redis DeliveryRepository deliveryRepository, DeliveryUpdatedEventSocket socket, 
			ManagedExecutor executor) {
		this.deliveryRepository = deliveryRepository;
		this.socket = socket;
		this.executor = executor;
	}
	
	@Incoming("delivery-pickedup")
	public void process(DeliveryPickedUpEvent event) {
		
		this.executor.execute(() -> {
			LOG.info("Delivery Picked Up: " + event);
			
			Optional<Delivery> deliveryMaybe = this.deliveryRepository.findById(event.getDeliveryId());
			
			if (deliveryMaybe.isPresent()) {
				Delivery delivery = deliveryMaybe.get();
				delivery.pickup();
				this.deliveryRepository.save(delivery);
				
				this.socket.broadcast(new DeliveryUpdatedEvent(deliveryMaybe.get(), EventType.DeliveryPickedUp, event.getPickupDate()));
			}
			
		});
	}
}
