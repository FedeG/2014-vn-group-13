package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.Jugador;
import com.tpa.app.domain.PantallaPrincipal;

public class VerJugadorSeleccionadoWindow extends  SimpleWindow<PantallaPrincipal> {

	public VerJugadorSeleccionadoWindow(WindowOwner parent, Jugador jugador) {
		super(parent, new PantallaPrincipal());

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Ver Equipos Generados");
		super.createMainTemplate(mainPanel);

	}


	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Volver")
		.onClick(new MessageSend(this, "volver"));
	
		
	}

	public void volver() {
		this.close();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);		
	}
}
