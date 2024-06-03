package ec.edu.ups.sd64.AppServicios.dao;

import java.util.List;

import ec.edu.ups.sd64.AppServicios.model.Personaje;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Stateless
public class PersonajeDao {
	
	@PersistenceContext
	private EntityManager em;
    
	public void insert(Personaje personaje) {
		em.merge(personaje);
	}
    
	public void update(Personaje personaje) {
		em.merge(personaje);
	}
    
	public void remove(int codigo) {
		Personaje personaje = em.find(Personaje.class, codigo);
		em.remove(personaje);
	}
    
	public Personaje read(String codigo) {
		Personaje personaje = em.find(Personaje.class, codigo);
		return personaje;
	}
    
	public List<Personaje> getAll(){
		String jpql = "SELECT c FROM Personaje c";
		Query q = em.createQuery(jpql, Personaje.class);
		return q.getResultList();
	}
    
	public Personaje getPersonaje(int codigo){
		String jpql = "SELECT p FROM Personaje p WHERE p.codigo = :codigo";
		Query q = em.createQuery(jpql, Personaje.class);
		q.setParameter("codigo", codigo);
		List<Personaje> personajes = q.getResultList();
		if(personajes.size()>0)
			return personajes.get(0);
		return null;
	}
}
