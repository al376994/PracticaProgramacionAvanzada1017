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

	private final int opcionesPrincipales = 1;
	private final int opcionesNuevoCliente = 2;
	private final int opcionesSalida = -1;

	public String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}

	public void chooseOptionSet(int set) {
		int option;
		switch (set) {
			case 1:
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionPrincipales(option);
				break;
			case 2:
				IO.out.toTerminal("\n" + getOpciones(listaOpcionesNuevoCliente));
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionNuevoCliente(option);
				boolean satisfactory = chooseOptionNuevoCliente(option);
				printIsSatisfactory(satisfactory);
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
		IO.out.toTerminal("\n" + getOpciones(listaOpcionesPrincipales));
		chooseOptionSet(opcionesPrincipales);
	}

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:
				chooseOptionSet(opcionesNuevoCliente);
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
				IO.out.toTerminal(option);
				break;
			case 7:
				IO.out.toTerminal(option);
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
				chooseOptionSet(opcionesSalida);
				baseDeDatos.exitWithoutSave();
			default:
				IO.out.toTerminal("Write one of the options.");
				chooseOptionSet(opcionesPrincipales);
				break;
		}
	}

	private boolean chooseOptionNuevoCliente(int option){
		switch (option) {
			case 1:
				return baseDeDatos.nuevoParticular(true);
			case 2:
				return baseDeDatos.nuevoParticular(false);
			case 3:
				return baseDeDatos.nuevaEmpresa(true);
			case 4:
				return baseDeDatos.nuevaEmpresa(false);
			default:
				IO.out.toTerminal("Write one of the options.");
				chooseOptionSet(opcionesPrincipales);
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
				chooseOptionSet(opcionesPrincipales);
				break;
			default:
				IO.out.toTerminal("Write one of the options.");
				chooseOptionSet(opcionesSalida);
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
