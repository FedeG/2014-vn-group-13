package com.tpa.app;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

import com.tpa.app.Administrador;
import com.tpa.app.Partido;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAdministrador {
	
	private Administrador admin;
	
	@Before
	public void setUp() {
		admin = new Administrador();
	}

	//Test #1 - Crear partido
	@Test
	public void testCrearUnPartido() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		admin.crearPartido(fecha_y_hora, "lugar", 10);
	
		Assert.assertEquals("La cantidad de partidos es 1", 1, admin.getPartidos().size());
	}

	//Test #1 - Crear partido con los datos correctos
	@Test
	public void testCrearUnPartidoConLosDatosCompletosCorrectos() {
		LocalDateTime fecha_y_hora = LocalDateTime.of(2014, Month.APRIL, 1, 10, 45);
		String lugar = "futbolito de 5";
		int cupo = 10;
		admin.crearPartido(fecha_y_hora, lugar, cupo);

		Collection<Partido> partidos = admin.getPartidos();
		Partido partido = (Partido) partidos.toArray()[0];
		
		Assert.assertEquals(partido.getLugar(), lugar);
		Assert.assertEquals(partido.getCupo(), cupo);
		Assert.assertEquals(partido.getFechaHora(), fecha_y_hora);
	}
}
