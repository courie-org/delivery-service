package com.redhat.appdev.courie.delivery.driver;

import java.util.Date;
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
public class DriverAssignedEventHandler {

	private static final Logger LOG = Logger.getLogger(DriverAssignedEventHandler.class);
	
	private DeliveryRepository deliveryRepository;
	private DeliveryUpdatedEventSocket socket;
	private ManagedExecutor executor;
	
	@Inject
	public DriverAssignedEventHandler(@Redis DeliveryRepository deliveryRepository, DeliveryUpdatedEventSocket socket,
			ManagedExecutor executor) {
		this.deliveryRepository = deliveryRepository;
		this.socket = socket;
		this.executor = executor;
	}
	
	@Incoming("driver-assigned")
	public void handle(DriverAssignedEvent event) {
		
		this.executor.execute(() -> {
			LOG.info("****************** Processing DriverAssignedEvent: " + event);
			
			Optional<Delivery> delivery = this.deliveryRepository.findById(event.getDeliveryId());
			
			LOG.info("The delivery: " + delivery);
			
			if (delivery.isPresent()) {
				Delivery d = delivery.get();
				d.assignDriver(event.getDriverId());
				
				this.deliveryRepository.save(d);
				
				this.socket.broadcast(new DeliveryUpdatedEvent(delivery.get(), EventType.DeliveryAssigned, new Date()));
			}
			
		});
	}
}
