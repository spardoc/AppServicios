package ec.edu.ups.sd64.AppServicios.services;

import java.util.List;

import ec.edu.ups.sd64.AppServicios.business.GestionPersonaje;
import ec.edu.ups.sd64.AppServicios.model.Personaje;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("personaje")
public class PersonajeServices {
	
	@Inject
	private GestionPersonaje gestionPersonaje;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Personaje personaje) {
		try {
			gestionPersonaje.guardar(personaje);
			return Response.ok(personaje).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(500,"Error al guardar un personaje: "+ ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Personaje personaje) {
		try {
			gestionPersonaje.actualizar(personaje);
			return Response.ok(personaje).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(406,"Error al actualizar un personaje: "+ex.getMessage());
			return Response.status(Response.Status.NOT_MODIFIED).entity(error).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@QueryParam("id") int codigo) {
		try {
			gestionPersonaje.borrar(codigo);
			return Response.ok(codigo).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99,"Error al borrar el cliente: "+ ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer(@QueryParam("codigo") int codigo) {
		try{
			System.out.println("codigo " +  codigo);
			Personaje personaje = gestionPersonaje.getPersonaje(codigo);
			return Response.ok(personaje).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(404, "Personaje no existe: " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	@GET
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer2(@PathParam("codigo") int codigo) {
		try{
			System.out.println("codigo " +  codigo);
			Personaje personaje = gestionPersonaje.getPersonaje(codigo);
			return Response.ok(personaje).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(404, "Personaje no existe: " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getPersonajes(){
		System.out.println("Extrayendo personajes");
		List<Personaje> personajes = gestionPersonaje.getPersonajes();
		try {
			return Response.ok(personajes).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(404, "No se registran personajes");
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
}


