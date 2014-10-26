package com.tpa.app.view;


import java.awt.Color;
import java.util.Arrays;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;

import com.tpa.app.model.Jugador;
import com.tpa.app.viewModel.BuscadorJugadores;

@SuppressWarnings("serial")
public class BuscarJugadoresWindow extends  SimpleWindow<BuscadorJugadores> {

	public BuscarJugadoresWindow(WindowOwner parent) {
		super(parent, new BuscadorJugadores());
		this.getModelObject().borrarBusqueda();
		this.getModelObject().search();
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Buscar Jugadores");
		mainPanel.setLayout(new VerticalLayout());
		super.createMainTemplate(mainPanel);
		
		/* Controles de formulario de busqueda */
		
		crearPanelDeBusqueda(mainPanel);

		/* Create Grid */
		createResultsGrid(mainPanel);
		
		/* Grid Actions */
		
		Panel botonera = new Panel(mainPanel);
		botonera.setLayout(new HorizontalLayout());
		
		Button verJugador = new Button(botonera);
		verJugador.setCaption("Ver Jugador Seleccionado");
		verJugador.setWidth(200);
		verJugador.onClick(new MessageSend(this, "verJugadorSeleccionado"));
		
		Button borrar = new Button(botonera);
		borrar.setCaption("Borrar campos busqueda");
		borrar.setWidth(200);
		borrar.onClick(new MessageSend(getModelObject(), "borrarBusqueda"));
		
		Button volver = new Button(botonera);
		volver.setCaption("Volver");
		volver.setWidth(200);
		volver.onClick(new MessageSend(this, "close"));
		
		//Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("jugadorSeleccionado");
		verJugador.bindEnabled(elementSelected);
	}
	protected void createResultsGrid(Panel mainPanel) {
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(600);
		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("jugadorSeleccionado");
		
		/* Grid Description */
		describeResultsGrid(table, "Nombre", "persona.nombre", 225);
		describeResultsGrid(table, "Apodo", "persona.apodo", 225);
		describeResultsGrid(table, "Handicap", "handicap", 75);
		describeResultsGrid(table, "Promedio", 75).bindContentsToTransformer(new PromedioTransformer());
	}
	protected Column<Jugador> describeResultsGrid(Table<Jugador> table, String nombre, int fixedSize) {
		return new Column<Jugador>(table)
			.setTitle(nombre)
			.setFixedSize(fixedSize)
			.bindBackground("handicap", new com.uqbar.commons.collections.Transformer<Double, Color>() {
					@Override
					public Color transform(Double handicap) {
						return  (handicap > 8 ? Color.BLUE : Color.WHITE);
					}
				} 		
			);
	}
	protected void describeResultsGrid(Table<Jugador> table, String nombre, String property, int fixedSize) {
		describeResultsGrid(table, nombre, fixedSize)
			.bindContentsToProperty(property);
	}
	protected void crearPanelDeBusqueda(Panel mainPanel)
	{
		Panel opcionesDeBusqueda = new Panel(mainPanel); 
		opcionesDeBusqueda.setLayout(new ColumnLayout(4));
		
		new Label(opcionesDeBusqueda).setText("Nombre comienza con:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("comienzaCon");
		new Label(opcionesDeBusqueda).setText("Apodo contiene:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("apodoContiene");
		
		new Label(opcionesDeBusqueda).setText("Handicap desde:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("handicapDesde");
		new Label(opcionesDeBusqueda).setText("Handicap hasta:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("handicapHasta");

		new Label(opcionesDeBusqueda).setText("Promedio desde:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("promedioDesde");;
		new Label(opcionesDeBusqueda).setText("Promedio hasta:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("promedioHasta");;
		
		new Label(opcionesDeBusqueda).setText("Tuvo infraccion:");
		new RadioSelector<String>(opcionesDeBusqueda).setContents(Arrays.asList("Si","No","Indistinto"), "infraccion")
		.bindValueToProperty("opcionInfraccion");
		new Label(opcionesDeBusqueda).setText("Fecha nacimiento anterior a:");
		new TextBox(opcionesDeBusqueda).setWidth(80).bindValueToProperty("fechaTope");
		
		new Button(mainPanel)
		.setCaption("Buscar")
		.onClick(new MessageSend(this.getModelObject(), "search"))
		.disableOnError();	
	}

	@Override
	protected void addActions(Panel actionsPanel) {
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {		
	}
		
	public void verJugadorSeleccionado() {
		this.openDialog(new VerJugadorSeleccionadoWindow(this, this.getModelObject().getJugadorSeleccionado()));
	}
	
	private void openDialog(VerJugadorSeleccionadoWindow nuevaVentana) {
		nuevaVentana.open();
	}
}
