package com.tpa.app.repo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Infraccion;
import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Persona;
import com.tpa.app.ui.PromedioTransformer;

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
		
		jugadormatias.agregarInfraccion(new Infraccion("por forro", fecha_y_hora));
		
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
	
	public List<Jugador> search(JugadorSearchParameter datosBusqueda) {
		
		List<Jugador> resultados = new ArrayList<Jugador>();
		PromedioTransformer promedioTrans = new PromedioTransformer();

		for (Jugador jugador : this.data) {
			if(startsWith(datosBusqueda.getComienzaCon(), jugador.getPersona().getNombre()) 
			   && match(datosBusqueda.getContiene(), jugador.getPersona().getApodo()) 
			   && esMayorOIgual(datosBusqueda.getHandicapDesde(), jugador.getHandicap()) 
			   && esMenorOIgual(datosBusqueda.getHandicapHasta(), jugador.getHandicap())
			   && esMayorOIgual(datosBusqueda.getPromedioDesde(), promedioTrans.transform(jugador))
			   && esMenorOIgual(datosBusqueda.getPromedioHasta(), promedioTrans.transform(jugador))
			   && compararBooleanoConListaVacia(datosBusqueda.getTuvoInfraccion(), jugador.getInfracciones())
			   && esMenorFecha(jugador.getPersona().getFechaNac(), datosBusqueda.getAntesDe())
			)
				resultados.add(jugador);
			}
		return resultados;
	}

	private boolean esMenorFecha(LocalDateTime ldt, Date fechaTope) {
		if (fechaTope == null) return true;
		Date fecha = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return fecha.before(fechaTope);
	}

	private boolean compararBooleanoConListaVacia(String SiNo, List<?> lista) {
		if (SiNo.equals("Indistinto")) return true;
		return SiNo.equals("No") == lista.isEmpty();
	}

	private boolean esMenorOIgual(Double tope, Double valor) {
		return tope == null || valor <= tope;
	}

	private boolean esMayorOIgual(Double base, Double valor) {
		return base == null || valor >= base;
	}

	protected boolean startsWith(String iniciales, String nombre){
		if (iniciales == null) return true;
		if (iniciales.length() > nombre.length()) return false;
		return match(iniciales, nombre.substring(0, iniciales.length()));
	}
	
	protected boolean match(Object expectedValue, Object realValue) {
		return expectedValue == null
			|| realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase());
	}
}
