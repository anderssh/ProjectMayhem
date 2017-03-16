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
				invalid = false;
				registerWithoutTemplate();
			}
			else {
				System.out.println("Skriv 'j' eller 'n' ");
			}
		}
	}
	public void registerWithoutTemplate () throws Exception {
		ResultSet template = null;
		input_workout(template);	
	}
	
	public void registerWithTemplate () throws Exception {
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		
		ResultSet chosen_template = null;
		boolean found_template = false;
		
		while(found_template == false){
			System.out.println("Her er en liste over dine tidligere treninger:");
			System.out.println("");
			System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Idrett");
			System.out.println("-------------------------------------------");
			ResultSet rs_workouts = acc.getAllWorkouts();
			int idprev = 0;
		    while (rs_workouts.next()) {
	            int id = rs_workouts.getInt("Trening_ID");
	            String date = rs_workouts.getString("dato");
	            String sport = rs_workouts.getString("idrett");
	            String exer = rs_workouts.getString("ovelse");
	            if (id == idprev){
	            	System.out.println("\t" + "-" + exer );
	            }else{
	            	System.out.println("");
	            	System.out.println("[" + id + "]" + "\t" + date +  "\t" + sport);
	            	System.out.println("\t" + "-" + exer );
	            }
	            idprev = id;
	        }
		    System.out.println("");
		    System.out.println("Skriv inn en [id] for å utforske en spesifikk trening:");
		    Scanner in = new Scanner(System.in);
			int id = in.nextInt();
			ResultSet rs_workoutOnID = acc.getWorkoutOnID(id);
			System.out.println("Id" + "\t" + "Dato" + "\t"+ "\t"  + "Tid"+ "\t" + "\t" + "Varighet");
			System.out.println("-------------------------------------------");
		    while (rs_workoutOnID.next()) {
	            String date 	= rs_workoutOnID.getString("dato");
	            String time 	= rs_workoutOnID.getString("tid");
	            String duration 	= rs_workoutOnID.getString("varighet");
	            System.out.println("[" + id + "]" + "\t" + date + "\t" + time + "\t" + duration);
				
		    }
		    
		    System.out.println("");
		    System.out.println("Vil du bruke denne treningen som mal?(j/n)");
		    Scanner in1 = new Scanner(System.in);
		    boolean temp = true;
		    while(temp) {
				String i = in1.nextLine();
				System.out.println(i);
			
				if (i.toLowerCase().equals("j")) {
					temp = false;
					found_template = true;
					chosen_template = rs_workoutOnID;
				}
				else if(i.toLowerCase().equals("n")) {
					temp = false;
				}
				else {
					System.out.println("Skriv 'j' eller 'n' ");
				}
			}
		    
		}		
		input_workout(chosen_template);
	}	
	public void input_workout(ResultSet template) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.println("Vennligst legg inn informasjon om dagens trening!");
		if (template == null){
			ResultSet rs_exercises = null;
			MySQLAccess acc = new MySQLAccess();
			acc.makeConnection();
			Scanner workout_info = new Scanner(System.in);
			
			System.out.println("Dato [YYYY-MM-DD]:");
			String date = workout_info.nextLine();
			
			System.out.println("Tid [HH:MM]:");
			String time = workout_info.nextLine();
			
			System.out.println("Varighet [HH:MM]:");
			String duration = workout_info.nextLine();
			
			System.out.println("Hvordan var din prestasjon? [1-10]");
			String performance = workout_info.nextLine();
			
			System.out.println("Hvordan fï¿½lte du deg? [1-10]");
			String form = workout_info.nextLine();
			
			System.out.println("Bedrev du en av disse idretten? [0] for ï¿½ legge til ny idrett.");
			ResultSet rs_all_sports = acc.getAllSports();
			int sport_ID = 0;
			String sport_name;
			while(rs_all_sports.next()){
				sport_ID = rs_all_sports.getInt("idrett_id");
				sport_name = rs_all_sports.getString("navn");
				System.out.println("[" + sport_ID + "]" + "\t" + sport_name);
				
			}
			ResultSet rs_idrett = null;
			int sport_type = workout_info.nextInt();
			String new_sport;
			if(sport_type == 0){
				System.out.println("Skriv navn pï¿½ ny idrett");
				Scanner in3 = new Scanner(System.in);
				new_sport = in3.nextLine();
				System.out.println(new_sport);
				rs_idrett=acc.addSport(new_sport);
				rs_idrett.next();
				sport_ID = (int) rs_idrett.getLong(1);
			}
			else if(sport_type != 0){
				sport_ID = sport_type;
			}
			System.out.println("Hvis du vil, legg til et notat.");
			Scanner in4 = new Scanner(System.in);
			String note = in4.nextLine();
			
			acc.addWorkout(date, time, duration, performance, form, sport_ID, note);
			
			System.out.println("Trente du ute[0] eller inne[1]?");
			
			boolean invalid = true;
			int location;
			String weather;
			int temperature;
			String ventilation;
			int crowd;
			while(invalid){
				location = in.nextInt();
				if (location==0){
					Scanner in1 = new Scanner(System.in);
					invalid = false;
					System.out.println("Hvordan var vÃ¦ret?");
					weather = in1.nextLine();
					System.out.println("Hva var temperaturen?");
					temperature = in1.nextInt();
					
				}
				else if(location==1)
				{
					Scanner in2 = new Scanner(System.in);
					invalid = false;
					System.out.println("Hvordan var ventilasjonen?");
					ventilation = in2.nextLine();
					System.out.println("Antall tilskuere?");
					crowd = in2.nextInt();
				}
				else{
					System.out.println("Skriv '0' eller '1'");
					}
			}
			
			System.out.println("Her er alle ï¿½velsene du kan velge i. [0] for ï¿½ legge til ny ï¿½velse.\n");
			rs_exercises = acc.getAllExercises();
			while(rs_exercises.next()){
				int exercise_id = rs_exercises.getInt("ovelse_ID");
				String exercise_name = rs_exercises.getString("navn");
				System.out.println("[" + exercise_id + "]" + "\t" + exercise_name);
			}
			
			
			/*Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			rs_exercises = acc.getExerciseOnID(i);
			while(rs_exercises.next()){
				String s = rs_exercises.getString("navn");
				System.out.println("Du har valgt " + s);
			}*/
			
			
						
			
		}else{
			ResultSetMetaData metaData = template.getMetaData();
			MySQLAccess acc = new MySQLAccess();
			int count = metaData.getColumnCount(); //number of column
			String columnName[] = new String[count];
			for (int i = 1; i <= count; i++){
			   columnName[i-1] = metaData.getColumnLabel(i);

			   System.out.println("Skriv inn:" + columnName[i-1]);
			   	

			   System.out.println(" Skriv inn følgende:" + columnName[i-1]);


			   //Scanner in = new Scanner(System.in);
			   String result = columnName[i-1];
			   result = in.nextLine();
			}
			//addWorkout(String date, String time, String duration, int num_exercises, String performance, String form, int sport_ID, String note);
		}
	}
	
}
