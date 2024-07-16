package ec.edu.ups.sd64.AppServicios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Universidad {
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaje_seq")
    @SequenceGenerator(name = "personaje_seq", sequenceName = "personaje_seq", allocationSize = 1)
	private int codigo;
    private String nombre;
    private String ubicacion;
    private int numEstudiantes;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getNumEstudiantes() {
		return numEstudiantes;
	}
	public void setNumEstudiantes(int numEstudiantes) {
		this.numEstudiantes = numEstudiantes;
	}
	@Override
	public String toString() {
		return "Universidad [codigo=" + codigo + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", numEstudiantes="
				+ numEstudiantes + "]";
	}
}