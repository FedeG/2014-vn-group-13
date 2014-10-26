package com.tpa.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.model.NoEstaInscriptoExcepcion;
import com.tpa.app.model.Inscripcion.PrioridadesInscripciones;

@SuppressWarnings("serial")
@Observable
@Entity
@Table(name = "partido")
public class Partido extends PersistentEntity implements Serializable {
	
	@OneToOne
	@JoinColumn(name = "administrador_id")
	private Administrador administrador;
	
	@OneToMany
	@JoinColumn(name = "partido_id")
	private List<Calificacion> calificaciones;
	
	@OneToMany
	@JoinColumn(name = "partido_id")
	private List<Inscripcion> inscripciones;
	
	@Column(name = "fecha_hora")
	private Timestamp fechaHora;

	@Transient
	private List<Inscripcion> equipoA;
	
	@Transient
	private List<Inscripcion> equipoB;
	
	@Transient
	private MailSender mailSender;
	
	private Boolean confirmado;
	private int cupo;
	private String lugar;
	
/*	private static Comparator<Inscripcion> comparator = new Comparator<Inscripcion>() {
		@Override
		public int compare(Inscripcion i1, Inscripcion i2) {
			return i1.getModalidad().dameTuPrioridad()
					- i2.getModalidad().dameTuPrioridad();
		}
	};*/
	@Override
	public String toString() {
		return String.format("Partido {0} {1:dd/MM/yyyy HH:mm}",
				this.getLugar(), this.getFechaHora());
	}

	public Partido()
	{
		
	}
	
	public Partido(Timestamp fecha_y_hora, String lugar, int cupo, MailSender sender) {
		this.mailSender = sender;
		this.fechaHora = fecha_y_hora;
		this.setLugar(lugar);
		this.setCupo(cupo);
		this.setInscripciones(new ArrayList<Inscripcion>(cupo));
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

	public void confirmar() {
		if (!this.verificarCupoCompleto())
			throw new UserException("El partido no tiene el cupo completo de jugadores");
		this.verificarConfirmacion();
		this.confirmado = true;
	}
	
	public void verificarConfirmacion(){
		if (this.getConfirmado())
			throw new PartidoYaConfirmadoExcepcion();
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

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public List<Inscripcion> getInscripciones() {
		return this.inscripciones;
	}

	public void inscribir(Inscripcion inscripcion) {
		this.verificarConfirmacion();
		this.getInscripciones().add(inscripcion);
		inscripcion.getJugador().avisarAmigos(this);
		if (this.verificarCupoCompleto())
			this.notificarAdministrador("Ya hay 10 jugadores inscriptos que pueden jugar.");
	}

	public Inscripcion obtenerInscripcionDe(Jugador jugador) {
		this.verificarJugadorIncripto(jugador);
		List<Inscripcion> inscrips = this.getInscripciones()
			.stream()
			.filter(inscripcion -> inscripcion.getJugador().equals(jugador))
			.collect(Collectors.toList());
		return inscrips.get(0);
	}

	public void darDeBaja(Jugador jugador, String motivo) {
		this.verificarConfirmacion();
		Inscripcion inscripcion = this.obtenerInscripcionDe(jugador);
		inscripcion.setActiva(false);
		jugador.agregarInfraccion(new Infraccion(motivo, Timestamp.from(Instant.now()), this));
		if (!this.verificarCupoCompleto())
			this.notificarAdministrador("Se dio de baja un jugador.");
	}

	public void darDeBaja(Jugador jugador, Jugador jugadorReemplaza) {
		this.verificarConfirmacion();
		Inscripcion inscripcion = this.obtenerInscripcionDe(jugador);
		inscripcion.setActiva(false);
		this.inscribir(new Inscripcion(jugador,this, inscripcion.getModalidad(), null)); // misma modalidad que el que se dio de baja
	}

	public boolean verificarCupoCompleto() {
		int cantInscripciones = (int) this.getInscripciones().size();
		return cantInscripciones >= 10;
	}

	private void notificarAdministrador(String mensaje) {
		Mail mail = new Mail("Notificacion", mensaje, "", "admin_partidos@dds.utn.frba");
		this.getMailSender().enviarMail(mail);
	}

	private void agregarCalificacion(Calificacion calificacion) {
		this.getCalificaciones().add(calificacion);
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public int contarInscripciones(PrioridadesInscripciones modalidad) {
		return (int) this.getInscripciones()
			.stream()
			.filter(inscripcion -> inscripcion.getModalidad() == modalidad)
			.count();
	}

	private boolean estaInscripto(Jugador jugador) {
		return this.getInscripciones()
			.stream()
			.anyMatch(i -> i.getJugador().equals(jugador));
	}

	private void verificarJugadorIncripto(Jugador jugador) {
		if (!this.estaInscripto(jugador))
			throw new NoEstaInscriptoExcepcion(jugador);
	}

	public void calificar(Jugador jugadorCalificador, Jugador jugadorACalificar, int nota, String critica) {
		this.verificarJugadorIncripto(jugadorACalificar);
		this.verificarJugadorIncripto(jugadorCalificador);
		Calificacion calificacion = new Calificacion(jugadorCalificador, this, nota, jugadorACalificar, critica);
		this.agregarCalificacion(calificacion);
	}

	public void equiposAJugar(List<Inscripcion> equipoA, List<Inscripcion> equipoB) {
		this.setEquipoA(equipoA);
		this.setEquipoB(equipoB);
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador adm) {
		this.administrador = adm;
	}
}