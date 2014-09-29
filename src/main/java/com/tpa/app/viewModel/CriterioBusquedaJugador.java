package com.tpa.app.viewModel;

import com.tpa.app.model.Jugador;

public abstract class CriterioBusquedaJugador {
	public abstract boolean evaluarJugador(Jugador unJugador);
	
	protected boolean match(Object expectedValue, Object realValue) {
		return expectedValue == null || realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase());
	}
}
