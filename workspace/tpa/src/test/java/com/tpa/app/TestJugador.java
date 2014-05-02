package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJugador {

	Partido partido;
	Jugador jugador;
	Jugador jugador_condicional;
	
	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		partido = new Partido(fecha_y_hora, "Parque patricios", 10);
		jugador = new Jugador(20);
		jugador_condicional = new Jugador(20, (Partido partido) -> partido.getLugar() == "Parque patricios");
	}
	
	@Test
	public void testInscribirmeEstandarAUnPartido() {
		partido.inscribirEstadar(jugador);
		Assert.assertEquals("Se agrego un jugador estandar", 1, partido.getJugadoresEstandar().size());
	}

	@Test
	public void testInscribirmeSolidarioAUnPartido() {
		partido.inscribirSolidario(jugador);
		Assert.assertEquals("Se agrego un jugador solidario", 1, partido.getJugadoresSolidarios().size());
	}
	
	@Test
	public void testInscribirmeCondicionalAUnPartido() {
		partido.inscribirCondicional(jugador_condicional);
		Assert.assertEquals("Se agrego un jugador condicional", 1, partido.getJugadoresConCondicion().size());
	}
}
