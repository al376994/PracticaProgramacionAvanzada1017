package Auxiliares;

public class Output {

	public <T> void toTerminal(T output) {
		System.out.println(output);
	}

	public <T> void toTerminal(T output, String eol) {
		String print = output + eol;
		System.out.print(print);
	}

}
