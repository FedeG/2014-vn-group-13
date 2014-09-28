package com.tpa.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.uqbar.commons.utils.Observable;

@Observable
public class Administrador {
	private Collection<Partido> partidos;
	private MailSender mailSender;
	private List<Propuesta> propuestas;
	private GeneradorDeEquipos generadorDeEquipos;
	private List<Criterio> criterios;
	private List<Divisor> divisores;

	public Administrador(MailSender mailSender) {
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
}
