package com.tpa.app.ui;

import java.util.ArrayList;
import java.util.List;

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
import com.tpa.app.ByIndex;
import com.tpa.app.Criterio;
import com.tpa.app.GeneradorDeEquipos;
import com.tpa.app.Partido;
import com.tpa.app.PartidoYaConfirmadoExcepcion;
import com.tpa.app.PorHandicap;
import com.tpa.app.PorPromedio;
import com.tpa.app.domain.BuscadorPartidos;

public class GenerarEquiposWindow extends SimpleWindow<BuscadorPartidos> {

	public GenerarEquiposWindow(WindowOwner parent) {
		super(parent, new BuscadorPartidos());
		this.getModelObject().search();
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

		// Deshabilitar los botones si no hay ningún elemento seleccionado en
		// la grilla.
		NotNullObservable elementSelected = new NotNullObservable(
				"partidoSeleccionado");
		generar.bindEnabled(elementSelected);
	}

	protected void crearSelectoresDeCriterios(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new HorizontalLayout());

		Panel searchOrdenamientoPanel = new Panel(searchFormPanel);
		searchOrdenamientoPanel.setLayout(new VerticalLayout());

		new Label(searchOrdenamientoPanel).setText("Criterios de Ordenamiento");
		RadioSelector<String> radioSelectorOrdenamiento = new RadioSelector<>(
				searchOrdenamientoPanel);
		radioSelectorOrdenamiento.setWidth(20);
		radioSelectorOrdenamiento
				.bindValueToProperty("ordenamientoSeleccionado");
		radioSelectorOrdenamiento.bindItemsToProperty("ordenamientos");
		this.getModelObject().setOrdenamientoSeleccionado("Por Promedio");

		Panel searchSeleccionPanel = new Panel(searchFormPanel);
		searchSeleccionPanel.setLayout(new VerticalLayout());

		new Label(searchSeleccionPanel).setText("Criterios de Seleccion");
		RadioSelector<String> radioSelectorSeleccion = new RadioSelector<>(
				searchSeleccionPanel);
		radioSelectorSeleccion.setWidth(20);
		radioSelectorSeleccion.bindValueToProperty("seleccionSeleccionada");
		radioSelectorSeleccion.bindItemsToProperty("selecciones");
		this.getModelObject().setSeleccionSeleccionada("Par/Impar");
	}

	protected void crearGrillaPartidos(Panel mainPanel) {
		Table<Partido> table = new Table<Partido>(mainPanel, Partido.class);
		table.setHeigth(100);
		table.setWidth(380);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("partidoSeleccionado");

		/* Grid Description */

		new Column<Partido>(table).setTitle("Fecha y Hora").setFixedSize(200)
				.bindContentsToTransformer(new FechaTransfomer());

		new Column<Partido>(table).setTitle("Lugar").setFixedSize(180)
				.bindContentsToProperty("lugar");
	}

	public void generar() {
		if(getModelObject().getPartidoSeleccionado().getConfirmado())
			throw new PartidoYaConfirmadoExcepcion();
		
		GeneradorDeEquipos generador = new GeneradorDeEquipos();
		ByIndex divisor = crearDivisorSegunRadioButton();
		List<Criterio> critOrden = new ArrayList<Criterio>();
		critOrden = crearListaSegunRadioButton();
		generador.generarEquipos(getModelObject().getPartidoSeleccionado(), critOrden, divisor);

		new EquiposGeneradosWindow(this, this.getModelObject()
				.getPartidoSeleccionado()).open();
	}

	private ByIndex crearDivisorSegunRadioButton() {

		ArrayList<Integer> indicesEquipoA = new ArrayList<Integer>();
		ArrayList<Integer> indicesEquipoB = new ArrayList<Integer>();

		if (this.getModelObject().getSeleccionSeleccionada() == "Par/Impar") {
			indicesEquipoA.add(0);
			indicesEquipoB.add(1);
			indicesEquipoA.add(2);
			indicesEquipoB.add(3);
			indicesEquipoA.add(4);
			indicesEquipoB.add(5);
			indicesEquipoA.add(6);
			indicesEquipoB.add(7);
			indicesEquipoA.add(8);
			indicesEquipoB.add(9);
		}

		if (this.getModelObject().getSeleccionSeleccionada() == "1,4,5,8,9") {
			indicesEquipoA.add(1);
			indicesEquipoB.add(0);
			indicesEquipoA.add(4);
			indicesEquipoB.add(2);
			indicesEquipoA.add(5);
			indicesEquipoB.add(3);
			indicesEquipoA.add(8);
			indicesEquipoB.add(6);
			indicesEquipoA.add(9);
			indicesEquipoB.add(7);
		}
		return new ByIndex(indicesEquipoA, indicesEquipoB);
	}

	private List<Criterio> crearListaSegunRadioButton() {
		List<Criterio> critOrden = new ArrayList<Criterio>();
		if (this.getModelObject().getOrdenamientoSeleccionado() == "Por Handicap") {
			critOrden.add(new PorHandicap());
		}
		if (this.getModelObject().getOrdenamientoSeleccionado() == "Por Promedio") {
			critOrden.add(new PorPromedio());
		}
		if (this.getModelObject().getOrdenamientoSeleccionado() == "Mixto") {
			critOrden.add(new PorHandicap());
			critOrden.add(new PorPromedio());
		}
		return critOrden;
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
	}

	@Override
	protected void addActions(Panel mainPanel) {
	}

}
