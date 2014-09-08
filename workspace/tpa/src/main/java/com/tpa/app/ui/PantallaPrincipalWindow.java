package com.tpa.app.ui;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.Transformer;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;




public class PantallaPrincipalWindow extends SimpleWindow<PantallaPrincipal> {

	public PantallaPrincipalWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Operaciones");
		super.createMainTemplate(mainPanel);

	}


	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);

	}

	
	@Override
	protected void addActions(Panel actionsPanel) {
		
		new Button(actionsPanel)
			.setCaption("Generar Equipos")
			.onClick(new MessageSend(this, "generarEquipos"));
	
		new Button(actionsPanel) //
			.setCaption("Buscar Jugadores")
			.onClick(new MessageSend(this, "buscarJugadores"));

	}
	
	public void generarEquipos() {
		this.openDialog(new GenerarEquiposWindow(this));
	}
	

	private void openDialog(GenerarEquiposWindow generarEquiposWindow) {
		generarEquiposWindow.open();
	}


	public void buscarJugadores() {
		this.openDialog(new BuscarJugadoresWindow(this));
	}
	
	private void openDialog(BuscarJugadoresWindow buscarJugadoresWindow) {
		buscarJugadoresWindow.open();
	}



}
