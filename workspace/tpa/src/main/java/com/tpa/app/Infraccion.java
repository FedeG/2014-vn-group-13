package com.tpa.app;

import java.time.LocalDateTime;

public class Infraccion {
	String motivo;
	LocalDateTime momento;
	
	public Infraccion(String motivo, LocalDateTime momento)
	{
		this.momento = momento;
		this.motivo = motivo;
	}
}
