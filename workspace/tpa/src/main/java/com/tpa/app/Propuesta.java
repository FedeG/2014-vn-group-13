package com.tpa.app;

import java.time.LocalDateTime;

public class Propuesta {

	public enum EstadoPropuesta {
		Pendiente, Aprobada, Rechazada
	}

	private Persona persona;
	private Inscripcion.PrioridadesInscripciones modalidad;
	private Partido partido;
	private EstadoPropuesta estado;
	private String motivo;
	private LocalDateTime fechaHoraRespuesta;

	public Propuesta(Persona persona,
			Inscripcion.PrioridadesInscripciones modalidad, Partido partido) {
		this.setEstado(EstadoPropuesta.Pendiente);
		this.setModalidad(modalidad);
		this.setPartido(partido);
		this.setPersona(persona);
	}

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

	public EstadoPropuesta getEstado() {
		return estado;
	}

	public void setEstado(EstadoPropuesta estado) {
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

	public void estasAprobada() {
		Jugador jugador = new Jugador(this.persona);
		this.estado = EstadoPropuesta.Aprobada;
		this.fechaHoraRespuesta = LocalDateTime.now();
		Inscripcion insc = FabricaInscripciones.crearInscripcion(
				this.modalidad, jugador);
		this.partido.inscribir(insc);
	}

	public void estasRechazada(String motivo) {
		this.motivo = motivo;
		this.estado = EstadoPropuesta.Rechazada;
		this.fechaHoraRespuesta = LocalDateTime.now();
	}

}
