package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.Infraccion;
import com.tpa.app.Jugador;
import com.tpa.app.Persona;
import com.tpa.app.domain.JugadorSelecionado;

@Observable
public class VerJugadorSeleccionadoWindow extends TransactionalDialog<JugadorSelecionado> {
	
	public VerJugadorSeleccionadoWindow(WindowOwner parent, Jugador jugador) {
		super(parent, new JugadorSelecionado(jugador));
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Jugador Seleccionado");
		this.setTaskDescription("                                                              Datos del jugador                                                              ");
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
		this.createHorizontalPanel(mainPanel, "Nombre: ", "jugador.persona.nombre");
		this.createHorizontalPanel(mainPanel, "Apodo: ", "jugador.persona.apodo");
		this.createHorizontalPanel(mainPanel, "Handicap: ", "jugador.handicap");
		this.createHorizontalPanel(mainPanel, "Promedio: ", "promedio");
		Panel panel1 = new Panel(mainPanel);
		new Label(panel1).setText("Amigos: ");
		this.crearGrillaConAmigos(mainPanel);
		Panel panel2 = new Panel(mainPanel);
		new Label(panel2).setText("\nInfracciones: ");
		this.crearGrillaConInfracciones(mainPanel);
	}

	protected void crearGrillaConInfracciones(Panel mainPanel) {
		Table<Infraccion> table = new Table<Infraccion>(mainPanel, Infraccion.class);
		table.setHeigth(175);
		table.setWidth(250);
		table.bindItemsToProperty("infracciones");

		new Column<Infraccion>(table)
			.setTitle("Motivo")
			.setFixedSize(225)
			.bindContentsToProperty("motivo");

		new Column<Infraccion>(table)
			.setTitle("Fecha")
			.setFixedSize(150)
			.bindContentsToProperty("date");

		new Column<Infraccion>(table)
			.setTitle("Time")
			.setFixedSize(75)
			.bindContentsToProperty("time");

	}

	protected void crearGrillaConAmigos(Panel mainPanel) {
		Table<Persona> table = new Table<Persona>(mainPanel, Persona.class);
		table.setHeigth(175);
		table.setWidth(250);
		table.bindItemsToProperty("amigos");

		new Column<Persona>(table)
			.setTitle("Nombre")
			.setFixedSize(225)
			.bindContentsToProperty("nombre");

		new Column<Persona>(table)
			.setTitle("Apodo")
			.setFixedSize(225)
			.bindContentsToProperty("apodo");

	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Volver").onClick(
			new MessageSend(this, "volver")
		);
	}

	public void volver() {
		this.close();
	}

}
