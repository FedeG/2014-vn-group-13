package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;


public class PantallaPrincipalWindow extends SimpleWindow<PantallaPrincipal> {

	public PantallaPrincipalWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Organizador de Partidos de Futbol - DDS 2014");
		this.setTaskDescription("Elija una operacion a realizar");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel){
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("- Operaciones -").setWidth(430);
		new Button(mainPanel)
			.setCaption("Generar Equipos")
			.onClick(new MessageSend(this, "generarEquipos"));
	
		new Button(mainPanel)
			.setCaption("Buscar Jugadores")
			.onClick(new MessageSend(this, "buscarJugadores"));
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		
		
	}
	
	public void generarEquipos() {
		new GenerarEquiposWindow(this).open();
	}

	public void buscarJugadores() {
		new BuscarJugadoresWindow(this).open();
	}
}
