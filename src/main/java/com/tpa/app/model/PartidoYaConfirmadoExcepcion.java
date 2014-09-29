package com.tpa.app.model;

import org.uqbar.commons.model.UserException;

public class PartidoYaConfirmadoExcepcion extends UserException {

	private static final long serialVersionUID = 1L;
	
	public PartidoYaConfirmadoExcepcion() {
		super("El partido ya está confirmado.");
	}

}
