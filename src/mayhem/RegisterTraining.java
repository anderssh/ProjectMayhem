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
			System.out.println("----Dato-----TreningsID");
			ResultSet rs_workouts = acc.getAllWorkouts();
		    while (rs_workouts.next()) {
	            int workout = rs_workouts.getInt("Trening_ID");
	            String date = rs_workouts.getString("dato");
	            System.out.println(date + "       " + workout);
	        }
		    System.out.println("Skriv inn en TreningsID for å utforske en spesifikk trening:");
		    Scanner in = new Scanner(System.in);
			int id = in.nextInt();
			ResultSet rs_workoutOnID = acc.getWorkoutOnID(id);
			System.out.println("----Dato-----TreningsID");
		    while (rs_workoutOnID.next()) {
	            id  = rs_workoutOnID.getInt("Trening_ID");
	            String date 	= rs_workoutOnID.getString("dato");
	            System.out.println(date + "       " + id);
				
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
		input_workout(chosen_template);
	}	
	public void input_workout(ResultSet template) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.println("Velkommen! Her kan du skrive inn resultatene dine for dagens trening!");
		if (template == null){
			ResultSet rs_exercises = null;
			MySQLAccess acc = new MySQLAccess();
			acc.makeConnection();
			Scanner workout_info = new Scanner(System.in);
			
			System.out.println("Dato [YYYY-MM-DD]:");
			String date = workout_info.nextLine();
			
			System.out.println("Tid [HH-MM]:");
			String time = workout_info.nextLine();
			
			System.out.println("Varighet [HH-MM]:");
			String duration = workout_info.nextLine();
			
			System.out.println("Hvordan var din prestasjon? [1-10]");
			String performance = workout_info.nextLine();
			
			System.out.println("Hvordan følte du deg? [1-10]");
			String form = workout_info.nextLine();
			
			System.out.println("Bedrev du en av disse idretten? [0] for å legge til ny idrett.");
			
			int sport_type = workout_info.nextInt();
			
			
			System.out.println("Trente du ute[0] eller inne[1]?");
			
			boolean invalid = true;
			while(invalid){
				int location = in.nextInt();
				if (location==0){
					Scanner in1 = new Scanner(System.in);
					invalid = false;
					System.out.println("Hvordan var været?");
					String weather = in1.nextLine();
					System.out.println("Hva var temperaturen?");
					int temperature = in1.nextInt();
					
				}
				else if(location==1){
					invalid = false;
				}
				else{
					System.out.println("Skriv '0' eller '1'");
					}
			}
			
			
			
			System.out.println("Her er alle øvelsene du kan velge i. [0] for å legge til ny øvelse.\n");
			rs_exercises = acc.getAllExercises();
			while(rs_exercises.next()){
				int exercise_id = rs_exercises.getInt("ovelse_id");
				String exercise_name = rs_exercises.getString("navn");
				System.out.println("[" + exercise_id + "]" + "\t" + exercise_name);
			}
			
			
			//Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			rs_exercises = acc.getExerciseOnID(i);
			while(rs_exercises.next()){
				String s = rs_exercises.getString("navn");
				System.out.println("Du har valgt " + s);
			}
			
			
						
			
		}else{
			ResultSetMetaData metaData = template.getMetaData();
			MySQLAccess acc = new MySQLAccess();
			int count = metaData.getColumnCount(); //number of column
			String columnName[] = new String[count];

			for (int i = 1; i <= count; i++){
			   columnName[i-1] = metaData.getColumnLabel(i);
			   System.out.println(" Skriv inn følgende:" + columnName[i-1]);

			 //  Scanner in = new Scanner(System.in);
			   String result = in.nextLine();
			   
			   jaja.addResult(template,i,result);

			   Scanner in = new Scanner(System.in);
			   String result = in.nextLine();   

			}
				
		}
			
	}
	
}
