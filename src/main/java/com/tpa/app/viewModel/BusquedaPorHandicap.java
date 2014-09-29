package com.tpa.app.viewModel;

import com.tpa.app.model.Jugador;

public class BusquedaPorHandicap extends CriterioBusquedaJugador {
	Double _desde, _hasta;
	
	public BusquedaPorHandicap() {
	}
	
	public BusquedaPorHandicap(Double desde, Double hasta){
		this._desde = desde;
		this._hasta = hasta;
	}
	
	public void setDesde(double desde) { this._desde = desde;}
	public void setHasta(double hasta) { this._hasta = hasta;}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		boolean resultado = true;
		
		if(this._desde != null) resultado = resultado && this._desde <= unJugador.getHandicap();
		if(this._hasta != null) resultado = resultado && this._hasta >= unJugador.getHandicap();
		
		return resultado;
	}
}
