package com.tpa.app.repo;

import com.tpa.app.Jugador;

public class BusquedaPorComienzoDeNombre extends CriterioBusquedaJugador{
	String _iniciales;
	
	public BusquedaPorComienzoDeNombre(String iniciales)
	{
		this._iniciales = iniciales;
	}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		if (_iniciales == null) return true;
		String nombre = unJugador.getPersona().getNombre();
		if (_iniciales.length() > nombre.length()) return false;
		return match(_iniciales, nombre.substring(0, _iniciales.length()));
	}
}
