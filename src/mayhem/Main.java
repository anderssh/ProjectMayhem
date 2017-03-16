package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;
import mayhem.RegisterTraining;
import mayhem.Notes;
import mayhem.Test_create_db;
import mayhem.ScriptRunner;
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
			System.out.println("[5] \t Oppdater m�l ");
			System.out.println("[6] \t Avslutt");
			System.out.println("[7] \t Sett opp database");
			
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			switch(i){
				case 1:
						RegisterTraining regTraining= new RegisterTraining();
						regTraining.startRegister();
						break;
				case 2: 
						ResultSet bs = null;
						MySQLAccess acc = new MySQLAccess();
						acc.makeConnection();
						bs = acc.getWorkoutOnID(8);
						break;
				case 3: 
						Statistics stats = new Statistics();
						stats.viewStats();
						break;
				case 4:	
						Notes note = new Notes();
						note.viewNotes();
						break;
				case 5: 
						Goals goals = new Goals();
						goals.updateGoal();
						break;
				case 6: done = true;
						break;
				case 7: 
						MySQLAccess setup = new MySQLAccess();
						setup.setupDatabase();
						break;
				default: 
					System.out.println("Skriv inn tall fra 1-6");
			}
		}
		
		System.out.print("Takk for idag");
		}		
}

