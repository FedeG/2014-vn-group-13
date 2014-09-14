package com.tpa.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;
import com.tpa.app.Inscripcion.PrioridadesInscripciones;
import com.tpa.app.NoEstaInscriptoExcepcion;

@Observable
public class Partido {

	private int cupo;
	private LocalDateTime fechaHora;
	private String lugar;
	private PriorityQueue<Inscripcion> inscripciones;
	private List<Inscripcion> equipoA;
	private List<Inscripcion> equipoB;
	private MailSender mailSender;
	private List<Calificacion> calificaciones;
	private static Comparator<Inscripcion> comparator = new Comparator<Inscripcion>() {
		@Override
		public int compare(Inscripcion i1, Inscripcion i2) {
			return i1.getModalidad().dameTuPrioridad()
					- i2.getModalidad().dameTuPrioridad();
		}
	};
	private Boolean confirmado;

	@Override
	public String toString() {
		return String.format("Partido {0} {1:dd/MM/yyyy HH:mm}",
				this.getLugar(), this.getFechaHora());
	}

	public Partido(LocalDateTime fecha_y_hora, String lugar, int cupo,
			MailSender sender) {
		this.mailSender = sender;
		this.fechaHora = fecha_y_hora;
		this.setLugar(lugar);
		this.setCupo(cupo);
		this.setInscripciones(new PriorityQueue<Inscripcion>(cupo, comparator));
		this.calificaciones = new ArrayList<Calificacion>();
		this.confirmado = false;
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

	public void Confirmar() {
		if (!verificarCupoCompleto())
			throw new UserException(
					"El partido no tiene el cupo completo de jugadores");

		if (getConfirmado()) // SI ya esta confirmado, avisar
			throw new PartidoYaConfirmadoExcepcion();
		;

		confirmado = true;
	}

	public List<Inscripcion> getEquipoA() {
		return equipoA;
	}

	private void setEquipoA(List<Inscripcion> equipoA) {
		this.equipoA = equipoA;
	}

	public List<Inscripcion> getEquipoB() {
		return equipoB;
	}

	private void setEquipoB(List<Inscripcion> equipoB) {
		this.equipoB = equipoB;
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

	public PriorityQueue<Inscripcion> getInscripciones() {
		return this.inscripciones;
	}

	public void inscribir(Inscripcion inscripcion) {
		if (getConfirmado())
			throw new PartidoYaConfirmadoExcepcion();
		this.getInscripciones().add(inscripcion);
		inscripcion.getJugador().avisarAmigos(this);
		if (this.verificarCupoCompleto())
			this.notificarAdministrador("Ya hay 10 jugadores inscriptos que pueden jugar.");
	}

	public Inscripcion obtenerInscripcionDe(Jugador jugador) {
		this.verificarJugadorIncripto(jugador);
		List<Inscripcion> inscrips = this
				.getInscripciones()
				.stream()
				.filter(inscripcion -> inscripcion.getJugador().equals(jugador))
				.collect(Collectors.toList());
		return inscrips.get(0);
	}

	public void darDeBaja(Jugador jugador, String motivo) {
		if (getConfirmado())
			throw new PartidoYaConfirmadoExcepcion();
		Inscripcion inscripcion = this.obtenerInscripcionDe(jugador);
		inscripcion.setActivo(false);
		jugador.agregarInfraccion(new Infraccion(motivo, LocalDateTime.now()));
		if (!this.verificarCupoCompleto())
			this.notificarAdministrador("Se dio de baja un jugador.");
	}

	public void darDeBaja(Jugador jugador, Jugador jugadorReemplaza) {
		if (getConfirmado())
			throw new PartidoYaConfirmadoExcepcion();
		Inscripcion inscripcion = this.obtenerInscripcionDe(jugador);
		inscripcion.setActivo(false);
		this.inscribir(new Inscripcion(jugador, inscripcion.getModalidad(),
				null)); // misma modalidad que el que se dio de baja
	}

	public boolean verificarCupoCompleto() {
		int cantInscripciones = (int) this.getInscripciones().size();
		return cantInscripciones >= 10;
	}

	private void notificarAdministrador(String mensaje) {
		Mail mail = new Mail("Notificacion", mensaje, "",
				"admin_partidos@dds.utn.frba");
		this.getMailSender().enviarMail(mail);
	}

	private void agregarCalificacion(Calificacion calificacion) {
		this.getCalificaciones().add(calificacion);
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public int contarInscripciones(PrioridadesInscripciones modalidad) {
		return (int) this.getInscripciones().stream()
				.filter(inscripcion -> inscripcion.getModalidad() == modalidad)
				.count();
	}

	private boolean estaInscripto(Jugador jugador) {
		return this.getInscripciones().stream()
				.anyMatch(i -> i.getJugador().equals(jugador));
	}

	private void verificarJugadorIncripto(Jugador jugador) {
		if (!this.estaInscripto(jugador))
			throw new NoEstaInscriptoExcepcion(jugador);
	}

	public void calificar(Jugador jugadorCalificador,
			Jugador jugadorACalificar, int nota, String critica) {
		this.verificarJugadorIncripto(jugadorACalificar);
		this.verificarJugadorIncripto(jugadorCalificador);
		Calificacion calificacion = new Calificacion(nota, jugadorACalificar,
				critica);
		this.agregarCalificacion(calificacion);
	}

	public void equiposAJugar(List<Inscripcion> equipoA,
			List<Inscripcion> equipoB) {
		this.setEquipoA(equipoA);
		this.setEquipoB(equipoB);
	}

	public void setInscripciones(PriorityQueue<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}
}