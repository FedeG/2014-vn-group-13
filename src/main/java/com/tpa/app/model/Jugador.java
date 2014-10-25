package com.tpa.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "jugador")
public class Jugador extends PersistentEntity implements Serializable {

	@OneToOne
	@JoinColumn(name = "persona_id")
//	@Transient
	private Persona persona;
	private Double handicap;
	
	@Transient
	@OneToMany
	@JoinColumn(name = "jugador_id")
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	@Transient
	private List<Partido> partidosJugados = new ArrayList<Partido>();
	
	
	public Jugador()
	{
	
	}
	
	public Jugador(Persona persona) {
		this(persona, 0.0);
	}
	
	public Jugador(Persona persona, Double handicap) {
		this.persona = persona;
		this.handicap = handicap;
	}

	public List<Infraccion> getInfracciones() {
		return this.infracciones;
	}

	public void agregarInfraccion(Infraccion infraccion) {
		infraccion.setJugador(this);
		getInfracciones().add(infraccion);
	}

	public void proponer(Persona persona, Partido partido, Administrador admin, Inscripcion.PrioridadesInscripciones modalidad) {
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
		this.getPartidosJugados().add(0, partidoJugado);
	}

	public Double getHandicap() {
		return handicap;
	}

	public void setHandicap(Double handicap) {
		this.handicap = handicap;
	}
	
	
}
