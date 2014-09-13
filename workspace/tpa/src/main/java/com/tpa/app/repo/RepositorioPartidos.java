package com.tpa.app.repo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Infraccion;
import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Persona;

@Observable
public class RepositorioPartidos implements Serializable {
	
	private static RepositorioPartidos instance;
	private List<Partido> data = new ArrayList<Partido>();

	public static synchronized RepositorioPartidos getInstance() {
		if (instance == null) {
			instance = new RepositorioPartidos();
		}
		return instance;
	}

	private RepositorioPartidos() {

		LocalDateTime fecha_y_hora = LocalDateTime.now();
		Partido partido1 = new Partido(fecha_y_hora, "Parque Patricios", 10);
		
		Persona cecilia = new Persona(fecha_y_hora, "cecilia", "cecilia", "chechu");
		Persona ezequiel = new Persona(fecha_y_hora, "ezequiel", "ezequiel", "pantalla tactil");
		Persona jorge = new Persona(fecha_y_hora, "jorge", "jorge", "pollerudo");
		Persona pablo = new Persona(fecha_y_hora, "pablo", "pablo", "baby on board");
		Persona federico = new Persona(fecha_y_hora, "federico", "federico", "tux");
		Persona sofia = new Persona(fecha_y_hora, "sofia", "sofia", "linda");
		Persona martin = new Persona(fecha_y_hora, "martin", "martin", "tincho");
		Persona matias = new Persona(fecha_y_hora, "matias", "matias", "forro");
		Persona mariano = new Persona(fecha_y_hora, "mariano", "mariano", "bueno");
		Persona juana = new Persona(fecha_y_hora, "juana", "juana", "la loca");

		juana.addAmigo(cecilia);
		juana.addAmigo(jorge);
		juana.addAmigo(federico);
		juana.addAmigo(ezequiel);
		juana.addAmigo(pablo);
		mariano.addAmigo(cecilia);
		mariano.addAmigo(jorge);
		mariano.addAmigo(federico);
		mariano.addAmigo(ezequiel);
		mariano.addAmigo(pablo);
		sofia.addAmigo(cecilia);
		sofia.addAmigo(jorge);
		sofia.addAmigo(federico);
		sofia.addAmigo(ezequiel);
		sofia.addAmigo(pablo);

		Jugador jugadorcecilia = new Jugador(cecilia,(double) 10);
		Jugador jugadorezequiel = new Jugador(ezequiel,(double) 1);
		Jugador jugadorjorge = new Jugador(jorge,(double) 0);
		Jugador jugadorpablo = new Jugador(pablo,(double) 6);
		Jugador jugadorfederico = new Jugador(federico,(double) 7);
		Jugador jugadorsofia = new Jugador(sofia,(double) 5);
		Jugador jugadormartin = new Jugador(martin,(double) 5);
		Jugador jugadormatias = new Jugador(matias,(double) 0);
		Jugador jugadormariano = new Jugador(mariano,(double) 10);
		Jugador jugadorjuana = new Jugador(juana,(double) 9);
		
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now()));
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now()));
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now()));
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now()));
		
		Inscripcion insc1 = new Inscripcion(jugadorcecilia, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc2 = new Inscripcion(jugadorezequiel, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc3 = new Inscripcion(jugadorjorge, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc4 = new Inscripcion(jugadorpablo, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc5 = new Inscripcion(jugadorfederico, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc6 = new Inscripcion(jugadorsofia, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc7 = new Inscripcion(jugadormartin, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc8 = new Inscripcion(jugadormatias, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc9 = new Inscripcion(jugadormariano, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc10 = new Inscripcion(jugadorjuana, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);

		partido1.inscribir(insc1);
		partido1.inscribir(insc2);
		partido1.inscribir(insc3);
		partido1.inscribir(insc4);
		partido1.inscribir(insc5);
		partido1.inscribir(insc6);
		partido1.inscribir(insc7);
		partido1.inscribir(insc8);
		partido1.inscribir(insc9);
		partido1.inscribir(insc10);
			
		Partido partido2 = new Partido(fecha_y_hora.plusMonths(2).plusDays(5).plusHours(3).plusMinutes(16), "Adrogue", 10);
		Partido partido3 = new Partido(fecha_y_hora.plusMonths(7).plusDays(15).plusHours(7).plusMinutes(36), "Lugano", 10);
		
		partido2.inscribir(insc1);
		partido2.inscribir(insc2);
		partido2.inscribir(insc3);
		partido2.inscribir(insc4);
		partido2.inscribir(insc5);
		partido2.inscribir(insc6);
		partido2.inscribir(insc7);
		partido2.inscribir(insc8);
		partido2.inscribir(insc9);
		partido2.inscribir(insc10);
		
		partido3.inscribir(insc1);
		partido3.inscribir(insc2);
		partido3.inscribir(insc3);
		partido3.inscribir(insc4);
		partido3.inscribir(insc5);
		partido3.inscribir(insc6);
		partido3.inscribir(insc7);
		partido3.inscribir(insc8);
		partido3.inscribir(insc9);
		partido3.inscribir(insc10);

		partido1.calificar(jugadorjuana, jugadormariano, 10, "un genio");
		partido1.calificar(jugadorcecilia, jugadormariano, 8, "un genio");
		partido1.calificar(jugadorezequiel, jugadormariano, 6, "mala persona");
		partido1.calificar(jugadorjuana, jugadorezequiel, 2, "un muerto");
		partido1.calificar(jugadorfederico, jugadorezequiel, 3, "mal arquero");
		partido1.calificar(jugadorcecilia, jugadorezequiel, 1, "muy raro");		
		jugadorezequiel.agregarPartidoJugado(partido1);
		jugadormariano.agregarPartidoJugado(partido1);

		this.create(partido1);
		this.create(partido2);
		this.create(partido3);
		
	}

	// ********************************************************
	// ** Altas y bajas
	// ********************************************************

	public void create(Partido partido) {
		this.data.add(partido);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************


	public List<Partido> search() {
		List<Partido> resultados = new ArrayList<Partido>();
		for (Partido partido : this.data) {
				resultados.add(partido);
		}
		return resultados;
		
	}

	public List<Persona> searchJugadoresEquipoA(Partido partidoSeleccionado) {
		List<Persona> resultados = new ArrayList<Persona>();
		if (partidoSeleccionado != null) {
			for (Inscripcion ins : partidoSeleccionado.getEquipoA()){
				resultados.add(ins.getJugador().getPersona());
			}
		}
		
		return resultados;
	}
	
	public List<Persona> searchJugadoresEquipoB(Partido partidoSeleccionado) {
		List<Persona> resultados = new ArrayList<Persona>();
		if (partidoSeleccionado != null) {
			for (Inscripcion ins : partidoSeleccionado.getEquipoB()){
				resultados.add(ins.getJugador().getPersona());
			}
		}
		return resultados;
	}

}
