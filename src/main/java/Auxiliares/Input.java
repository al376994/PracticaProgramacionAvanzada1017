package Auxiliares;

import java.util.Scanner;

public class Input {

	private static Scanner s = new Scanner(System.in);

	String fromTerminalAnString() {
		return s.nextLine();
	}

	int fromTerminalAnInt() {
		return s.nextInt();
	}

	double fromTerminalADouble() {
		return s.nextDouble();
	}

}
