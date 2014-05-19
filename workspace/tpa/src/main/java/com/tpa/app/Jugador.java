package com.tpa.app;

import java.util.ArrayList;
import java.util.List;


public class Jugador {
	private int edad;
	private List<Infraccion> infracciones;
	private List<Jugador> amigos;
	private String email;
	

	public Jugador(int edad) {
		this.setEdad(edad);
		this.infracciones = new ArrayList<Infraccion>();
		this.setAmigos(new ArrayList<Jugador>());
	}

	public List<Infraccion> getInfracciones() {
		return this.infracciones;
	}
	public void agregarInfraccion(Infraccion infraccion)
	{
		getInfracciones().add(infraccion);
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Jugador> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(List<Jugador> amigos) {
		this.amigos = amigos;
	}
	
	public void avisarAmigos(Partido partido)
	{
		this.getAmigos().forEach(a -> partido.getMailSender().enviarMail(new Mail("Se anotó tu amigo.","Te queremos avisar que tu amigo a este partido.","",a.getEmail())));
	}
}
