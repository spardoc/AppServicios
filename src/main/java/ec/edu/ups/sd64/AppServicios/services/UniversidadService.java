package ec.edu.ups.sd64.AppServicios.services;

import java.util.List;

import ec.edu.ups.sd64.AppServicios.business.GestionUniversidad;
import ec.edu.ups.sd64.AppServicios.model.Universidad;
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

@Path("universidad")
public class UniversidadService {
	@Inject
	private GestionUniversidad gestionUniversidad;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Universidad universidad) {
		try {
			gestionUniversidad.guardar(universidad);
			System.out.println("UNIVERSIDAD GUARDADA");
			return Response.ok(universidad).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(500,"Error al guardar una universidad: "+ ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Universidad universidad) {
		try {
			gestionUniversidad.actualizar(universidad);
			return Response.ok(universidad).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(406,"Error al actualizar una universidad: "+ex.getMessage());
			return Response.status(Response.Status.NOT_MODIFIED).entity(error).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@QueryParam("id") int codigo) {
		try {
			gestionUniversidad.borrar(codigo);
			return Response.ok(codigo).build();
		}catch(Exception ex) {
			ErrorMessage error = new ErrorMessage(99,"Error al borrar la universidad: "+ ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer(@QueryParam("codigo") int codigo) {
		try{
			System.out.println("codigo " +  codigo);
			Universidad universidad = gestionUniversidad.getUniversidad(codigo);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(404, "Universidad inexistente: " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	@GET
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer2(@PathParam("codigo") int codigo) {
		try{
			System.out.println("codigo " +  codigo);
			Universidad universidad = gestionUniversidad.getUniversidad(codigo);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(404, "Universidad inexistente: " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getUniversidades(){
		System.out.println("Extrayendo universidades");
		List<Universidad> universidades = gestionUniversidad.getUniversidades();
		try {
			return Response.ok(universidades).build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(404, "No se registran universidades");
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}
}
