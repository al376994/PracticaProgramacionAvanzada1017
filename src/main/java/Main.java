import Auxiliares.IOTerminal;
import Auxiliares.InOut;
import BaseDeDatos.BaseDeDatos;
import Menu.MenuTerminal;

public class Main {

    public static void main(String args[]) {

        BaseDeDatos baseDeDatos;

        if (BaseDeDatos.EN_TERMINAL) {
            baseDeDatos = new BaseDeDatos();
            MenuTerminal menuTerminal = new MenuTerminal();
            menuTerminal.run(baseDeDatos);
        }

    }

}