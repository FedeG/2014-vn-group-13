package com.tpa.app.viewModel;

import java.util.List;

import com.tpa.app.model.Jugador;

public class BusquedaPorInfraccion extends CriterioBusquedaJugador {
	
	String _opcion;
	
	public BusquedaPorInfraccion(String opcion)
	{
		this._opcion = opcion;
	}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		if (_opcion == null) return true;
		List<?> infracciones = unJugador.getInfracciones();
		if (_opcion.equals("Indistinto")) return true;
		return _opcion.equals("No") == infracciones.isEmpty();
	}
	
	
}
