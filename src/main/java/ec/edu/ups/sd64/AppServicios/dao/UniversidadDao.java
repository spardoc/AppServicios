package ec.edu.ups.sd64.AppServicios.dao;

import java.util.List;

import ec.edu.ups.sd64.AppServicios.model.Universidad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UniversidadDao {
	
	@PersistenceContext
	private EntityManager em;
    
	public void insert(Universidad universidad) {
		em.merge(universidad);
	}
    
	public void update(Universidad universidad) {
		em.merge(universidad);
	}
    
	public void remove(int codigo) {
		Universidad universidad = em.find(Universidad.class, codigo);
		em.remove(universidad);
	}
    
	public Universidad read(String codigo) {
		Universidad universidad = em.find(Universidad.class, codigo);
		return universidad;
	}
    
	public List<Universidad> getAll(){
		String jpql = "SELECT c FROM Universidad c";
		Query q = em.createQuery(jpql, Universidad.class);
		return q.getResultList();
	}
    
	public Universidad getUniversidad(int codigo){
		String jpql = "SELECT p FROM Universidad p WHERE p.codigo = :codigo";
		Query q = em.createQuery(jpql, Universidad.class);
		q.setParameter("codigo", codigo);
		List<Universidad> universidades = q.getResultList();
		if(universidades.size()>0)
			return universidades.get(0);
		return null;
	}
}
