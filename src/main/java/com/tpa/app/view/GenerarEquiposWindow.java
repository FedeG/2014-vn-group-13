package com.tpa.app.view;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.RadioSelector;

import com.tpa.app.model.Criterio;
import com.tpa.app.model.Divisor;
import com.tpa.app.model.Partido;
import com.tpa.app.viewModel.GeneradorEquipos;

public class GenerarEquiposWindow extends SimpleWindow<GeneradorEquipos> {

	public GenerarEquiposWindow(WindowOwner parent) {
		super(parent, new GeneradorEquipos());
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {

		this.setTitle("Generar Equipos");
		mainPanel.setLayout(new VerticalLayout());
		super.createMainTemplate(mainPanel);

		crearSelectoresDeCriterios(mainPanel);
		crearGrillaPartidos(mainPanel);

		/* Grid Actions */

		Panel botonera = new Panel(mainPanel);
		botonera.setLayout(new HorizontalLayout());

		Button generar = new Button(botonera);
		generar.setCaption("Generar");
		generar.onClick(new MessageSend(this, "generar")).setWidth(200);		

		Button volver = new Button(botonera);
		volver.setCaption("Volver");
		volver.setWidth(200);
		volver.onClick(new MessageSend(this, "close"));

		NotNullObservable elementSelected = new NotNullObservable("partidoSeleccionado");
		generar.bindEnabled(elementSelected);
	}
	public void generar() {
		this.getModelObject().generar();
		new EquiposGeneradosWindow(this, this.getModelObject().getPartidoSeleccionado()).open();
	}
	protected void crearSelectoresDeCriterios(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new HorizontalLayout());

		Panel searchOrdenamientoPanel = new Panel(searchFormPanel);
		searchOrdenamientoPanel.setLayout(new VerticalLayout());

		new Label(searchOrdenamientoPanel).setText("Criterios de Ordenamiento");
		RadioSelector<Criterio> radioSelectorOrdenamiento = new RadioSelector<>(searchOrdenamientoPanel);
		radioSelectorOrdenamiento.setWidth(20);
		radioSelectorOrdenamiento.bindValueToProperty("ordenamientoSeleccionado");
		radioSelectorOrdenamiento.bindItemsToProperty("ordenamientos");
		//this.getModelObject().setOrdenamientoSeleccionado("Por Promedio");

		Panel searchSeleccionPanel = new Panel(searchFormPanel);
		searchSeleccionPanel.setLayout(new VerticalLayout());

		new Label(searchSeleccionPanel).setText("Criterios de Seleccion");
		RadioSelector<Divisor> radioDivisorSeleccion = new RadioSelector<>(searchSeleccionPanel);
		radioDivisorSeleccion.setWidth(20);
		radioDivisorSeleccion.bindValueToProperty("divisionSeleccionada");
		radioDivisorSeleccion.bindItemsToProperty("divisores");
		//this.getModelObject().setDivisionSeleccionada("ParesImpares");
	}

	protected void crearGrillaPartidos(Panel mainPanel) {
		Table<Partido> table = new Table<Partido>(mainPanel, Partido.class);
		table.setHeigth(100);
		table.setWidth(380);

		table.bindItemsToProperty("administrador.partidosPendientes");
		table.bindValueToProperty("partidoSeleccionado");

		/* Grid Description */

		new Column<Partido>(table)
			.setTitle("Fecha y Hora")
			.setFixedSize(200)
			.bindContentsToTransformer(new FechaTransfomer());

		new Column<Partido>(table)
			.setTitle("Lugar")
			.setFixedSize(180)
			.bindContentsToProperty("lugar");
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {}

	@Override
	protected void addActions(Panel mainPanel) {}

}
