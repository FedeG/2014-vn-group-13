package com.tpa.app.repo;

import com.tpa.app.Jugador;
import com.tpa.app.ui.PromedioTransformer;

public class BusquedaPorPromedio extends CriterioBusquedaJugador {
	
	Double _desde, _hasta;
	PromedioTransformer _promedioTrans;
	
	public BusquedaPorPromedio() {
		this._promedioTrans = new PromedioTransformer();
	}
	
	public BusquedaPorPromedio(Double desde, Double hasta){
		this();
		this._desde = desde;
		this._hasta = hasta;
	}
	
	public void setDesde(double desde) { this._desde = desde;}
	public void setHasta(double hasta) { this._hasta = hasta;}
	
	@Override
	public boolean evaluarJugador(Jugador unJugador) {
		double promedio = this._promedioTrans.transform(unJugador);
		boolean resultado = true;
		
		if(this._desde != null) resultado = resultado && this._desde <= promedio;
		if(this._hasta != null) resultado = resultado && this._hasta >= promedio;
		
		return resultado;
	}
}
