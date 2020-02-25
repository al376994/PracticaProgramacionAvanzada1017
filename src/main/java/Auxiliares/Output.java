package Auxiliares;

public class Output {

	public void toTerminal(String output) {
		System.out.println(output);
	}

	public void toTerminal(String output, String eol) {
		output += eol;
		System.out.print(output);
	}

}
