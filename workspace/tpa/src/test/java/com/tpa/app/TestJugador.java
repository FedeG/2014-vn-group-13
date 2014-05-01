package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJugador {

	Partido partido;
	Jugador jugador;
	
	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		partido = Partido.CrearPartidoFutbol5(fecha_y_hora, "Parque Patricios");
		jugador = new Jugador(20, new Estandar());
	}

	@Test (expected = YaEstaInscriptoAlPartidoExcepcion.class)
	public void testInscribirmeDosVecesAlMismoPartido() {
		partido.inscribirJugador(jugador);
		partido.inscribirJugador(jugador);
	}
	
	@Test
	public void testInscribirmeAUnPartido() {
		partido.inscribirJugador(jugador);
		Assert.assertEquals("La cantidad de partidos es 1", 1, partido.getJugadoresInscriptos().size());
	}

}
