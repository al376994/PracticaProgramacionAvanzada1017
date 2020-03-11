package Menu;

import Auxiliares.*;
import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

// El menu cuando se ejecuta para la terminal.

public class MenuTerminal {

	private BaseDeDatos baseDeDatos;
	private InOut entradaSalida = new IOTerminal();
	private Creador creador = new Creador(entradaSalida);

	public void run(BaseDeDatos baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
		entradaSalida.print("Menu de Terminal de la aplicación de Facturación");
		while (true) {
			chooseOptionSet(MenuOpciones.OPCIONES_PRINCIPALES);
		}
	}

	private String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}	//Añadido de MenuOpciones

	private void chooseOptionSet(int set) {
		int option;
		String inputText = "\nEscribe el número de la opción que quieres elegir:";
		switch (set) {
			case 1:																	//Menu principal
				entradaSalida.print("\n" + getOpciones(MenuOpciones.listaOpcionesPrincipales));
				option = entradaSalida.getOption(inputText);
				chooseOptionPrincipales(option);
				break;
			case 2:																	//Seleccinar tipo de cliente
				entradaSalida.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevoCliente));
				option = entradaSalida.getOption(inputText);
				printIsSatisfactory(chooseOptionNuevoCliente(option));
				break;
			case 3:																	//Crear llamada
				entradaSalida.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevaLlamada));
				option = entradaSalida.getOption(inputText);
				printIsSatisfactory(chooseOptionNuevaLlamada(option));
				break;
			case 4:																	//Crear factura
				entradaSalida.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevaFactura));
				option = entradaSalida.getOption(inputText);
				printIsSatisfactory(chooseOptionNuevaFactura(option));
				break;
			case -1:																//Opciones de salida
				entradaSalida.print("\n" + getOpciones(MenuOpciones.listaOpcionesSalida));
				option = entradaSalida.getOption(inputText);
				chooseOptionSalida(option);
				break;
			default:
				entradaSalida.print("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				break;
		}
		//return chooseOptionSet(MenuOpciones.OPCIONES_PRINCIPALES);
	}

	// Todos los choose a lo qeu llama chooseOptionSet a partir de aqui
	// En los switch solo van sentencias simples, si es necesario algo mas complejo crea una funcion debajo del choose

	//	********************************************************************************************************  \\
	//	******************************************OPCIONES_PRINCIPALES******************************************  \\
	//	********************************************************************************************************  \\

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:											// CREAR UN CLIENTE NUEVO
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVO_CLIENTE);
				break;
			case 2:											// BORRAR UN CLIENTE EXISTENTE
				printIsSatisfactory(askBorrarCliente());
				break;
			case 3:											// CAMBIAR LA TARIFA DE UN CLIENTE
				printIsSatisfactory(cambiarTarifa());
				break;
			case 4:											// MOSTRAR LOS DATOS DE UN CLIENTE
				printCliente();
				break;
			case 5:											// MOSTRAR LOS DATOS DE TODOS LOS CLIENTES
				listarClientes();
				break;
			case 6:											// CREAR UNA LLAMADA PARA UN CLIENTE
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVA_LLAMADA);
				break;
			case 7:											// MOSTRAR LLAMADAS DE UN CLIENTE
				listarLlamadas();
				break;
			case 8:											//CREAR UNA FACTURA PARA UN CLIENTE
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVA_FACTURA);
				break;
			case 9:											//RECUPERAR FACTURA A PARTIR DE CODIGO
				printFactura();
				break;
			case 10:										//RECUPERAR FACTURAS DE UN CLIENTE
				listarFacturas();
				break;
			case 11:										//LISTAR CLIENTES ENTRE FECHAS
				entradaSalida.listar(listarClientesEntreFechas());
				break;
			case 12:										//LISTAR LLAMADAS ENTRE FECHAS
				entradaSalida.listar(listarLlamadassEntreFechas());
				break;
			case 13:										//LISTAR FACTURAS ENTRE FECHAS
				entradaSalida.listar(listarFacturasEntreFechas());
				break;
			case 14:										//GUARDAR BASE DE DATOS
				printIsSatisfactory(baseDeDatos.saveData());
				break;
			case 15:										//CARGAR BASE DE DATOS
				printIsSatisfactory(baseDeDatos.loadData());
				break;
			case 16:										// CERRAR EL PROGRAMA
				chooseOptionSet(MenuOpciones.OPCIONES_SALIDA);
				break;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_PRINCIPALES);
				break;
		}
	}

	private boolean askBorrarCliente() {
		String nif;
		nif = entradaSalida.askNIF();
		return baseDeDatos.borrarCliente(nif);
	}

	private boolean cambiarTarifa() {
		String nif = entradaSalida.askNIF();
		Tarifa tarifa = creador.nuevaTarifa();
		return baseDeDatos.cambiarTarifa(nif, tarifa);
	}

	private void printCliente() {
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		entradaSalida.print("\n" + cliente + "\n");
		entradaSalida.waitIntro();
	}

	private void listarClientes() {
		entradaSalida.listar(baseDeDatos.getClientes().values());
	}

	private void listarLlamadas() {
		entradaSalida.print("\n");
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		entradaSalida.listar(cliente.getLlamadas());
	}

	private void printFactura() {
		Factura factura = entradaSalida.askForFactura(baseDeDatos);
		entradaSalida.print(factura);
		entradaSalida.waitIntro();
	}

	private void listarFacturas() {
		entradaSalida.print("\n");
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		entradaSalida.listar(cliente.getFacturas().values());
	}

	//	********************************************************************************************************  \\
	//	*****************************************OPCIONES_NUEVO_CLIENTE*****************************************  \\
	//	********************************************************************************************************  \\

	private boolean chooseOptionNuevoCliente(int option){
		Cliente cliente;
		switch (option) {
			case 1:
				cliente = creador.nuevoCliente(true, false);
				entradaSalida.print("\n" + cliente + "\n");
				return baseDeDatos.addClient(cliente);
			case 2:
				cliente = creador.nuevoCliente(true, true);
				entradaSalida.print("\n" + cliente + "\n");
				return baseDeDatos.addClient(cliente);
			case 3:
				cliente = creador.nuevoCliente(false, false);
				entradaSalida.print("\n" + cliente + "\n");
				return baseDeDatos.addClient(cliente);
			case 4:
				cliente = creador.nuevoCliente(false, true);
				entradaSalida.print("\n" + cliente + "\n");
				return baseDeDatos.addClient(cliente);
			case 5:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVO_CLIENTE);
				break;
		}
		return false;
	}

	//	********************************************************************************************************  \\
	//	*****************************************OPCIONES_NUEVA_LLAMADA*****************************************  \\
	//	********************************************************************************************************  \\

	private boolean chooseOptionNuevaLlamada(int option){
		switch (option) {
			case 1:
				return nuevaLlamada(false);
			case 2:
				return nuevaLlamada(true);
			case 3:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVA_LLAMADA);
				break;
		}
		return false;
	}

	private boolean nuevaLlamada(boolean random) {
		String nif = entradaSalida.askNIF();
		Llamada llamada = creador.nuevaLlamada(random);
		entradaSalida.print("\n" + llamada + "\n");
		return baseDeDatos.addLlamada(nif, llamada);
	}

	//	********************************************************************************************************  \\
	//	*****************************************OPCIONES_NUEVA_FACTURA*****************************************  \\
	//	********************************************************************************************************  \\

	private boolean chooseOptionNuevaFactura(int option) {
		switch (option) {
			case 1:
				return nuevaFactura(false);
			case 2:
				return nuevaFactura(true);
			case 3:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVA_FACTURA);
				break;
		}
		return false;
	}

	private boolean nuevaFactura(boolean random) {
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		Factura factura = creador.nuevaFactura(cliente, random);
		entradaSalida.print("\n" + factura + "\n");
		return baseDeDatos.addFactura(factura);
	}

	//	*********************************************************************************************************  \\
	//	*********************************************OPCIONES_SALIDA*********************************************  \\
	//	*********************************************************************************************************  \\

	private void chooseOptionSalida(int option) {
		switch (option) {
			case 1:
				baseDeDatos.exitWithoutSave();
				break;
			case 2:
				baseDeDatos.saveData();
				System.exit(0);
				break;
			case 3:
				break;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_SALIDA);
				break;
		}
	}

	// Todas las funciones a las que llamen los choose van a partir de aquí a partir de aqui

	private Collection<Cliente> listarClientesEntreFechas() {
		ArrayList<Cliente> clientes = new ArrayList<>(baseDeDatos.getClientes().values());
		LocalDate desde = entradaSalida.askDate("de la fecha inicial");
		LocalDate hasta = entradaSalida.askDate("de la fecha final");
		return baseDeDatos.elementosEntreFechas(clientes, desde, hasta);
	}

	private Collection<Llamada> listarLlamadassEntreFechas() {
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		ArrayList<Llamada> llamadas = new ArrayList<>(cliente.getLlamadas());
		LocalDate desde = entradaSalida.askDate("de la fecha inicial");
		LocalDate hasta = entradaSalida.askDate("de la fecha final");
		return baseDeDatos.elementosEntreFechas(llamadas, desde, hasta);
	}

	private Collection<Factura> listarFacturasEntreFechas() {
		Cliente cliente = entradaSalida.askForCliente(baseDeDatos);
		ArrayList<Factura> facturas = new ArrayList<>(cliente.getFacturas().values());
		LocalDate desde = entradaSalida.askDate("de la fecha inicial");
		LocalDate hasta = entradaSalida.askDate("de la fecha final");
		return baseDeDatos.elementosEntreFechas(facturas, desde, hasta);
	}

	private void printIsSatisfactory(boolean s) {
		if (s) entradaSalida.print("Operacion completada con exito");
		else entradaSalida.print("La operacion no se ha podido realizar");
		entradaSalida.waitIntro();
	}

	private void wrongOptionWriten(int set) {
		entradaSalida.print("Write one of the options.");
		entradaSalida.waitIntro();
		chooseOptionSet(set);
	}
}