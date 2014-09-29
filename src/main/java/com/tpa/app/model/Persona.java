package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.uqbar.commons.utils.Observable;

@Observable
public class Persona {

	private LocalDateTime fechaNac;
	private String email;
	private String nombre;
	private String apodo;
	private List<Persona> amigos;

	public Persona(LocalDateTime fechaNac, String email, String nombre) {
		this(fechaNac, email);
		this.nombre = nombre;
		this.setAmigos(new ArrayList<Persona>());
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
