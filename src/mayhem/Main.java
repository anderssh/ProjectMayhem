package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;
import mayhem.RegisterTraining;
import mayhem.Notes;

public class Main {

	public static void main(String[] args) throws Exception {
		boolean done = false;
		while(!done){
			System.out.println("Velg handling: \n");
			System.out.println("[1] \t Registrer trening");
			System.out.println("[2] \t Se tidligere trening");
			System.out.println("[3] \t Statistikk ");
			System.out.println("[4] \t Notater");
			System.out.println("[5] \t Se tidligere trening ");
			System.out.println("[6] \t Avslutt");
			
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			switch(i){
				case 1:
<<<<<<< HEAD
						System.out.println("jjaja");
						RegisterTraining regTraining= new RegisterTraining();
=======
						RegisterTraining regTraining = new RegisterTraining();
>>>>>>> 5e3d3d755cd6714279adbfe226f665d03815be2d
						regTraining.startRegister();
						break;
				case 2: 
						System.out.println("asasas");
						break;
				case 3: 
						break;
				case 4:	
						Notes note = new Notes();
						note.notesHandler();
						break;
				case 5: 
						break;
				case 6: done = true;
						break;
				default: 
					System.out.println("Skriv inn tall fra 1-6");
<<<<<<< HEAD
		
		
			}
		}
		System.out.print("Takk for idag");
=======
			}		
		}
>>>>>>> 5e3d3d755cd6714279adbfe226f665d03815be2d
	}
}

