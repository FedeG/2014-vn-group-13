package com.tpa.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class Administrador extends PersistentEntity implements Serializable {
	public Administrador()
	{}
	
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

	public Administrador(Persona persona, MailSender mailSender) {
		this.persona = persona;
		this.mailSender = mailSender;
		this.partidos = new ArrayList<Partido>();
		this.propuestas = new ArrayList<Propuesta>();
		this.divisores = new ArrayList<Divisor>();
		this.criterios = new ArrayList<Criterio>();
		this.generadorDeEquipos = new GeneradorDeEquipos();
		this.agregarCriterio(new PorHandicap());
		this.agregarCriterio(new PorPromedio());
		
		ArrayList<Integer> indicesEquipoA = new ArrayList<Integer>() {{add(0);add(2);add(4);add(6);add(8);}};
		ArrayList<Integer> indicesEquipoB = new ArrayList<Integer>() {{add(1);add(3);add(5);add(7);add(9);}};
		ArrayList<Integer> indicesEquipoA2 = new ArrayList<Integer>() {{add(1);add(4);add(5);add(8);add(9);}};
		ArrayList<Integer> indicesEquipoB2 = new ArrayList<Integer>() {{add(0);add(2);add(3);add(6);add(7);}};
		
		ByIndex byIndex = new ByIndex("Pares/Impares", indicesEquipoA, indicesEquipoB);
		ByIndex byIndex2 = new ByIndex("1,4,5,8,9", indicesEquipoA2, indicesEquipoB2);
		
		this.agregarDivisor(byIndex);
		this.agregarDivisor(byIndex2);
	}

	public Collection<Partido> getPartidos() {
		return this.partidos;
	}

	public Partido crearPartido(Timestamp fecha_y_hora, String lugar, int cupo) {
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
