package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;

public class GenerarEquiposWindow extends  SimpleWindow<PantallaPrincipal> {

	public GenerarEquiposWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Generar Equipos");
		super.createMainTemplate(mainPanel);

	}


	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Generar")
		.onClick(new MessageSend(this, "generar"));
		
	}
	
	public void generar() {
		this.openDialog(new EquiposGeneradosWindow(this));
	}
	

	private void openDialog(EquiposGeneradosWindow equiposGeneradosWindow) {
		equiposGeneradosWindow.open();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);		
	}
}
