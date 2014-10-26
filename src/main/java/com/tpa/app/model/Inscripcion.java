package com.tpa.app.model;

import java.io.Serializable;
import java.util.function.Predicate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "inscripcion")
public class Inscripcion extends PersistentEntity implements Serializable {
	public Inscripcion()
	{}
	public enum Equipo {
		NINGUNO,
		A,
		B	
	}
	public enum PrioridadesInscripciones implements TipoInscripcion {
		ESTANDAR {
			public int dameTuPrioridad() {
				return 0;
			}
		},
		SOLIDARIA {
			public int dameTuPrioridad() {
				return 1;
			}
		},
		CONDICIONAL {
			public int dameTuPrioridad() {
				return 2;
			}
		},
		;
	}
	@OneToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	private boolean activa;
	@OneToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;
	private PrioridadesInscripciones modalidad;
	@Transient
	private Predicate<Partido> condicion;
	@OneToOne
	@JoinColumn(name = "jugador_reemplazo_id")
	private Jugador jugadorReemplazo;
	private Equipo equipo;
	
	public Inscripcion(Jugador jugador, Partido partido, PrioridadesInscripciones modalidad, Predicate<Partido> condicion) {
		this.jugador = jugador;
		this.partido = partido;
		this.setCondicion(condicion);
		this.modalidad = modalidad;
		this.equipo = Equipo.NINGUNO;
	}

	public Jugador getJugador() {
		return jugador;
	}
	public Partido Partido() {
		return partido;
	}

	public boolean getActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Predicate<Partido> getCondicion() {
		return condicion;
	}

	public void setCondicion(Predicate<Partido> condicion) {
		this.condicion = condicion;
	}

	public PrioridadesInscripciones getModalidad() {
		return modalidad;
	}


	public void setModalidad(PrioridadesInscripciones modalidad) {
		this.modalidad = modalidad;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}	
}
