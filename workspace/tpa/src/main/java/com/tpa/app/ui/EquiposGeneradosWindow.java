package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;

public class EquiposGeneradosWindow extends  SimpleWindow<PantallaPrincipal> {

	public EquiposGeneradosWindow(WindowOwner parent) {
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
		.setCaption("Confirmar")
		.onClick(new MessageSend(this, "confirmar"));
		
	}
	
	public void confirmar() {
		this.openDialog(new ConfirmacionExitosaWindow(this));
	}
	

	private void openDialog(ConfirmacionExitosaWindow confirmacionExitosaWindow) {
		confirmacionExitosaWindow.open();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);		
	}
}
