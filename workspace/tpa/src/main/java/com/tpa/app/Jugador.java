package com.tpa.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private int edad;
	private List<Infraccion> infrancciones;
	private List<Jugador> amigos;
	

	public Jugador(int edad) {
		this.setEdad(edad);
		this.infrancciones = new ArrayList<Infraccion>();
		this.setAmigos(new ArrayList<Jugador>());
	}

	public List<Infraccion> getInfracciones() {
		return this.infrancciones;
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

	public List<Jugador> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(List<Jugador> amigos) {
		this.amigos = amigos;
	}
	
	public void avisarAmigos(Partido partido)
	{
		//TODO : enviar mail a todos los amigos		
	}
}
