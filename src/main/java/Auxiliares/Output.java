package Auxiliares;

import java.util.Collection;

import static BaseDeDatos.BaseDeDatos.EN_TERMINAL;

// Todos los outputs se hacer aquí, siempre que se quiera que algo de informacion se envie a algun medio para que
// lo vea el usuario se utiliza esta clase.

public class Output {

	private final String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";

	public <T> void print(T output) {
		System.out.println(output);
	}

	public <T> void print(T output, String eol) {
		String print = output + eol;
		System.out.print(print);
	}

	public <T> void listar(Collection<T> lista) {
		if (EN_TERMINAL) {
			for (T elemento : lista) {
				IO.out.print("\n" + elemento);
			}
			IO.out.print(SEPARADOR);
			IO.waitIntro();
		}
	}

}
