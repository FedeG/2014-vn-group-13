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

@SuppressWarnings("restriction")
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

		ArrayList<Criterio> criterios = new ArrayList<Criterio>();
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
		PorHandicap criterio1 = new PorHandicap(valores);
		criterios.add(criterio1);
		admin.setCriterios(criterios);

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

		Assert.assertEquals(
				"el primer/mejor jugador es el 3",
				jugadorConJuego3,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(0).getJugador());
	}

	@Test
	public void ordernarPorHandicapTresJugadoresEstandarElPeorJugadorEsElUno() {
		Assert.assertEquals(
				"el primer/mejor jugador es el 1",
				jugadorConJuego1,
				(admin.getGeneradorDeEquipos().ordenarJugadores(
						admin.getCriterios(), partido)).get(2).getJugador());
	}

}
