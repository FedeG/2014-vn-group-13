package com.tpa.app;

public class YaEstaInscriptoAlPartidoExcepcion extends RuntimeException {

	public YaEstaInscriptoAlPartidoExcepcion() {
		super("Ya se encuentra inscripto al partido.");
	}

}
