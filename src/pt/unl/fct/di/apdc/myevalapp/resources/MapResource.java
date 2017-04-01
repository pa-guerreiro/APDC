package pt.unl.fct.di.apdc.myevalapp.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/map")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MapResource {
	
	public MapResource() {}

}
