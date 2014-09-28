package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;

public class ConfirmacionExitosaWindow extends SimpleWindow<PantallaPrincipal> {

	public ConfirmacionExitosaWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("");
		this.setTaskDescription("\n   Confirmación Exitosa !!!   \n");
		super.createMainTemplate(mainPanel);
		
		new Button(mainPanel)
		.setCaption("OK")
		.onClick(new MessageSend(this, "close"));
	}

	@Override
	protected void addActions(Panel mainPanel) {}

	@Override
	protected void createFormPanel(Panel mainPanel) {}

}
