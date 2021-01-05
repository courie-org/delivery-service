package com.redhat.appdev.courie.delivery.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeliveryUpdatedEventEncoder implements Encoder.Text<DeliveryUpdatedEvent> {

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(DeliveryUpdatedEvent object) throws EncodeException {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonValue = "";
		try {
			jsonValue = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonValue;
	}

}
