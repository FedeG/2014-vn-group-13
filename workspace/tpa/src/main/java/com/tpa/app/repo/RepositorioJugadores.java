package com.tpa.app.repo;

import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.oval.constraint.Length;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.Infraccion;
import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.MailSender;
import com.tpa.app.Partido;
import com.tpa.app.PartidoMailSender;
import com.tpa.app.Persona;
import com.tpa.app.Inscripcion.PrioridadesInscripciones;
import com.tpa.app.ui.ApodoTransformer;
import com.tpa.app.ui.NombreTransformer;
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
		NombreTransformer nombreTrans = new NombreTransformer();
		ApodoTransformer apodoTrans = new ApodoTransformer();
		PromedioTransformer promedioTrans = new PromedioTransformer();

		for (Jugador jugador : this.data) {
			if(startsWith(datosBusqueda.getComienzaCon(), nombreTrans.transform(jugador)) 
			   && match(datosBusqueda.getContiene(), apodoTrans.transform(jugador)) 
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
		if (SiNo.equals("")) return true;
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
