package com.aws.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/amazon")
@Consumes("application/json")
@Produces("application/json")
public class AmazonService {
	private static Map<String, String> uidMap = new HashMap<>();

	static {
		uidMap.put("9822134", "Amazon EC2");
		uidMap.put("9822135", "AWS Redshift");
	}

	@GET
	@Path("/prod/{id}")
	public Response serviceName(@PathParam("id") String id) {
		return uidMap.get(id) != null ? Response.ok(new AWSProduct(id, uidMap.get(id))).build()
				: Response.ok("No Amazon Product Found !").build();
	}
}

class AWSProduct {
	String prodId;
	String prodName;

	public AWSProduct(String prodId, String prodName) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
	}

	public String getProdId() {
		return prodId;
	}

	public String getProdName() {
		return prodName;
	}

}
