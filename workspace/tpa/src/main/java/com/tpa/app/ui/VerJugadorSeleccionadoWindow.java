package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;
import com.tpa.app.domain.JugadorSelecionado;

@Observable
public class VerJugadorSeleccionadoWindow extends TransactionalDialog<JugadorSelecionado> {
	
	public VerJugadorSeleccionadoWindow(WindowOwner parent, Jugador jugador) {
		super(parent, new JugadorSelecionado(jugador));
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Jugador Seleccionado");
		this.setTaskDescription("                     Datos del jugador                     ");
		super.createMainTemplate(mainPanel);
	}

	private void createHorizontalPanel(Panel mainPanel, String key, String value){
		Panel horizontal_panel1 = new Panel(mainPanel);
		horizontal_panel1.setLayout(new HorizontalLayout());
		new Label(horizontal_panel1).setText(key);
		new Label(horizontal_panel1).bindValueToProperty(value);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {		
		this.createHorizontalPanel(mainPanel, "Nombre", "jugador.persona.nombre");
		this.createHorizontalPanel(mainPanel, "Apodo", "jugador.persona.apodo");
		this.createHorizontalPanel(mainPanel, "Handicap", "jugador.handicap");
		this.createHorizontalPanel(mainPanel, "Promedio", "promedio");
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

}
