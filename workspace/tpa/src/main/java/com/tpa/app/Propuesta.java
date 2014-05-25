package com.tpa.app;

import java.time.LocalDateTime;

public class Propuesta {

	private Persona persona;
	private Inscripcion.PrioridadesInscripciones modalidad;
	private Partido partido;
	private boolean estado;
	private String motivo;
	private LocalDateTime fechaHoraRespuesta;

	public LocalDateTime getFechaHoraRespuesta() {
		return fechaHoraRespuesta;
	}

	public void setFechaHoraRespuesta(LocalDateTime fechaHoraRespuesta) {
		this.fechaHoraRespuesta = fechaHoraRespuesta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Inscripcion.PrioridadesInscripciones getModalidad() {
		return modalidad;
	}

	public void setModalidad(Inscripcion.PrioridadesInscripciones modalidad) {
		this.modalidad = modalidad;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Propuesta(Persona persona,Inscripcion.PrioridadesInscripciones modalidad, Partido partido) {
		this.modalidad = modalidad;
		this.partido = partido;
		this.persona = persona;
	}
	
	public void estasAprobada() {
		
		Jugador jugador = new Jugador();
		this.estado = true;
		
		//esto está muy feo, pero no se me ocurrió como hacerlo de otra forma
			
		if (this.modalidad.name().equals("Solidario")) {
			InscripcionSolidaria insc = new InscripcionSolidaria(jugador);
			this.partido.inscribir(insc);
		}

		if (this.modalidad.name().equals("Estandar")) {
			InscripcionEstandar insc = new InscripcionEstandar(jugador);
			this.partido.inscribir(insc);
		}
		
		if (this.modalidad.name().equals("Condicional")) {
			InscripcionCondicional insc = new InscripcionCondicional(jugador, null);
			this.partido.inscribir(insc);
		}	
		
		
	}
	
	public void estasRechazada(String motivo) {
		
		this.motivo = motivo;
		this.estado = false;
		this.fechaHoraRespuesta = LocalDateTime.now();
		
	}
	
	

}
