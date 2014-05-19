package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;;

public class TestPartido {
	
	Partido partido;
	@Mock
	MailSender mailSenderMock;

	Jugador jugador;
	
	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		mailSenderMock = mock(MailSender.class);
		partido = new Partido(fecha_y_hora, "Parque Patricios", 10, mailSenderMock);
		jugador = new Jugador(20);

	}
	
	@Test 
	public void testNotificarAlAdministrador() {
		for(int i = 0 ; i < 10; i++)
			partido.inscribir(new InscripcionEstandar(new Jugador(20)));
		verify(mailSenderMock, times(1)).enviarMail(any(Mail.class));
		//Assert.assertEquals("La cantidad de jugadores condicionales es 1", 1, partido.getInscripciones().size());
	}
}
