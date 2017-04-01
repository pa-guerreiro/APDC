package pt.unl.fct.di.apdc.myevalapp.resources;

import java.util.Date;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import pt.unl.fct.di.apdc.myevalapp.util.RegisterData;

@Path("/register")
public class RegisterResource {

	/**
	 * A logger object.
	 */
	private static final Logger LOG = Logger.getLogger(RegisterResource.class.getName());
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public RegisterResource() {
	} // Nothing to be done here...
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doRegistration(RegisterData data) {
		
		if( ! data.validRegistration() ) {
			return Response.status(Status.BAD_REQUEST).entity("Missing or wrong parameter.").build();
		}
		
		Transaction txn = datastore.beginTransaction();
		try {
			// If the entity does not exist an Exception is thrown. Otherwise,
			Key userKey = KeyFactory.createKey("User", data.username);
			datastore.get(userKey);
			txn.rollback();
			return Response.status(Status.BAD_REQUEST).entity("User already exists.").build(); 
		} catch (EntityNotFoundException e) {
			Entity user = new Entity("User", data.username);
			user.setProperty("user_email", data.email);
			user.setProperty("user_homephone", data.homePhone);
			user.setProperty("user_mobilephone", data.mobilePhone);
			user.setProperty("user_street", data.street);
			user.setProperty("user_locationdescription", data.locationDescription);
			user.setProperty("user_city", data.city);
			user.setProperty("user_cp", data.cp);
			user.setProperty("user_nif", data.nif);
			user.setProperty("user_cc", data.cc);
			user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
			user.setUnindexedProperty("user_creation_time", new Date());
			datastore.put(txn,user);
			LOG.info("User registered " + data.username);
			txn.commit();
			return Response.ok("{}").build();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}	
	}
}
