package com.tpa.app;

public class CupoMaximoSuperadoExcepcion extends RuntimeException {

	public CupoMaximoSuperadoExcepcion() {
		super("El partido ya esta completo. Te esperamos la prox!");
	}

}
