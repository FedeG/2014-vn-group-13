package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.DateAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;

@Observable
public class VerJugadorSeleccionadoWindow extends TransactionalDialog<Jugador> {

	public VerJugadorSeleccionadoWindow(WindowOwner parent, Jugador jugador) {
		super(parent, jugador);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Ver Jugador Seleccionado");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(4));

		new Label(form).setText("Nombre");
		new Label(form).bindValueToProperty("persona.nombre");

		new Label(form).setText("Apodo");
		new Label(form).bindValueToProperty("persona.apodo");

		new Label(form).setText("Fecha de Nacim:");
		new Label(form).bindValueToProperty("persona.fechaNac"));
		
		//new Label(form).setText("Handicap");
		//new Label(form).bindValueToProperty("handicap");

		//new Label(form).setText("Promedio");
		//new Label(form).bindValueToProperty("promedio");

		//crearGrillaConAmigos(mainPanel);
		//crearGrillaConInfracciones(mainPanel);
	}

	protected void crearGrillaConInfracciones(Panel mainPanel) {
		Table<Jugador> table = new Table<Jugador>(mainPanel);
		table.setHeigth(100);
		table.setWidth(250);
		table.bindItemsToProperty("infracciones");
		//table.bindValueToProperty("jugadorSeleccionado");
		
		//Grid Description 
		
		new Column<Jugador>(table) //
		.setTitle("Motivo")
		.setFixedSize(225)
		.bindContentsToProperty("motivo");

		new Column<Jugador>(table) //
		.setTitle("Fecha")
		.setFixedSize(225)
		.bindContentsToProperty("momento");
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Volver").onClick(
				new MessageSend(this, "volver"));
	}

	public void volver() {
		this.close();
	}
}
