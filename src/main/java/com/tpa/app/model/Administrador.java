package com.tpa.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "administrador")
public class Administrador extends PersistentEntity {
	
	@OneToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	@OneToMany
	@JoinColumn(name = "administrador_id")
	private Collection<Partido> partidos;
	@Transient
	private MailSender mailSender;
	@Transient
	private List<Propuesta> propuestas;
	@Transient
	private GeneradorDeEquipos generadorDeEquipos;
	@Transient
	private List<Criterio> criterios;
	@Transient
	private List<Divisor> divisores;

	public Administrador()
	{
		
	}
	
	public Administrador(Persona persona, MailSender mailSender) {
		this.persona = persona;
		this.mailSender = mailSender;
		this.partidos = new ArrayList<Partido>();
		this.propuestas = new ArrayList<Propuesta>();
		this.divisores = new ArrayList<Divisor>();
		this.criterios = new ArrayList<Criterio>();
		this.generadorDeEquipos = new GeneradorDeEquipos();
	}

	public Collection<Partido> getPartidos() {
		return this.partidos;
	}

	public Partido crearPartido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		Partido partidoNuevo = new Partido(fecha_y_hora, lugar, cupo, this.mailSender);
		partidoNuevo.setAdministrador(this);
		this.partidos.add(partidoNuevo);
		return partidoNuevo;
	}

	public void generarEquiposTentativos(Partido partido) {
		List<Inscripcion> equipoA = partido
				.getInscripciones()
				.stream()
				.limit(5)
				.collect(Collectors.toCollection(() -> new ArrayList<Inscripcion>()));
		
		List<Inscripcion> equipoB = partido
				.getInscripciones()
				.stream()
				.skip(5)
				.collect(Collectors.toCollection(() -> new ArrayList<Inscripcion>()));

		partido.equiposAJugar(equipoA, equipoB);
	}

	public void agregarPropuesta(Propuesta propuesta) {
		this.getPropuestas().add(propuesta);
	}

	public List<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public GeneradorDeEquipos getGeneradorDeEquipos() {
		return generadorDeEquipos;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void agregarCriterio(Criterio criterio) {
		this.getCriterios().add(criterio);
	}

	public List<Divisor> getDivisores() {
		return divisores;
	}
	public void agregarDivisor(Divisor divisor) {
		this.getDivisores().add(divisor);
	}

	public Persona getPersona() {
		return persona;
	}
}
