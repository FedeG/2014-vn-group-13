package com.tpa.app.viewModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.tpa.app.model.Jugador;

public class BusquedaPorFechaNacimiento extends CriterioBusquedaJugador{
	private Date _fechaTope;

	public BusquedaPorFechaNacimiento(Date antesDe) {
		this._fechaTope = antesDe;
	}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		if (_fechaTope == null) return true;
		LocalDateTime ldt = unJugador.getPersona().getFechaNac();
		Date fecha = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return fecha.before(_fechaTope);
	}
}
