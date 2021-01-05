package com.redhat.appdev.courie.delivery.driver;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DeliveryStartedEventDeserializer extends ObjectMapperDeserializer<DeliveryStartedEvent> {

	public DeliveryStartedEventDeserializer() {
		super(DeliveryStartedEvent.class);
	}

}
