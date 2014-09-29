package com.tpa.app.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.uqbar.commons.utils.Observable;

@Observable
public class Infraccion {
	
	private String motivo;
	private LocalDateTime momento;
	
	public Infraccion(String motivo, LocalDateTime momento)
	{
		this.momento = momento;
		this.motivo = motivo;
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
}
