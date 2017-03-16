package mayhem;
import java.util.Scanner;
import java.sql.*;

public class Notes {
	public void viewNotes() throws Exception{
		boolean done = false;
		while(!done){
			ResultSet rs_workouts = null;
			MySQLAccess acc = new MySQLAccess();
			acc.makeConnection();
			System.out.println("Dette er alle treninger som inneholder notater");
			rs_workouts = acc.getWorkoutsWithNotes();
			while(rs_workouts.next()){
				int trening_id = rs_workouts.getInt("trening_ID");
				String date = rs_workouts.getString("dato");
				System.out.println(trening_id + "\t" + date);
			}
			System.out.println("Velg hva du vil se\n");
			
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			ResultSet rs_selected_notes = null; 
			rs_selected_notes=acc.getNotesOnWorkoutID(i);
			while (rs_selected_notes.next()){	
				String notes = rs_selected_notes.getString("notat");
				System.out.println(notes);
			   }
			System.out.println("Vil du se flere notater? [j/n]");
			Scanner in1 = new Scanner(System.in);
			//String s = in1.nextLine();
			
			boolean invalid = true;
			while(invalid){
				String s = in1.nextLine();
				if (s.toLowerCase().equals("j")){
					done = false;
					invalid = false;
				}
				else if(s.toLowerCase().equals("n")){
					done = true;
					invalid = false;
				}
				else{
					System.out.println("Skriv 'j' eller 'n'");
					}
			}
		}
	}	
}
