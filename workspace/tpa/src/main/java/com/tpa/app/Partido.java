package com.tpa.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;

public class Partido {

	private int cupo;
	private LocalDateTime fechaHora;
	private String lugar;
	private PriorityQueue<Inscripcion> inscripciones;
	private List<Inscripcion> equipoA;
	private List<Inscripcion> equipoB;
	private MailSender mailSender;
	private List<Calificacion> calificaciones;

	@Override
	public String toString() {
		return String.format("Partido {0} {1:dd/MM/yyyy HH:mm}", getLugar(),
				getFechaHora());
	}

	public Partido(LocalDateTime fecha_y_hora, String lugar, int cupo,
			MailSender sender) {
		this.mailSender = sender;
		this.setFechaHora(fecha_y_hora);
		this.setLugar(lugar);
		this.setCupo(cupo);
		this.inscripciones = new PriorityQueue<Inscripcion>((x, y) -> x
				.dameTuPrioridad().compareTo(y.dameTuPrioridad()));
		this.calificaciones = new ArrayList<Calificacion>();
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<Inscripcion> getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(List<Inscripcion> equipoB) {
		this.equipoB = equipoB;
	}

	public List<Inscripcion> getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(List<Inscripcion> equipoA) {
		this.equipoA = equipoA;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public PriorityQueue<Inscripcion> getInscripciones() {
		return this.inscripciones;
	}

	public void inscribir(Inscripcion inscripcion) {
		this.getInscripciones().add(inscripcion);
		inscripcion.jugador.avisarAmigos(this);
		if (verificarCupoCompleto())
			notificarAdministrador("Ya hay 10 jugadores inscriptos que pueden jugar.");
	}

	public Inscripcion obtenerInscripcionDe(Jugador jugador) {
		List<Inscripcion> inscrips = getInscripciones().stream()
				.filter(i -> i.jugador.equals(jugador))
				.collect(Collectors.toList());
		if (!inscrips.isEmpty())
			return inscrips.get(0);
		throw new RuntimeException(
				"El jugador no est� inscripto en este partido.");
	}

	public void darDeBaja(Jugador jugador, String motivo) {
		Inscripcion inscripcion = obtenerInscripcionDe(jugador);
		if (inscripcion == null)
			return;
		inscripcion.setActivo(false);
		jugador.agregarInfraccion(new Infraccion(motivo, LocalDateTime.now()));
		if (!verificarCupoCompleto())
			notificarAdministrador("Se dio de baja un jugador.");
	}

	public void darDeBaja(Jugador jugador, Jugador jugadorReemplaza) {
		Inscripcion inscripcion = obtenerInscripcionDe(jugador);
		if (inscripcion == null)
			return;
		inscripcion.setActivo(false);

		this.inscribir(new InscripcionEstandar(jugadorReemplaza));
	}

	public boolean verificarCupoCompleto() {
		int cantInscripcionesEstandar = (int) getInscripciones()
				.stream()
				.filter(i -> i.getActivo()
						&& i.dameTuPrioridad() == PrioridadesInscripciones.Estandar)
				.count();
		int cantInscripcionesSolidarias = (int) getInscripciones()
				.stream()
				.filter(i -> i.getActivo()
						&& i.dameTuPrioridad() == PrioridadesInscripciones.Solidaria)
				.count();
		int cantInscripcionesCondicionales = (int) getInscripciones()
				.stream()
				.filter(i -> i.getActivo()
						&& i.dameTuPrioridad() == PrioridadesInscripciones.Condicional)
				.count();
		return cantInscripcionesEstandar + cantInscripcionesSolidarias
				+ cantInscripcionesCondicionales >= 10;
	}

	public void notificarAdministrador(String mensaje) {
		getMailSender().enviarMail(
				new Mail("Notificaci�n", mensaje, "",
						"admin_partidos@dds.utn.frba"));
	}

	public void agregarCalificacion(Calificacion calificacion) {
		this.getCalificaciones().add(calificacion);
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
}