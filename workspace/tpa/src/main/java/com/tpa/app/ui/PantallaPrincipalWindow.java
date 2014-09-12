package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
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
		this.setTitle("Operaciones");
		this.setTaskDescription("Elija una operación a realizar");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {}
	
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
		new GenerarEquiposWindow(this).open();
	}

	public void buscarJugadores() {
		new BuscarJugadoresWindow(this).open();
	}
}
