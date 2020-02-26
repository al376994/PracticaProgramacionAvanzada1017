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
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionPrincipales(option);
				break;
			case "nuevoCliente":
				option = IO.in.fromTerminalAskInt(inputText);
				chooseOptionNuevoCliente(option);
				break;
			default:
				IO.out.toTerminal("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.fromTerminalAskInt("");
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
				option = IO.in.fromTerminalAskInt("\nEscribe el número de la opción que quieres elegir:");
				boolean satisfactory = chooseOptionNuevoCliente(option);
				if (satisfactory) IO.out.toTerminal("Operacion completada con exito");
				else IO.out.toTerminal("La operacion no se ha podido realizar");
				IO.waitIntro();
				break;
			case 2:
				String nif;
				nif = IO.in.fromTerminalAskString("\nEscribe el nif del cliente que quieres borrar");
				baseDeDatos.borrarCliente(nif);
				break;
			case 3:
				IO.out.toTerminal(option);
				break;
			case 4:
				IO.out.toTerminal(option);
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
			default:
				IO.out.toTerminal("Write one of the options.");
				ChooseOptionSet("principales");
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
				ChooseOptionSet("nuevoCliente");
				break;
		}
		return false;
	}

}
