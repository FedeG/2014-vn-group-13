package com.tpa.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private List<Infraccion> infracciones;
	private Persona persona;
	private List<Partido> partidosJugados;



	public Jugador(Persona persona) {
		this.infracciones = new ArrayList<Infraccion>();
		this.partidosJugados = new ArrayList<Partido>();
		this.persona = persona;
	}

	public List<Infraccion> getInfracciones() {
		return this.infracciones;
	}

	public void agregarInfraccion(Infraccion infraccion) {
		getInfracciones().add(infraccion);
	}

	public void proponer(Persona persona, Partido partido, Administrador admin,
			Inscripcion.PrioridadesInscripciones modalidad) {
		Propuesta propuesta = new Propuesta(persona, modalidad, partido);
		admin.agregarPropuesta(propuesta);
	}

	public void avisarAmigos(Partido partido) {
		this.getPersona().avisarAmigos(partido);
	}

	public Persona getPersona() {
		return persona;
	}

	public List<Partido> getPartidosJugados() {
		return partidosJugados;
	}

	public void agregarPartidoJugado(Partido partidoJugado) {
		this.getPartidosJugados().add(0, partidoJugado); //El ultimo partido jugado va a estar siempre al princio JOJO
	}

	/* Ezequiel estuvo aqui: Getters y setters para arena*/
	
	public String getNombre(){ return this.persona.getNombre();	}
	public void setNombre(String unNombre){ this.persona.setNombre(unNombre); }
	public String getApodo(){ return this.persona.getApodo();	}
	public void setApodo(String unNombre){ this.persona.setApodo(unNombre); }
	public Integer getHandicap(){ return 0; }
	public void setHandicap(Integer unNumero){ }
	public Integer getPromedio(){ return 0; }
	public void setPromedio(Integer unNumero){ }
}
