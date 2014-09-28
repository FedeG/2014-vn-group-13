package com.tpa.app.repo;

import java.util.ArrayList;
import java.util.List;

import com.tpa.app.Jugador;

public class BusquedaMultiple extends CriterioBusquedaJugador {
	private List<CriterioBusquedaJugador> _compositeBusquedas;

	public BusquedaMultiple() {
		this._compositeBusquedas = new ArrayList<CriterioBusquedaJugador>();
	}
	
	public void agregarCriterio(CriterioBusquedaJugador criterio)
	{
		this._compositeBusquedas.add(criterio);
	}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		return this._compositeBusquedas.stream().allMatch(criterio -> criterio.evaluarJugador(unJugador));
	}
}
