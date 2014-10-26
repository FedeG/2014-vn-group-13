package com.tpa.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "calificacion")
public class Calificacion implements Serializable {
	public Calificacion()
	{}
	
	@Id
	@OneToOne
	@JoinColumn(name = "jugador_califica_id")
	private Jugador jugadorCalificador;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "partido_id")
	private Partido partido; 
	
	@Id	
	@OneToOne
	@JoinColumn(name = "jugador_calificado_id")
	private Jugador jugadorCalificado;

	private String critica;
	private int nota;

	public Calificacion(Jugador calificador, Partido partido, int nota, Jugador calificado, String critica) {
		this.jugadorCalificador = calificador;
		this.partido = partido;
		this.critica = critica;
		this.nota = nota;
		this.jugadorCalificado = calificado;
	}

	public Partido getPartido() {
		return partido;
	}
	public Jugador getJugadorCalificador() {
		return jugadorCalificador;
	}
	public Jugador getJugadorCalificado() {
		return jugadorCalificado;
	}
	public String getCritica() {
		return critica;
	}	
	public int getNota() {
		return nota;
	}
}
