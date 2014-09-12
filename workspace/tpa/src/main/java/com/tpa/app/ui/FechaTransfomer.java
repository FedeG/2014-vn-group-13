package com.tpa.app.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.uqbar.commons.collections.Transformer;


public final class FechaTransfomer implements Transformer<Partido, String> {
	
	@Override
	public String transform(Partido partido) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
		return partido.getFechaHora().format(format);
	}
}