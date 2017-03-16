package mayhem;

import java.sql.ResultSet;
import java.util.Scanner; 

public class RegisterExercise {
	
	MySQLAccess acc = null;
	
	public void displayExercises () throws Exception {
		
		acc = new MySQLAccess();
		acc.makeConnection();
		
		ResultSet rs_sports = null;
		rs_sports = acc.getAllSports();
		
		System.out.println("Her ser du alle idrettene som finnes i systemet nå:");
		
		System.out.println("");
		
		while (rs_sports.next()) {
            int ID = rs_sports.getInt("idrett_ID");
            String sport = rs_sports.getString("navn");
            System.out.println( "[" + ID + "]" + "\t" + sport);  
		}
		System.out.println("-------------------------------------------------");
		System.out.println("");
		System.out.println("Ønsker du å legge til en ny idrett? [J/N]");
		
		Scanner in1 = new Scanner(System.in);
	    boolean temp = true;
	    while(temp) {
			String i = in1.nextLine();
		
			if (i.toLowerCase().equals("j")) {
				temp = false;
				addSport();
			}
			else if(i.toLowerCase().equals("n")) {
				temp = false;
			}
			else {
				System.out.println("Skriv 'j' eller 'n' ");
			}
		}
	}
	private void addSport() throws Exception {
		
		Scanner in2 = new Scanner(System.in);
		
	    String newSport = null;
	    System.out.println("Vennligst skriv inn navnet på idretten du ønsker å legge til: ");
	    System.out.println("");
	    
	    newSport = in2.nextLine();
	    acc.addSport(newSport);
	    System.out.println("");
	    
	    System.out.println("Gratulerer! Du har lagt til idretten " + newSport);
	    System.out.println("");
	}
}
	