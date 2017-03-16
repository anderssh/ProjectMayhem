package mayhem;

import java.sql.ResultSet;
import java.util.Scanner; 

public class RegisterExerciseType {
	
	MySQLAccess acc = null;
	
	public void displayExerciseTypes () throws Exception {
		
		acc = new MySQLAccess();
		acc.makeConnection();
		
		ResultSet rs_exerciseTypes = null;
		rs_exerciseTypes = acc.getAllExerciseTypes();
		
		System.out.println("Her ser du alle treningstypene:");
		
		System.out.println("");
		
		while (rs_exerciseTypes.next()) {
            int ID = rs_exerciseTypes.getInt("treningstype_ID");
            String exerciseType = rs_exerciseTypes.getString("navn");
            System.out.println( "[" + ID + "]" + "\t" + exerciseType);  
		}
		System.out.println("-------------------------------------------------");
		System.out.println("");
		System.out.println("Ønsker du å legge til en ny treningstype? [J/N]");
		
		Scanner in1 = new Scanner(System.in);
	    boolean temp = true;
	    while(temp) {
			String i = in1.nextLine();
		
			if (i.toLowerCase().equals("j")) {
				temp = false;
				addExerciseType();
			}
			else if(i.toLowerCase().equals("n")) {
				temp = false;
			}
			else {
				System.out.println("Skriv 'J' eller 'N' ");
			}
		}
	}
	private void addExerciseType() throws Exception {
		
		Scanner in2 = new Scanner(System.in);
		
	    String newExerciseType = null;
	    System.out.println("Vennligst skriv inn navnet på treningstypen du ønsker å legge til: ");
	    System.out.println("");
	    
	    newExerciseType = in2.nextLine();
	    acc.addExerciseType(newExerciseType);
	    System.out.println("");
	    
	    System.out.println("Gratulerer! Du har lagt til treningstypen " + newExerciseType + "!");
	    System.out.println("");
	    displayExerciseTypes();
	}
}
	