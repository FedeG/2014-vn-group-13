package com.tpa.app.repo;

import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.MailSender;
import com.tpa.app.Partido;
import com.tpa.app.PartidoMailSender;
import com.tpa.app.Persona;
import com.tpa.app.Inscripcion.PrioridadesInscripciones;

@Observable
public class RepositorioJugadores implements Serializable {
	
	private static RepositorioJugadores instance;
	private List<Jugador> data = new ArrayList<Jugador>();

	public static synchronized RepositorioJugadores getInstance() {
		if (instance == null) {
			instance = new RepositorioJugadores();
		}
		return instance;
	}

	private RepositorioJugadores() {		
		
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		
		Persona cecilia = new Persona(fecha_y_hora, "cecilia", "cecilia");
		Persona ezequiel = new Persona(fecha_y_hora, "ezequiel", "ezequiel");
		Persona jorge = new Persona(fecha_y_hora, "jorge", "jorge");
		Persona pablo = new Persona(fecha_y_hora, "pablo", "pablo");
		Persona federico = new Persona(fecha_y_hora, "federico", "federico");
		Persona sofia = new Persona(fecha_y_hora, "sofia", "sofia");
		Persona martin = new Persona(fecha_y_hora, "martin", "martin");
		Persona matias = new Persona(fecha_y_hora, "matias", "matias");
		Persona mariano = new Persona(fecha_y_hora, "mariano", "mariano");
		Persona juana = new Persona(fecha_y_hora, "juana", "juana");
		
		Jugador jugadorcecilia = new Jugador(cecilia);
		Jugador jugadorezequiel = new Jugador(ezequiel);
		Jugador jugadorjorge = new Jugador(jorge);
		Jugador jugadorpablo = new Jugador(pablo);
		Jugador jugadorfederico = new Jugador(federico);
		Jugador jugadorsofia = new Jugador(sofia);
		Jugador jugadormartin = new Jugador(martin);
		Jugador jugadormatias = new Jugador(matias);
		Jugador jugadormariano = new Jugador(mariano);
		Jugador jugadorjuana = new Jugador(juana);			
		
		this.create(jugadorcecilia);
		this.create(jugadorezequiel);
		this.create(jugadorjorge);
		this.create(jugadorpablo);
		this.create(jugadorfederico);
		this.create(jugadormartin);
		this.create(jugadormatias);
		this.create(jugadormariano);
		this.create(jugadorjuana);
		this.create(jugadorsofia);
		
	}

	// ********************************************************
	// ** Altas y bajas
	// ********************************************************

	public void create(Jugador jugador) {
		this.data.add(jugador);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************


	public List<Jugador> search() {
		
		List<Jugador> resultados = new ArrayList<Jugador>();

		for (Jugador jugador : this.data) {
			
				resultados.add(jugador);
			}
		
		return resultados;
		
	}


}
