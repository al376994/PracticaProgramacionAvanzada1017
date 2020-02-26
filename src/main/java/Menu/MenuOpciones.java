package Menu;

import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;

public class MenuOpciones {

	BaseDeDatos baseDeDatos = new BaseDeDatos();

	final String inputText = "\nEscribe el número de la opción que quieres elegir:";

	final String[] listaOpcionesPrincipales = {
			"1. \tDar de alta a un nuevo cliente",
			"2. \tBorrar un cliente",
			"3. \tCambiar la tarifa de un cliente",
			"4. \tRecuperar los datos de un cliente a partir de su NIF",
			"5. \tRecuperar el listado de todos los clientes",
			"6. \tDar de alta una llamada",
			"7. \tListar todas las llamadas de un cliente",
			"8. \tEmitir una factura para un cliente, calculando el importe de la misma en función de las llamadas",
			"9. \tRecuperar los datos de una factura a partir de su código",
			"10.\tRecuperar todas las facturas de un cliente"
	};

	final String[] listaOpcionesNuevoCliente = {
			"1. \tDar de alta un Particular",
			"2. \tDar de alta un Particular aleatorio",
			"3. \tDar de alta una Empresa",
			"4. \tDar de alta una Empresa aleatoria"
	};

	final String[] listaOpcionesSalida = {
			"1. \tSalir sin guardar",
			"2. \tGuardar y salir",
			"3. \tCancelar"
	};

	final String[] listaOpcionesNuevaLlamada = {
			"1. \tDar de alta una Llamada",
			"2. \tDar de alta una Llamada aleatoria",
			"3. \tCancelar"
	};

	private final int OPCIONES_PRINCIPALES = 1;
	private final int OPCIONES_NUEVO_CLIENTE = 2;
	private final int OPCIONES_NUEVA_LLAMADA = 3;
	private final int OPCIONES_SALIDA = -1;

	public String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}

	public void chooseOptionSet(int set) {
		int option;
		switch (set) {
			case 1:
				IO.out.toTerminal("\n" + getOpciones(listaOpcionesPrincipales));
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionPrincipales(option);
				break;
			case 2:
				IO.out.toTerminal("\n" + getOpciones(listaOpcionesNuevoCliente));
				option = IO.in.fromTerminalAskInt(inputText);
				boolean satisfactory = chooseOptionNuevoCliente(option);
				printIsSatisfactory(satisfactory);
				break;
			case 3:
				IO.out.toTerminal("\n" + getOpciones(listaOpcionesNuevaLlamada));
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionNuevaLlamada(option);
				printIsSatisfactory(true);
				break;
			case -1:
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionSalida(option);
			default:
				IO.out.toTerminal("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionPrincipales(option);
				break;
		}
		chooseOptionSet(OPCIONES_PRINCIPALES);
	}

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:
				chooseOptionSet(OPCIONES_NUEVO_CLIENTE);
				break;
			case 2:
				borrarCliente();
				break;
			case 3:
				baseDeDatos.cambiarTarifa();
				break;
			case 4:
				baseDeDatos.printCliente();
				break;
			case 5:
				baseDeDatos.listarClientes();
				IO.waitIntro();
				break;
			case 6:
				chooseOptionSet(OPCIONES_NUEVA_LLAMADA);
				break;
			case 7:
				baseDeDatos.listarLlamadas();
				break;
			case 8:
				IO.out.toTerminal(option);
				break;
			case 9:
				IO.out.toTerminal(option);
				break;
			case 10:
				IO.out.toTerminal(option);
				break;
			case 11:
				chooseOptionSet(OPCIONES_SALIDA);
				baseDeDatos.exitWithoutSave();
			default:
				IO.out.toTerminal("Write one of the options.");
				break;
		}
	}

	private boolean chooseOptionNuevoCliente(int option){
		switch (option) {
			case 1:
				IO.out.toTerminal("Estoy aqui, option: " + option);
				return baseDeDatos.nuevoParticular(false);
			case 2:
				return baseDeDatos.nuevoParticular(true);
			case 3:
				return baseDeDatos.nuevaEmpresa(false);
			case 4:
				return baseDeDatos.nuevaEmpresa(true);
			default:
				IO.out.toTerminal("Write one of the options.");
				break;
		}
		return false;
	}

	private boolean chooseOptionNuevaLlamada(int option){
		switch (option) {
			case 1:
				return baseDeDatos.darDeAltaLlamada(false);
			case 2:
				return baseDeDatos.darDeAltaLlamada(true);
			case 3:
				chooseOptionSet(OPCIONES_PRINCIPALES);
				break;
			default:
				IO.out.toTerminal("Write one of the options.");
				chooseOptionSet(OPCIONES_NUEVA_LLAMADA);
				break;
		}
		return false;
	}

	private void chooseOptionSalida(int option) {
		switch (option) {
			case 1:
				baseDeDatos.exitWithoutSave();
				break;
			case 2:
				IO.out.toTerminal("Opcion no implementada.");
				break;
			case 3:
				break;
			default:
				IO.out.toTerminal("Write one of the options.");
				chooseOptionSet(OPCIONES_SALIDA);
				break;
		}
	}

	private void printIsSatisfactory(boolean s) {
		if (s) IO.out.toTerminal("Operacion completada con exito");
		else IO.out.toTerminal("La operacion no se ha podido realizar");
		IO.waitIntro();
	}

	private void borrarCliente() {
		boolean satisfactory = baseDeDatos.borrarCliente();
		printIsSatisfactory(satisfactory);
	}

}
