package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@SuppressWarnings("serial")
@Observable
@Entity
@Table(name = "persona")
public class Persona extends PersistentEntity implements Serializable {

	public Persona()
	{}
	
	@Column(nullable=false)

	private String nombre;
	private String email;
	private String apodo;
	
	@Column(name="fecha_nac")
	private Timestamp fechaNac;	

	@ManyToMany
	@JoinTable(name = "amigos_x_persona",
            joinColumns =
                @JoinColumn(name = "persona_id", referencedColumnName = "id"),
            inverseJoinColumns =

                @JoinColumn(name = "amigo_id", referencedColumnName = "id"))
	private List<Persona> amigos = new ArrayList<Persona>();

	public Persona(Timestamp fechaNac, String email, String nombre) {
		this(fechaNac, email);
		this.nombre = nombre;
		//this.setAmigos(new ArrayList<Persona>());
	}
	public Persona(Timestamp fechaNac, String email) {
		this.setFechaNac(fechaNac);
		this.setEmail(email);
		this.setAmigos(new ArrayList<Persona>());
	}

	public Persona(Timestamp fechaNac, String email, String nombre, String apodo) {
		this(fechaNac, email);
		this.nombre = nombre;
		this.apodo = apodo;
		this.setAmigos(new ArrayList<Persona>());
	}

	public Timestamp getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Timestamp fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public List<Persona> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Persona> amigos) {
		this.amigos = amigos;
	}

	public void addAmigo(Persona amigo) {
		this.amigos.add(amigo);
	}

	public void avisarAmigos(Partido partido) {
		this.getAmigos()
			.forEach(amigo -> partido
				.getMailSender()
				.enviarMail(
					new Mail("Se anoto tu amigo.","Te queremos avisar que tu amigo se inscribio a este partido.","", amigo.getEmail())
				)
			);
	}
	public String getApodo() {
		return apodo;
	}
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

}
