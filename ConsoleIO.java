package codes;

import java.util.*;

public class ConsoleIO {
	
	private Scanner sc;
	
	public ConsoleIO() {
		sc = new Scanner(System.in);
	}
	
	public void printLine(String line) {
		System.out.println(line);
	}
	
	public int readInt() {
		return sc.nextInt();
	}

}
