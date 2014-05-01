package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPartido {
	
	Partido partido;
	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		partido = Partido.CrearPartidoFutbol5(fecha_y_hora, "Parque Patricios");

	}

	@Test (expected = CupoMaximoSuperadoExcepcion.class)
	public void testSuperarCupoPartido() {
		for (int i = 0; i < partido.getCupo() + 1 ; i++){ // Sumo 1 al cupo max del partido
			partido.inscribirJugador(new Jugador(20,new Estandar()));
		}
	}
	
	@Test 
	public void testCantidadDeJugadoresInscriptosPorTipo() {
		partido.inscribirJugador(new Jugador(20, new Estandar()));
		partido.inscribirJugador(new Jugador(20, new Estandar()));
		partido.inscribirJugador(new Jugador(20, new Condicional()));
		//partido.inscribirJugador(new Jugador(20, new Solidario()));
		Assert.assertEquals("La cantidad de Estandares es 2", 2, partido.getCantidadJugadoresInscriptosPorTipo(Estandar.class));
		Assert.assertEquals("La cantidad de Condicionales es 1", 1, partido.getCantidadJugadoresInscriptosPorTipo(Condicional.class));
		Assert.assertEquals("La cantidad de Solidarios es 0", 0, partido.getCantidadJugadoresInscriptosPorTipo(Solidario.class));
	}
}
