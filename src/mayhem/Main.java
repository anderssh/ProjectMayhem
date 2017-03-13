package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;
import mayhem.RegisterTraining;

public class Main {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Velg handling: \n");
		System.out.println("[1] \t Registrer trening");
		System.out.println("[2] \t Se tidligere trening");
		System.out.println("[3] \t Statistikk ");
		System.out.println("[4] \t Notater");
		System.out.println("[5] \t Se tidligere trening ");
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		switch(i){
			case 1:
					System.out.println("jjaja");
					RegisterTraining regTraining= new RegisterTraining();
					regTraining.startRegister();
					break;
			case 2: System.out.println("asasas");
					break;
			case 3: 
		}
	}
}
