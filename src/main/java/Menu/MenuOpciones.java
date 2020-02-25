package Menu;

import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;

public class MenuOpciones {

	BaseDeDatos baseDeDatos = new BaseDeDatos();

	final String inputText = "\nEscribe el número de la opción que quieres elegir:";

	final String[] opcionesPrincipales = {
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

	final String[] opcionesNuevoCliente = {
			"1. \tDar de alta un Particular",
			"2. \tDar de alta un Particular aleatorio",
			"3. \tDar de alta una Empresa",
			"4. \tDar de alta una Empresa aleatoria"
	};

	public String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}

	public void ChooseOptionSet(String set) {
		int option;
		switch (set.toLowerCase()) {
			case "principales":
				option = IO.in.fromTerminalAnInt(inputText);
				chooseOptionPrincipales(option);
				break;
			case "nuevoCliente":
				option = IO.in.fromTerminalAnInt(inputText);
				chooseOptionNuevoCliente(option);
				break;
			default:
				IO.out.toTerminal("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.fromTerminalAnInt("");
				chooseOptionPrincipales(option);
				break;
		}
		IO.out.toTerminal("\n" + getOpciones(opcionesPrincipales));
		ChooseOptionSet("principales");
	}

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:
				IO.out.toTerminal("\n" + getOpciones(opcionesNuevoCliente));
				option = IO.in.fromTerminalAnInt("\nEscribe el número de la opción que quieres elegir:");
				chooseOptionNuevoCliente(option);
				break;
			case 2:
				IO.out.toTerminal(option);
				break;
			case 3:
				IO.out.toTerminal(option);
				break;
			case 4:
				IO.out.toTerminal(option);
				break;
			case 5:
				IO.out.toTerminal(option);
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
			default:
				IO.out.toTerminal("Write one of the options.");
				ChooseOptionSet("principales");
				break;
		}
	}

	private void chooseOptionNuevoCliente(int option){
		switch (option) {
			case 1:
				IO.out.toTerminal(option);
				break;
			case 2:
				IO.out.toTerminal(option);
				break;
			case 3:
				IO.out.toTerminal(option);
				break;
			case 4:
				IO.out.toTerminal(option);
				break;
			default:
				IO.out.toTerminal("Write one of the options.");
				ChooseOptionSet("nuevoCliente");
				break;
		}
	}

}
