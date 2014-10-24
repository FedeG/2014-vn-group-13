package com.tpa.app.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name = "infraccion")
public class Infraccion extends PersistentEntity {
	
	private String motivo;
	private LocalDateTime momento;
	@OneToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	@OneToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;
	
	public Infraccion(String motivo, LocalDateTime momento, Partido partido)
	{
		this.momento = momento;
		this.motivo = motivo;
		this.partido = partido;
	}

	public String getMotivo() {
		return motivo;
	}

	public LocalDateTime getMomento() {
		return momento;
	}
	
	public String getDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return momento.format(formatter);
	}

	public String getTime(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return momento.format(formatter);
	}
	public Partido getPartido() {
		return partido;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
