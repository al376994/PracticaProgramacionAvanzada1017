package Auxiliares;

// El unico objetivo de esta clase es tener el input y el output desde una sola clase aunque cada uno sea manejado por
// una clase distinta.

public class IO {
	public static final Input in = new Input();
	public static final Output out = new Output();
	public static void waitIntro() {
		in.waitIntro();
	}
}
