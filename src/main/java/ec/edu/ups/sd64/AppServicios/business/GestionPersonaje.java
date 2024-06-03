package ec.edu.ups.sd64.AppServicios.business;

import ec.edu.ups.sd64.AppServicios.dao.PersonajeDao;
import ec.edu.ups.sd64.AppServicios.model.Personaje;

import java.util.List;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPersonaje{
	
	@Inject
	private PersonajeDao dao;

	private final Tracer tracer = GlobalTracer.get();

	public void guardar(Personaje personaje) {
		Span span = tracer.buildSpan("guardar").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
		dao.insert(personaje);
		}
		catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		}finally {
			span.finish();
		}
	}
	
	public void actualizar(Personaje personaje) {
		Personaje per = dao.getPersonaje(personaje.getCodigo());
		Span span = tracer.buildSpan("actualizar").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
        	if(per != null) {
        	    per.setNombre(personaje.getNombre());
        	    per.setEdad(personaje.getEdad());
        	    per.setAltura(personaje.getAltura());
        	    per.setPeso(personaje.getPeso());
        	    per.setGenero(personaje.getGenero());
        	    per.setRol(personaje.getRol());
        	    dao.update(per);
        	}
        }catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		}
        finally {
			span.finish();
		}
	}
	
	public Personaje getPersonaje(int codigo) throws Exception {
		Personaje per = dao.getPersonaje(codigo);
		Span span = tracer.buildSpan("getPersonaje").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
		if(per != null) {
			return per;
		}else {
			return null;
		}}catch(Exception e){
			span.log(e.getMessage());
			throw e;
		}finally{
			span.finish();
		}
	}
	
	public List<Personaje> getPersonajes() {
		Span span = tracer.buildSpan("listar").start();
		try{try (Scope scope = tracer.scopeManager().activate(span)) {
				return dao.getAll();
			
			}catch(Exception ex){
				span.log(ex.getMessage());
				throw ex;
			}
			finally{
				span.finish();
			}
		}catch(Exception e){
			return null;
		}
		
	}
	
	//@Override
	public void borrar(int codigo) {
		Span span = tracer.buildSpan("eliminar").start();
		try {
			Personaje per = dao.getPersonaje(codigo);
			
	        try (Scope scope = tracer.scopeManager().activate(span)) {
			if(per !=null) {
				dao.remove(per.getCodigo());
			}else {
				System.out.println("No existe");
			}
		}catch(Exception s) {
			s.printStackTrace();
		}}
		catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		}finally{
			span.finish();
		}
	}
}