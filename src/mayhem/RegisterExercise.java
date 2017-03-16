
package mayhem;

import java.sql.ResultSet;
import java.util.Scanner; 

public class RegisterExercise {
	
	MySQLAccess acc = null;
	
	public void displayExercises () throws Exception {
		
		acc = new MySQLAccess();
		acc.makeConnection();
		
		ResultSet rs_exercise = null;
		rs_exercise = acc.getAllExercises();
		
		System.out.println("Her ser du alle øvelsene som finnes i systemet nå:");
		
		System.out.println("");
		
		while (rs_exercise.next()) {
            int ID = rs_exercise.getInt("ovelse_ID");
            String ovelse = rs_exercise.getString("navn");
            String description = rs_exercise.getString("beskrivelse");
            System.out.format("[%d]  " +"%-30s%-50s", ID, ovelse, description);
            System.out.println("");
		}
		System.out.println("-------------------------------------------------");
		System.out.println("");
		System.out.println("Ønsker du å legge til en ny øvelse? [J/N]");
		
		Scanner in1 = new Scanner(System.in);
	    boolean temp = true;
	    while(temp) {
			String i = in1.nextLine();
		
			if (i.toLowerCase().equals("j")) {
				temp = false;
				addExercise();
			}
			else if(i.toLowerCase().equals("n")) {
				temp = false;
			}
			else {
				System.out.println("Skriv 'J' eller 'N' ");
			}
		}
	}
	private void addExercise() throws Exception {
		
		Scanner in2 = new Scanner(System.in);
		
	    String newExerciseName = null;
	    int workoutType;
	    String newDescription = null;
		ResultSet workoutTypes = null;

	    System.out.println("Vennligst skriv inn navnet på øvelsen du ønsker å legge til: ");
	    System.out.println("");
	    
	    newExerciseName = in2.nextLine();
	    System.out.println("");
	    
	    workoutTypes = acc.getWorkoutType();
	    System.out.println("Her ser du treningstypene som finnes, velg den som passer til din nye øvelsen:");
	    System.out.println("(Hvis treningstypen ikke finnes foreslår jeg å benytte hovedmenyen for å opprett ny)");
	    
	    System.out.println("");
	    
		while (workoutTypes.next()) {
            int ID = workoutTypes.getInt("treningstype_ID");
            String workoutTypeName = workoutTypes.getString("navn");
            System.out.println( "[" + ID + "]" + "\t" + workoutTypeName);  
		}
		workoutType = in2.nextInt();

		System.out.println("Skriv en liten beskrivelse til for den nye øvelsen (Bør være mindre enn 100 tegn):");
		
		Scanner in3 = new Scanner(System.in);
		newDescription = in3.nextLine();
		
		ResultSet thisID = null;
	    thisID = acc.addExercise(newExerciseName, newDescription, workoutType);
	    
	    System.out.println("Hvilke øvelser kan denne nye øvelsen erstatte?");
	    System.out.println("Skriv in tallet til de aktuelle øvelsene, med linjeskift i mellom. [0] for å avslutte");
	    
	    boolean temp = true;
	    int newSimilarExercise;
	    while (temp == true) {
	    	newSimilarExercise = in3.nextInt();
	    	
	    	
	    }
	    
	    System.out.println("");
	    System.out.println("Gratulerer! Du har lagt til øvelsen " + newExerciseName + "!");
	    System.out.println("");
	    
	    displayExercises();
	}
}
	