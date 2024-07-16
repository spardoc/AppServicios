package ec.edu.ups.sd64.AppServicios.business;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import ec.edu.ups.sd64.AppServicios.dao.UniversidadDao;
import ec.edu.ups.sd64.AppServicios.model.Universidad;

@Stateless
public class GestionUniversidad {
	
	@Inject
	private UniversidadDao dao;

	public void guardar(Universidad universidad) {
		dao.insert(universidad);
	}
	
	public void actualizar(Universidad universidad) {
		Universidad uni = dao.getUniversidad(universidad.getCodigo());
    	if(uni != null) {
    		uni.setNombre(universidad.getNombre());
    		uni.setUbicacion(universidad.getUbicacion());
    		uni.setNumEstudiantes(universidad.getNumEstudiantes());
    	    dao.update(uni);
    	}
	}
	
	public Universidad getUniversidad(int codigo) throws Exception {
		Universidad uni = dao.getUniversidad(codigo);
		if(uni != null) {
			return uni;
		}else {
			return null;
		}
	}
	
	public List<Universidad> getUniversidades() {
		return dao.getAll();
	}
	
	//@Override
	public void borrar(int codigo) {
		Universidad uni = dao.getUniversidad(codigo);
		if(uni !=null) {
			dao.remove(uni.getCodigo());
		}else {
			System.out.println("No existe");
		}
	}
}
