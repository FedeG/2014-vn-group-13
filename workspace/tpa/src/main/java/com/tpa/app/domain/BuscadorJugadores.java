package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;
import org.uqbar.commons.utils.Observable;
import com.tpa.app.Jugador;
import com.tpa.app.repo.RepositorioJugadores;

@Observable
public class BuscadorJugadores implements Serializable {
	
	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	private String comienzaCon = "", contiene = "";
	private Integer handicapDesde = null, handicapHasta = null;

	public void search() 
	{ 
		this.resultados = RepositorioJugadores.getInstance().search(
				this.comienzaCon, this.contiene, this.handicapDesde, this.handicapHasta
		); 
	}
	
	public Jugador getJugadorSeleccionado(){ return this.jugadorSeleccionado; }
	public void setJugadorSeleccionado(Jugador jugador){ this.jugadorSeleccionado = jugador; }

	public List<Jugador> getResultados(){ return resultados; }
	public void setResultados(List<Jugador> resultados) {this.resultados = resultados;}

	public String getComienzaCon() {return this.comienzaCon;}
	public void setComienzaCon(String iniciales) {this.comienzaCon = iniciales;	}
	
	public String getContiene() {return this.contiene;}
	public void setContiene(String letras) {this.contiene = letras;	}
	
	public Integer getHandicapDesde() {return this.handicapDesde;}
	public void setHandicapDesde(Integer handicap) {this.handicapDesde = handicap;}
	
	public Integer getHandicapHasta() {return this.handicapHasta;}
	public void setHandicapHasta(Integer handicap) {this.handicapHasta = handicap;}
}
