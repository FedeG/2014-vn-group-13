package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "persona")
public class Persona extends PersistentEntity {

	@Column(nullable=false)
	private String nombre;
	private String email;
	private String apodo;
	@Column(name="fecha_nac")
	private LocalDateTime fechaNac;	
	@ManyToMany
	@JoinTable(name = "amigos_x_persona",
            joinColumns =
                @JoinColumn(name = "persona_id", referencedColumnName = "Id"),
            inverseJoinColumns =
                @JoinColumn(name = "amigo_id", referencedColumnName = "Id"))
	private List<Persona> amigos = new ArrayList<Persona>();

	public Persona(LocalDateTime fechaNac, String email, String nombre) {
		this(fechaNac, email);
		this.nombre = nombre;
		//this.setAmigos(new ArrayList<Persona>());
	}
	public Persona(LocalDateTime fechaNac, String email) {
		this.setFechaNac(fechaNac);
		this.setEmail(email);
		this.setAmigos(new ArrayList<Persona>());
	}

	public Persona(LocalDateTime fechaNac, String email, String nombre, String apodo) {
		this(fechaNac, email);
		this.nombre = nombre;
		this.apodo = apodo;
		this.setAmigos(new ArrayList<Persona>());
	}

	public LocalDateTime getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(LocalDateTime fechaNac) {
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
