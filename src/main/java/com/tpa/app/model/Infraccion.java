package com.tpa.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.uqbar.commons.utils.Observable;


@Observable
@Entity
@Table(name = "infraccion")
public class Infraccion extends PersistentEntity implements Serializable {
	public Infraccion()
	{}
	
	private String motivo;
	@Column(name = "momento")
	private Timestamp momento;
	@OneToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	@OneToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;
	
	public Infraccion(String motivo, Timestamp momento, Partido partido)
	{
		this.momento = momento;
		this.motivo = motivo;
		this.partido = partido;
	}

	public String getMotivo() {
		return motivo;
	}

	public Timestamp getMomento() {
		return momento;
	}
	
	public String getDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return momento.toLocalDateTime().format(formatter);
	}

	public String getTime(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return momento.toLocalDateTime().format(formatter);
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
