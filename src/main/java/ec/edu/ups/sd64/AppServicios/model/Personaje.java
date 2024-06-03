package ec.edu.ups.sd64.AppServicios.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Personaje {
    // Atributos básicos
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaje_seq")
    @SequenceGenerator(name = "personaje_seq", sequenceName = "personaje_seq", allocationSize = 1)
	private int codigo;
    private String nombre;
    private int edad;
    private double altura; // en metros
    private double peso; // en kilogramos
    private String genero;
    private String rol;
    
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método para mostrar información básica del personaje
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", altura=" + altura +
                ", peso=" + peso +
                ", genero='" + genero + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
	