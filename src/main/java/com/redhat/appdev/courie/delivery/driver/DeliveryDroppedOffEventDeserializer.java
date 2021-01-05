package com.redhat.appdev.courie.delivery.driver;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DeliveryDroppedOffEventDeserializer extends ObjectMapperDeserializer<DeliveryDroppedOffEvent> {

	public DeliveryDroppedOffEventDeserializer() {
		super(DeliveryDroppedOffEvent.class);
	}

}
