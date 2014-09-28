package com.tpa.app.repo;

import com.tpa.app.Jugador;

public class BusquedaPorMatchearApodo extends CriterioBusquedaJugador{
	private String _clave;

	public BusquedaPorMatchearApodo(String clave) {
		this._clave = clave;
	}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		return match(_clave, unJugador.getPersona().getApodo()); 
	}
}
