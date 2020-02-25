package Menu;

import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;

public class MenuOpciones {

	BaseDeDatos baseDeDatos = new BaseDeDatos();

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
								"10.\tRecuperar todas las facturas de un cliente"};

	public String getOpciones() {
		return String.join("\n", opcionesPrincipales);
	}

	public void ChooseOptionSet(String set) {
		int option;
		switch (set.toLowerCase()) {
			case "principales":
				option = IO.in.fromTerminalAnInt("");
				ChooseOptionPrincipales(option);
				break;
			default:
				IO.out.toTerminal("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.fromTerminalAnInt("");
				ChooseOptionPrincipales(option);
				break;
		}

	}

	public void ChooseOptionPrincipales(int option) {

		switch(option) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 9:

				break;
			case 10:

				break;
			default:

				break;
		}

	}

}
