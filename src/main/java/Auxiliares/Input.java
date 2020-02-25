package Auxiliares;

import java.util.Scanner;

public class Input {

	private static Scanner s = new Scanner(System.in);

	public String fromTerminalAnString(String text) {
		IO.out.toTerminal(text, " ");
		return s.nextLine();
	}

	public int fromTerminalAnInt(String text) {
		IO.out.toTerminal(text, " ");
		return s.nextInt();
	}

	public double fromTerminalADouble(String text) {
		IO.out.toTerminal(text, " ");
		return s.nextDouble();
	}

}
