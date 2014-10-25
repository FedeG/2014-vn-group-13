package com.tpa.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.mockito.Mock;

import com.tpa.app.model.Administrador;
import com.tpa.app.model.MailSender;
import com.tpa.app.model.Partido;
import com.tpa.app.model.Persona;
import com.tpa.app.model.Propuesta;
import com.tpa.app.model.Inscripcion.PrioridadesInscripciones;
import com.tpa.app.model.Propuesta.EstadoPropuesta;

public class TestPropuesta {

	Persona persona;
	Propuesta propuesta;
	Partido partido;
	Administrador admin;
	@Mock
	MailSender mailSenderMock;
	@Mock
	Persona personaMock;

	@Before
	public void setUp() {
		Timestamp fecha_y_hora = Timestamp.from(Instant.now());
		mailSenderMock = mock(MailSender.class);
		personaMock = mock(Persona.class);
		partido = new Partido(fecha_y_hora, "Parque patricios", 10,
				mailSenderMock);
		Timestamp fechaNac = Timestamp.valueOf(LocalDateTime.of(1991, 9, 26, 23, 25));
		persona = new Persona(fechaNac, "ceciliazgr@gmail.com");
		propuesta = new Propuesta(persona, PrioridadesInscripciones.ESTANDAR,
				partido);
		admin = new Administrador(personaMock, mailSenderMock);
	}

	// Test #1 - Aceptar una propuesta

	@Test
	public void testAceptarPropuesta() {
		propuesta.estasAprobada();
		Assert.assertEquals(EstadoPropuesta.Aprobada, propuesta.getEstado());
		Assert.assertEquals(partido.getInscripciones().size(), 1);
		Assert.assertEquals(
				true,
				partido.getInscripciones()
						.stream()
						.anyMatch(
								i -> i.getJugador().getPersona()
										.equals(persona)));
	}

	// Test #2 - Rechazar una propuesta

	@Test
	public void testRechazarPropuesta() {
		propuesta
				.estasRechazada("porque el jugador es malisimo y además le gustan las pantallas tactiles");
		Assert.assertTrue("El estado es rechazado", propuesta.getEstado()
				.equals(EstadoPropuesta.Rechazada));
		Assert.assertTrue(
				"El motivo es el que di",
				propuesta
						.getMotivo()
						.compareTo(
								"porque el jugador es malisimo y además le gustan las pantallas tactiles") == 0);
	}

}
