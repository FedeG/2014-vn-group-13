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
import com.tpa.app.Divisor;
import com.tpa.app.GeneradorDeEquipos;
import com.tpa.app.Inscripcion;
import com.tpa.app.Partido;
import com.tpa.app.domain.BuscadorPartidos;

public class GenerarEquiposWindow extends SimpleWindow<BuscadorPartidos> {

	public GenerarEquiposWindow(WindowOwner parent) {
		super(parent, new BuscadorPartidos());
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

	protected void crearSelectoresDeCriterios(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new HorizontalLayout());

		Panel searchOrdenamientoPanel = new Panel(searchFormPanel);
		searchOrdenamientoPanel.setLayout(new VerticalLayout());

		new Label(searchOrdenamientoPanel).setText("Criterios de Ordenamiento");
		RadioSelector<String> radioSelectorOrdenamiento = new RadioSelector<>(searchOrdenamientoPanel);
		radioSelectorOrdenamiento.setWidth(20);
		radioSelectorOrdenamiento.bindValueToProperty("ordenamientoSeleccionado");
		radioSelectorOrdenamiento.bindItemsToProperty("ordenamientos");
		this.getModelObject().setOrdenamientoSeleccionado("Por Promedio");

		Panel searchSeleccionPanel = new Panel(searchFormPanel);
		searchSeleccionPanel.setLayout(new VerticalLayout());

		new Label(searchSeleccionPanel).setText("Criterios de Seleccion");
		RadioSelector<String> radioSelectorSeleccion = new RadioSelector<>(searchSeleccionPanel);
		radioSelectorSeleccion.setWidth(20);
		radioSelectorSeleccion.bindValueToProperty("seleccionSeleccionada");
		radioSelectorSeleccion.bindItemsToProperty("selecciones");
		this.getModelObject().setSeleccionSeleccionada("ParesImpares");
	}

	protected void crearGrillaPartidos(Panel mainPanel) {
		Table<Partido> table = new Table<Partido>(mainPanel, Partido.class);
		table.setHeigth(100);
		table.setWidth(380);

		table.bindItemsToProperty("administrador.partidos");
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

	public void generar() {
		this.getModelObject().getPartidoSeleccionado().verificarConfirmacion();
		GeneradorDeEquipos generador = new GeneradorDeEquipos();
		List<Criterio> critOrden = new ArrayList<Criterio>();
		critOrden = crearListaSegunRadioButton();
		List<Inscripcion> inscripciones = generador.ordenarJugadores(critOrden, this.getModelObject().getPartidoSeleccionado());
		ByIndex divisor = crearDivisorSegunRadioButton();
		generador.dividirEquipos(divisor, this.getModelObject().getPartidoSeleccionado(), inscripciones);
		new EquiposGeneradosWindow(this, this.getModelObject().getPartidoSeleccionado()).open();
	}

	private ByIndex crearDivisorSegunRadioButton() {
		for (Divisor divisor: this.getModelObject().getAdministrador().getDivisores()){
			if (this.getModelObject().getSeleccionSeleccionada() == ((ByIndex) divisor).getNombre())
				return (ByIndex) divisor;
		}
		return null;
	}

	private List<Criterio> crearListaSegunRadioButton() {
		// TODO 
			// Es una lista para poder usar varios criterios :D
				// pero hay que cambiar el como se usa porque no se que componente de arena tengo que usar :P
		List<Criterio> criterios = new ArrayList<Criterio>();
		for (Criterio criterio: this.getModelObject().getAdministrador().getCriterios()){
			if (this.getModelObject().getOrdenamientoSeleccionado() == criterio.getNombre())
				criterios.add(criterio);
		}
		return criterios;
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {}

	@Override
	protected void addActions(Panel mainPanel) {}

}
