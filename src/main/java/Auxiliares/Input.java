package Auxiliares;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

	private static Scanner s = new Scanner(System.in);

	public String fromTerminalAskString(String text) {
		IO.out.toTerminal(text, " ");
		return s.nextLine();
	}

	public int fromTerminalAskInt(String text) {
		while (true) {
			IO.out.toTerminal(text, " ");
			try {
				int input = s.nextInt();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.toTerminal("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public double fromTerminalAskDouble(String text) {
		while (true) {
			IO.out.toTerminal(text, " ");
			try {
				double input = s.nextDouble();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.toTerminal("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public void waitIntro() {
		IO.out.toTerminal("Pulsa cualquier tecla para continuar.", " ");
		s.nextLine();
		s.nextLine();
	}

}
