package Auxiliares;

public class Output {

	void toTerminal(String output) {
		System.out.println(output);
	}

	void toTerminal(String output, String eol) {
		output += eol;
		System.out.print(output);
	}

}
