package com.tpa.app.repo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Persona;

@Observable
public class RepositorioPartidos implements Serializable {
	
	private List<Partido> data = new ArrayList<Partido>();
	private static final RepositorioPartidos instance = new RepositorioPartidos();
	public static RepositorioPartidos getInstance() {
	    return instance;
	}

	public RepositorioPartidos() {

		RepositorioJugadores jugadores = new RepositorioJugadores();
		List<Jugador> listajugadores = jugadores.getData(); 
		
		Jugador jugadorcecilia = this.searchJugador(listajugadores, "cecilia");
		Jugador jugadorezequiel = this.searchJugador(listajugadores, "ezequiel");
		Jugador jugadorjorge = this.searchJugador(listajugadores, "jorge");
		Jugador jugadorpablo = this.searchJugador(listajugadores, "pablo");
		Jugador jugadorfederico = this.searchJugador(listajugadores, "federico");
		Jugador jugadorsofia = this.searchJugador(listajugadores, "sofia");
		Jugador jugadormartin = this.searchJugador(listajugadores, "martin");
		Jugador jugadormatias = this.searchJugador(listajugadores, "matias");
		Jugador jugadormariano = this.searchJugador(listajugadores, "mariano");
		Jugador jugadorjuana = this.searchJugador(listajugadores, "juana");
		
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		
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

		Partido partido1 = new Partido(fecha_y_hora, "Parque Patricios", 10);
		Partido partido2 = new Partido(fecha_y_hora.plusMonths(2).plusDays(5).plusHours(3).plusMinutes(16), "Adrogue", 10);
		Partido partido3 = new Partido(fecha_y_hora.plusMonths(7).plusDays(15).plusHours(7).plusMinutes(36), "Lugano", 10);

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

	public Jugador searchJugador(List<Jugador> listajugadores, String nombre) {
		
		for (Jugador jugador : listajugadores) {
			if(jugador.getPersona().getNombre().equals(nombre)) 
				return jugador;
			}
		return null;
	}

}
