package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;
import mayhem.RegisterTraining;
import mayhem.Notes;
import java.sql.*;
public class Main {
	
	

	public static void main(String[] args) throws Exception {
		//PropertyHandling ph = new PropertyHandling();
		//ph.CreateDatabaseProperties();
		boolean done = false;
		while(!done){
			System.out.println("Velg handling: \n");
			System.out.println("[1] \t Registrer trening");
			System.out.println("[2] \t Se tidligere trening");
			System.out.println("[3] \t Statistikk ");
			System.out.println("[4] \t Notater");
			System.out.println("[5] \t Se tidligere trening ");
			System.out.println("[6] \t Se / Legg til idrett ");
			System.out.println("[7] \t Avslutt");
			
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			switch(i){
				case 1:
						RegisterTraining regTraining= new RegisterTraining();
						regTraining.startRegister();
						break;
				case 2: 
					
						break;
				case 3: 
						
						break;
				case 4:	
						Notes note = new Notes();
						note.viewNotes();
						break;
				case 5: 
						break;
				case 6: 
					break;
				case 7: done = true;
						break;
				default: 
					System.out.println("Skriv inn tall fra 1-7");
			}
		}
		
		System.out.print("Takk for idag");
		}		
}

