package com.tpa.app;

import java.util.function.Predicate;

public class Inscripcion {
	
	public enum PrioridadesInscripciones implements TipoInscripcion {
		ESTANDAR {
			public int dameTuPrioridad() {
				return 1;
			}
		},
		SOLIDARIA {
			public int dameTuPrioridad() {
				return 2;
			}
		},
		CONDICIONAL {
			public int dameTuPrioridad() {
				return 3;
			}
		},
		;

		
	}
	
	private PrioridadesInscripciones modalidad;
	private Jugador jugador;
	private boolean activo;
	private Predicate<Partido> condicion;
	
	public Inscripcion(Jugador jugador, PrioridadesInscripciones modalidad, Predicate<Partido> condicion) {
		this.jugador = jugador;
		this.setCondicion(condicion);
	}


	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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

	
}
