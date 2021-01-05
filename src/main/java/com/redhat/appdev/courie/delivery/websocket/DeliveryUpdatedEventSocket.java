package com.redhat.appdev.courie.delivery.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;

@ServerEndpoint(value = "/events/deliveries/{deliveryId}", encoders= { DeliveryUpdatedEventEncoder.class })         
@ApplicationScoped
public class DeliveryUpdatedEventSocket {
	
	private static final Logger LOG = Logger.getLogger(DeliveryUpdatedEventSocket.class);
	
	Map<String, Session> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
    public void onOpen(@PathParam("deliveryId") String deliveryId, Session session) {
		session.setMaxIdleTimeout(0);
		sessions.put(generateKey(deliveryId, session.getId()), session);
    }

    @OnClose
    public void onClose(@PathParam("deliveryId") String deliveryId, Session session) {
    	
    	LOG.info("******************** Closing Socket!!!!!");
    	
    	sessions.remove(generateKey(deliveryId, session.getId()));
    }

    @OnError
    public void onError(@PathParam("deliveryId") String deliveryId, Session session, Throwable throwable) {
    	
    	LOG.info("******************** ERRROR Socket!!!!! - " + throwable.toString());
    	
    	sessions.remove(generateKey(deliveryId, session.getId()));
    }
    
    public void broadcast(DeliveryUpdatedEvent event) { 
    
    	sessions.forEach((key, session) -> {
    		
    		if (key.equals(generateKey(event.getDelivery().getId(), session.getId()))) {
    			session.getAsyncRemote().sendObject(event, result ->  {
                    if (result.getException() != null) {
                        LOG.error("Error sending location update to WS. Event: " + event, result.getException());
                    }
                });
    		}
    	});
    }
    
    private String generateKey(String deliveryId, String sessionId) {
    	return deliveryId + "||" + sessionId;
    }

}
