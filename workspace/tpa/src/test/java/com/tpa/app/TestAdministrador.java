package com.tpa.app;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.time.Month;

import javafx.util.Pair;

import com.tpa.app.Administrador;
import com.tpa.app.Inscripcion.PrioridadesInscripciones;
import com.tpa.app.Partido;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestAdministrador {

	private Administrador admin;
	Partido partido;
	Jugador jugadorConJuego1;
	Inscripcion inscripcionJ1;
	Jugador jugadorConJuego2;
	Inscripcion inscripcionJ2;
	Jugador jugadorConJuego3;
	Inscripcion inscripcionJ3;
	ArrayList<Pair<Jugador, Integer>> valores;
	PorHandicap porHandicap;
	PorPromedio porPromedio;
	PorCalificacion porCalificacion;

	@Mock
	MailSender mailSenderMock;

	@Before
	public void setUp() {
		mailSenderMock = mock(MailSender.class);
		admin = new Administrador(mailSenderMock);

		partido = new Partido(null, null, 10, mailSenderMock);
		jugadorConJuego1 = new Jugador(new Persona(null, "pedro"));
		inscripcionJ1 = new Inscripcion(jugadorConJuego1,
				PrioridadesInscripciones.ESTANDAR, null);
		partido.inscribir(inscripcionJ1);

		jugadorConJuego2 = new Jugador(new Persona(null, "cecilia"));
		inscripcionJ2 = new Inscripcion(jugadorConJuego2,
				PrioridadesInscripciones.ESTANDAR, null);
		partido.inscribir(inscripcionJ2);

		jugadorConJuego3 = new Jugador(new Persona(null, "mauro"));
		inscripcionJ3 = new Inscripcion(jugadorConJuego3,
				PrioridadesInscripciones.ESTANDAR, null);
		partido.inscribir(inscripcionJ3);

		ArrayList<Pair<Jugador, Integer>> valores = new ArrayList<Pair<Jugador, Integer>>();
		Pair<Jugador, Integer> par1 = new Pair<Jugador, Integer>(
				jugadorConJuego1, 1);
		Pair<Jugador, Integer> par2 = new Pair<Jugador, Integer>(
				jugadorConJuego2, 2);
		Pair<Jugador, Integer> par3 = new Pair<Jugador, Integer>(
				jugadorConJuego3, 3);
		valores.add(par1);
		valores.add(par2);
		valores.add(par3);
		porHandicap = new PorHandicap();
		porPromedio = new PorPromedio();
		//-------------------------------------------------------------/
		partido.calificar(jugadorConJuego2, jugadorConJuego1, 9, "");
		partido.calificar(jugadorConJuego3, jugadorConJuego1, 8, ""); 
		partido.calificar(jugadorConJuego3, jugadorConJuego2, 10, "");
		partido.calificar(jugadorConJuego1, jugadorConJuego2, 8, "");
		partido.calificar(jugadorConJuego2, jugadorConJuego3, 8, "");
		partido.calificar(jugadorConJuego1, jugadorConJuego3, 7, "");
		jugadorConJuego1.agregarPartidoJugado(partido);
		jugadorConJuego2.agregarPartidoJugado(partido);
		jugadorConJuego3.agregarPartidoJugado(partido);
		//-------------------------------------------------------------/
		porCalificacion = new PorCalificacion(3);
	}

	// Test #1 - Crear partido
	// Test #1.1 - Con cualquier dato
	@Test
	public void testCrearUnPartido() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		admin.crearPartido(fecha_y_hora, "lugar", 10);

		Assert.assertEquals("La cantidad de partidos es 1", 1, admin
				.getPartidos().size());
	}

	// Test #1.2 - Crear partido con los datos correctos
	@Test
	public void testCrearUnPartidoConLosDatosCompletosCorrectos() {
		LocalDateTime fecha_y_hora = LocalDateTime.of(2014, Month.APRIL, 1, 10,
				45);
		String lugar = "futbolito de 5";
		int cupo = 10;
		admin.crearPartido(fecha_y_hora, lugar, cupo);

		ArrayList<Partido> partidos = (ArrayList<Partido>) admin.getPartidos();
		Partido partido = (Partido) partidos.toArray()[0];

		Assert.assertEquals(partido.getLugar(), lugar);
		Assert.assertEquals(partido.getCupo(), cupo);
		Assert.assertEquals(partido.getFechaHora(), fecha_y_hora);
	}

	@Test
	public void ordernarPorHandicapTresJugadoresEstandarElMejorJugadorEsElTres() {
		admin.agregarCriterio(porHandicap);
		Assert.assertEquals(
				"el primer/mejor jugador es el 3",
				jugadorConJuego3,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(0).getJugador());
	}

	@Test
	public void ordernarPorHandicapTresJugadoresEstandarElPeorJugadorEsElUno() {
		admin.agregarCriterio(porHandicap);
		Assert.assertEquals(
				"el ultimo/peor jugador es el 1",
				jugadorConJuego1,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(2).getJugador());
	}

	@Test
	public void ordernarPorPromedioTresJugadoresEstandarElMejorJugadorEsElDos() {
		admin.agregarCriterio(porPromedio);
		Assert.assertEquals(
				"el primer/mejor jugador es el 2",
				jugadorConJuego2,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(0).getJugador());
	}
	@Test
	public void ordernarPorPromedioTresJugadoresEstandarElPeorJugadorEsElTres() {
		admin.agregarCriterio(porPromedio);
		Assert.assertEquals(
				"el ultimo/peor jugador es el 3",
				jugadorConJuego3,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(2).getJugador());
	}
	
	@Test
	public void ordernarPorCalificacionTresJugadoresEstandarElPeorJugadorEsElTres() {
		ConfigurarOtroPartido();
		admin.agregarCriterio(porCalificacion);
		Assert.assertEquals(
				"el ultimo/peor jugador es el 3",
				jugadorConJuego3,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(2).getJugador());
	}
	@Test
	public void ordernarPorCalificacionTresJugadoresEstandarElMejorJugadorEsElUno() {
		ConfigurarOtroPartido();
		admin.agregarCriterio(porCalificacion);
		Assert.assertEquals(
				"el primer/mejor jugador es el 1",
				jugadorConJuego1,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(0).getJugador());
	}
	private void ConfigurarOtroPartido(){		
		Partido otroPartido = admin.crearPartido(LocalDateTime.now(), "Campito", 10);
		otroPartido.inscribir(new Inscripcion(jugadorConJuego1, PrioridadesInscripciones.ESTANDAR, null));
		otroPartido.inscribir(new Inscripcion(jugadorConJuego2, PrioridadesInscripciones.ESTANDAR, null));
		otroPartido.inscribir(new Inscripcion(jugadorConJuego3, PrioridadesInscripciones.ESTANDAR, null));
		otroPartido.calificar(jugadorConJuego2, jugadorConJuego1, 10, "");
		otroPartido.calificar(jugadorConJuego3, jugadorConJuego1, 10, ""); 
		otroPartido.calificar(jugadorConJuego1, jugadorConJuego2, 8, "");
		otroPartido.calificar(jugadorConJuego3, jugadorConJuego2, 10, ""); 
		otroPartido.calificar(jugadorConJuego2, jugadorConJuego3, 9, "");
		otroPartido.calificar(jugadorConJuego1, jugadorConJuego3, 10, ""); 
		jugadorConJuego1.agregarPartidoJugado(otroPartido);
		jugadorConJuego2.agregarPartidoJugado(otroPartido);
		jugadorConJuego3.agregarPartidoJugado(otroPartido);
	}

	
	@Test
 	public void generarEquipoJugadoresParesEImpares() {
		Partido futbol5 = new Partido(null, null, 10, mailSenderMock);
		ArrayList<Integer> indicesEquipoA = new ArrayList<Integer>() {
			{
				add(0);
				add(2);
				add(4);
				add(6);
				add(8);
			}
		};
		ArrayList<Integer> indicesEquipoB = new ArrayList<Integer>() {
			{
				add(1);
				add(3);
				add(5);
				add(7);
				add(9);
			}
		};
		ByIndex byIndex = new ByIndex("ParesImpares", indicesEquipoA,
				indicesEquipoB);

		for (Integer i = 0; i < 10; i++) {
			Persona personaMock = Mockito.mock(Persona.class);
			Mockito.when(personaMock.getNombre()).thenReturn(i.toString());
			futbol5.inscribir(new Inscripcion(new Jugador(personaMock),
					PrioridadesInscripciones.ESTANDAR, null));
		}
		//admin.getGeneradorDeEquipos().dividirEquipos(byIndex, futbol5);
		Assert.assertArrayEquals(
				futbol5.getEquipoA().stream()
						.map(i -> i.getJugador().getPersona().getNombre())
						.toArray(),
				indicesEquipoA.stream().map(i -> i.toString()).toArray());
		Assert.assertArrayEquals(
				futbol5.getEquipoB().stream()
						.map(i -> i.getJugador().getPersona().getNombre())
						.toArray(),
				indicesEquipoB.stream().map(i -> i.toString()).toArray());
	}
}
