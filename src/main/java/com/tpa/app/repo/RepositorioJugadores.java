package com.tpa.app.repo;

import static com.tpa.app.db.EntityManagerHelper.getEntityManager;
//import static utn.dds.persistencia.futbol.db.EntityManagerHelper.getEntityManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.model.Infraccion;
import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Partido;
import com.tpa.app.model.PartidoMailSender;
import com.tpa.app.model.Persona;
import com.tpa.app.viewModel.BusquedaMultiple;

@Observable
public class RepositorioJugadores implements Serializable{

	private List<Jugador> data = new ArrayList<Jugador>();
	private static final RepositorioJugadores instance = new RepositorioJugadores();
	public static RepositorioJugadores getInstance() {
	    return instance;
	}

	@SuppressWarnings("unchecked")
	public RepositorioJugadores() {		
		
		data = getEntityManager()
				.createQuery("from Jugador")
				.getResultList();
		
		/*LocalDateTime fecha_y_hora = LocalDateTime.now();
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
		
		ezequiel.addAmigo(cecilia);
		ezequiel.addAmigo(jorge);
		ezequiel.addAmigo(federico);
		ezequiel.addAmigo(juana);
		ezequiel.addAmigo(pablo);
		cecilia.addAmigo(juana);
		cecilia.addAmigo(jorge);
		cecilia.addAmigo(federico);
		cecilia.addAmigo(ezequiel);
		cecilia.addAmigo(pablo);
		jorge.addAmigo(pablo);
		pablo.addAmigo(cecilia);
		pablo.addAmigo(jorge);
		pablo.addAmigo(federico);
		pablo.addAmigo(ezequiel);
		pablo.addAmigo(juana);
		federico.addAmigo(cecilia);
		federico.addAmigo(jorge);
		federico.addAmigo(juana);
		federico.addAmigo(ezequiel);
		federico.addAmigo(pablo);
		martin.addAmigo(cecilia);
		martin.addAmigo(jorge);
		martin.addAmigo(federico);
		martin.addAmigo(ezequiel);
		martin.addAmigo(pablo);
		matias.addAmigo(juana);
		matias.addAmigo(jorge);
		matias.addAmigo(federico);
		matias.addAmigo(ezequiel);
		matias.addAmigo(pablo);
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
		
		Partido partido1 = new Partido(fecha_y_hora, "Algun lugar", 10, new PartidoMailSender());
		
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now(), partido1));
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now().plusDays(7), partido1));
		jugadorjuana.agregarInfraccion(new Infraccion("Esta re loca", LocalDateTime.now().plusDays(14), partido1));
		jugadormatias.agregarInfraccion(new Infraccion("Por forro", fecha_y_hora, partido1));


		Inscripcion insc1 = new Inscripcion(jugadorezequiel, partido1, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc2 = new Inscripcion(jugadormariano, partido1, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc3 = new Inscripcion(jugadorcecilia, partido1, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc4 = new Inscripcion(jugadorfederico, partido1, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		Inscripcion insc5 = new Inscripcion(jugadorjuana, partido1, Inscripcion.PrioridadesInscripciones.ESTANDAR, null);
		
		partido1.inscribir(insc1);
		partido1.inscribir(insc2);
		partido1.inscribir(insc3);
		partido1.inscribir(insc4);
		partido1.inscribir(insc5);

		partido1.calificar(jugadorjuana, jugadormariano, 10, "un genio");
		partido1.calificar(jugadorcecilia, jugadormariano, 8, "un genio");
		partido1.calificar(jugadorezequiel, jugadormariano, 6, "mala persona");
		partido1.calificar(jugadorjuana, jugadorezequiel, 2, "un muerto");
		partido1.calificar(jugadorfederico, jugadorezequiel, 3, "mal arquero");
		partido1.calificar(jugadorcecilia, jugadorezequiel, 1, "muy raro");		
		
		jugadorezequiel.agregarPartidoJugado(partido1);
		jugadormariano.agregarPartidoJugado(partido1);
		
		this.create(jugadorcecilia);
		this.create(jugadorezequiel);
		this.create(jugadorjorge);
		this.create(jugadorpablo);
		this.create(jugadorfederico);
		this.create(jugadormartin);
		this.create(jugadormatias);
		this.create(jugadormariano);
		this.create(jugadorjuana);
		this.create(jugadorsofia);*/
		
		
		
	}
	
	// ********************************************************
	// ** Getter
	// ********************************************************
	
	public List<Jugador> getData(){
		return data;
	}

	// ********************************************************
	// ** Altas
	// ********************************************************

	public void create(Jugador jugador) {
		this.data.add(jugador);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************
	
	public List<Jugador> search(BusquedaMultiple filtros) {
		List<Jugador> resultados = new ArrayList<Jugador>();
		for (Jugador jugador : this.data) 
			if (filtros.evaluarJugador(jugador)) resultados.add(jugador);
		return resultados;
	}
}
