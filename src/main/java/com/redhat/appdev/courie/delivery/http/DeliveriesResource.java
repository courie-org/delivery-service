package com.redhat.appdev.courie.delivery.http;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.redhat.appdev.courie.delivery.domain.Delivery;
import com.redhat.appdev.courie.delivery.domain.DeliveryStatus;
import com.redhat.appdev.courie.delivery.service.DeliveryService;


@Path("/deliveries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliveriesResource {

	private DeliveryService deliveryService;
	
	@Inject
	public DeliveriesResource(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	
	@GET
	public Response getDeliveries(@QueryParam("status") DeliveryStatus status) {
		return Response.ok(this.deliveryService.findWithStatusOf(status)).build();
	}
	
	@GET
	@Path("{id}")
	public Response getDelivery(@PathParam("id") String id) {
		
		Optional<Delivery> delivery = this.deliveryService.findDeliveryById(id);
		
		if (!delivery.isPresent()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(delivery.get()).build();
	}
	
	@POST
	public Response newDelivery(NewDeliveryRequest request) {
		
		Delivery newDelivery = this.deliveryService.createDelivery(request);
		
		return Response
				.ok(newDelivery)
				.status(Status.CREATED)
				.build();
	}
	
}
