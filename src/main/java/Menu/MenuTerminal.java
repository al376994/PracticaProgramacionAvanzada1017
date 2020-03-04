package Menu;

import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;

public class MenuTerminal {

	public static void run(BaseDeDatos baseDeDatos) {
		MenuOpciones menuOpciones = new MenuOpciones();
		IO.out.toTerminal("Menu de Terminal de la aplicación de Facturación");
		menuOpciones.chooseOptionSet(1);
	}

}
