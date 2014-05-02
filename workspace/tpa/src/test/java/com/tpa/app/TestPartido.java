package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPartido {
	
	Partido partido;
	Jugador jugador;
	
	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		partido = new Partido(fecha_y_hora, "Parque Patricios", 10);
		jugador = new Jugador(20, (Partido partido) -> partido.getLugar() == "mi casa");
	}

	@Test
	public void testInscribirEstandarAUnPartido() {
		partido.inscribirEstadar(jugador);
		Assert.assertEquals("La cantidad de jugadores estandar es 1", 1, partido.getJugadoresEstandar().size());
	}

	@Test
	public void testInscribirSolidarioAUnPartido() {
		partido.inscribirSolidario(jugador);
		Assert.assertEquals("La cantidad de jugadores solidarios es 1", 1, partido.getJugadoresSolidarios().size());
	}
	
	@Test
	public void testInscribirCondicionalAUnPartido() {
		partido.inscribirCondicional(jugador);
		Assert.assertEquals("La cantidad de jugadores condicionales es 1", 1, partido.getJugadoresConCondicion().size());
	}
}
