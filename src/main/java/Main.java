import BaseDeDatos.BaseDeDatos;
import Menu.MenuTerminal;

public class Main {

    public static void main(String args[]) {    // TODO: Profesor(el tipo estaba mal, no se podía ejecutar) si lo estaba lo arreglé después porque ahora esta bien

        BaseDeDatos baseDeDatos;

        if (BaseDeDatos.EN_TERMINAL) {
            baseDeDatos = new BaseDeDatos();
            MenuTerminal menuTerminal = new MenuTerminal();
            menuTerminal.run(baseDeDatos);
        }

    }

}