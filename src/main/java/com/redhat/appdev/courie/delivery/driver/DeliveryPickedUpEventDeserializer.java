package com.redhat.appdev.courie.delivery.driver;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DeliveryPickedUpEventDeserializer extends ObjectMapperDeserializer<DeliveryPickedUpEvent> {

	public DeliveryPickedUpEventDeserializer() {
		super(DeliveryPickedUpEvent.class);
	}

}
