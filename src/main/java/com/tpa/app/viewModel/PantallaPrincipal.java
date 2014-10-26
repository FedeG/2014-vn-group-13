package com.tpa.app.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Administrador;

@SuppressWarnings("serial")
@Observable
public class PantallaPrincipal implements Serializable {

	private Administrador administradorSeleccionado;

	public List<Administrador> getAdministradores() {
		return EntityManagerHelper.createQuery("from Administrador")
				.getResultList();
	}

	public Administrador getAdministradorSeleccionado() {
		return administradorSeleccionado;
	}

	public void setAdministradorSeleccionado(
			Administrador administradorSeleccionado) {
		this.administradorSeleccionado = administradorSeleccionado;
	}
}
