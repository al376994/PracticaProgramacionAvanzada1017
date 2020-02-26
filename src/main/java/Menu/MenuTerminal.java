package Menu;

import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;

public class MenuTerminal {

	public static void run(BaseDeDatos baseDeDatos) {

		MenuOpciones menuOpciones = new MenuOpciones();
		IO.out.toTerminal("Menu de Terminal de la aplicación de Facturación");
		printOptions(menuOpciones.listaOpcionesPrincipales);
		menuOpciones.chooseOptionSet("principales");
	}

	static private void printOptions(String[] options) {
		for (String option : options) {
			IO.out.toTerminal(option);
		}
	}

}
