import Auxiliares.IO;
import BaseDeDatos.BaseDeDatos;
import Menu.MenuTerminal;

public class Main {

    public static void main(String args[]) {

        BaseDeDatos baseDeDatos;

        if (BaseDeDatos.EN_TERMINAL) {
            baseDeDatos = new BaseDeDatos();
            MenuTerminal.run(baseDeDatos);
        }

    }

}