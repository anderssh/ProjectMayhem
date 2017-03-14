package mayhem;

import java.sql.*;
import java.util.Scanner;
import mayhem.MySQLAccess;

public class RegisterTraining {
	
	public void startRegister () throws Exception {
	
	System.out.println("Nå skal du registrere en ny trening");
	System.out.println("Ønsker du å benytte en mal? [J/N]");
	
	Scanner in = new Scanner(System.in);
	
	boolean invalid = true;
	while(invalid) {
			String i = in.nextLine();
			System.out.println(i);
		
			if (i.toLowerCase().equals("j")) {
				
				//System.out.println("ja in while - RegTraining");
				invalid = false;
				registerWithTemplate();
			}
			else if(i.toLowerCase().equals("n")) {
				System.out.println("nei in while - RegTraining");
				invalid = false;
			}
			else {
				System.out.println("Skriv 'j' eller 'n' ");
			}
		}
	}
	public void registerWithoutTemplate () {
		
	}
	public void input_workout(ResultSet template){
		System.out.println("looooooooool");
		
	}
	
	
	public void registerWithTemplate () throws Exception {
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		
		boolean found_template = false;
		while(found_template == false){
			System.out.println("Her er en liste over dine tidligere treninger:");
			ResultSet rs_workouts = acc.getAllWorkouts();
		    while (rs_workouts.next()) {
	            int workout = rs_workouts.getInt("Trening_ID");
	            String date = rs_workouts.getString("dato");
	            System.out.println(date + ", " + workout);
	        }
		    System.out.println("Skriv inn trenings_ID for å utforske en spesifikk trening:");
		    Scanner in = new Scanner(System.in);
			int id = in.nextInt();
			ResultSet rs_workoutOnID = acc.getWorkoutOnID(id);
		    while (rs_workoutOnID.next()) {
	            id  = rs_workoutOnID.getInt("Trening_ID");
	            String date 	= rs_workoutOnID.getString("dato");
	            System.out.println(date + ", " + id);
				
		    }
		    System.out.println("Vil du bruke denne treningen som mal?(j/n)");
		    Scanner in1 = new Scanner(System.in);
		    boolean temp = true;
		    while(temp) {
				String i = in1.nextLine();
				System.out.println(i);
			
				if (i.toLowerCase().equals("j")) {
					
					System.out.println("JAAAAA");
					temp = false;
					found_template = true;
					ResultSet chosen_template;
					chosen_template = rs_workoutOnID;
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
		
	}	
}
