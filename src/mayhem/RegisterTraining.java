package mayhem;

import java.sql.*
import java.util.Scanner;
import mayhem.MySQLAccess;

public class RegisterTraining {
	
	public void startRegister () {
	
	System.out.println("Nå skal du registrere en ny trening");
	System.out.println("Ønsker du å benytte en mal? [J/N]");
	
	Scanner in = new Scanner(System.in);
	
	boolean temp = true;
	while(temp) {
			String i = in.nextLine();
			System.out.println(i);
		
			if (i.toLowerCase().equals("j")) {
				
				System.out.println("JAAAAA");
				temp = false;
			}
			else if(i.toLowerCase().equals("n")) {
				System.out.println("NEIIII");
				temp = false;
			}
			else {
				System.out.println("Skriv 'j' eller 'n' ");
			}
		}
	}
	public void registerWithoutTemplate () {
		
	}
	
	
	public void registerWithTemplate () {
		MySQLAccess acc = new MYSQLAccess();
		
		boolean found_template = false;
		while(found_template = false){
			System.out.println("Her er en liste over dine tidligere treninger:");
			ResultSet rs_workouts = acc.getAllWorkouts();
		    while (rs_workouts.next()) {
	            String workout = rs_workouts.getString("Trening_ID");
	            String date = rs_workouts.getString("dato");
	            System.out.println(date + ", " + workout);
	     
	        }
		    
		    /*System.out.println("Utforsk en spesifikk trening basert p� trenings_ID");
		    Scanner in = new Scanner(System.in);
			String id = in.nextLine();
			rs_workoutOnID = GetWorkoutOnID(toInt(id));
		    while (rs_workoutOnID.next()) {
	            String workout =rs_workoutOnID.getString("Trening_ID");
	            String date = rs_workoutOnID.getString("dato");
	            System.out.println(date + ", " + workout);
				
		    }
		    
		    System.out.println("Vil du bruke denne treningen som mal?(j/n)");
		    Scanner in = new Scanner(System.in);
		    String found_template = in.nextLine();*/
		    found_template = true;
		
		}
	}
}
