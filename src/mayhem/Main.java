package mayhem;

import java.util.Scanner;
import mayhem.RegisterTraining;
import mayhem.Notes;
import mayhem.Goals;
import mayhem.Workouts;
import mayhem.ScriptRunner;
import mayhem.Workouts;
import java.sql.*;

public class Main {

	public static void main(String[] args) throws Exception {

		boolean done = false;
		while(!done){
			System.out.println("Velg handling: \n");
			System.out.println("[1] \t Registrer trening");
			System.out.println("[2] \t Se tidligere trening");
			System.out.println("[3] \t Statistikk ");
			System.out.println("[4] \t Notater");
			System.out.println("[5] \t Oppdater mål ");
			System.out.println("[6] \t Se / Legg til idrett ");
			System.out.println("[7] \t Se / Legg til øvelse ");
			System.out.println("[8] \t Se / Legg til treningstype ");
			System.out.println("[9] \t Sett opp database");
			System.out.println("[10] \t Avslutt");
			
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			switch(i){
				case 1:
						RegisterTraining regTraining= new RegisterTraining();
						regTraining.startRegister();
						break;
				case 2: 
						Workouts pwork = new Workouts();
						pwork.prevWorkouts ();
						break;
				case 3: 
						Statistics stats = new Statistics();
						stats.statsMenu();
						break;
				case 4:	
						Notes note = new Notes();
						note.viewNotes();
						break;
				case 5: 
						Goals goals = new Goals();
						goals.updateGoal();
						break;
				case 6: 
						RegisterSport regSport = new RegisterSport();
						regSport.displaySports();
						break;
				case 7: 
						RegisterExercise regEx = new RegisterExercise();
						regEx.displayExercises();
						break;
				case 8:
						RegisterExerciseType regExType = new RegisterExerciseType();
						regExType.displayExerciseTypes();
						break;
				case 9: 
						MySQLAccess setup = new MySQLAccess();
						setup.setupDatabase();
				case 10:
						done = true;
						break;
						
				default: 
					System.out.println("Skriv inn tall fra 1-10");
			}
		}
		
		System.out.print("Takk for idag");
		}		
}

