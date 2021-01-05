package com.redhat.appdev.courie.delivery.driver;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DriverAssignedEventDeserializer extends ObjectMapperDeserializer<DriverAssignedEvent> {

	public DriverAssignedEventDeserializer() {
		super(DriverAssignedEvent.class);
	}

}
