package mayhem;

import java.sql.*;
import java.util.Scanner;
import mayhem.MySQLAccess;

public class RegisterTraining {
	
	public void startRegister () throws Exception {
	
	System.out.println("Nå skal du registrere en ny trening");
	System.out.println("ønsker du å benytte en mal? [J/N]");
	
	Scanner in = new Scanner(System.in);
	
	boolean invalid = true;
	while(invalid) {
			String i = in.nextLine();
			System.out.println(i);
		
			if (i.toLowerCase().equals("j")) {
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
	            	//System.out.println("\t" + "-" + exer );
	            }else{
	            	//System.out.println("");
	            	System.out.println("[" + id + "]" + "\t" + date +  "\t" + sport);
	            	//System.out.println("\t" + "-" + exer );
	            }
	            idprev = id;
	        }
		    System.out.println("");
		    System.out.println("Skriv inn en [id] for ï¿½ utforske en spesifikk trening:");
		    Scanner in = new Scanner(System.in);
			int id = in.nextInt();
			ResultSet rs_workoutOnID = acc.getWorkoutOnID(id);
			System.out.println("Id" + "\t" + "Inne/Ute" + "\t"+ "Idrett" + "\t" + "\t" + "Øvelse" + "\t" + "Antall sett"+ "\t" + "Antall reps"+ "\t" + "Belastning(kg)");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		    int idprev1 = 0;
			while (rs_workoutOnID.next()) {
		    	String  outin	= rs_workoutOnID.getString("inneUte");
	            String sport	= rs_workoutOnID.getString("idrett");
	            String  exer	= rs_workoutOnID.getString("ovelse");
	            int sets  	= rs_workoutOnID.getInt("antall_set");
	            int reps 	= rs_workoutOnID.getInt("antall_repetisjoner");
	            int load 	= rs_workoutOnID.getInt("belastning_kg");
	            
	            if (id == idprev1){
	            	System.out.println("\t"  + "\t" + "\t"  + "\t" + "\t" + exer + "\t" + sets +"\t"+  reps +"\t"+ load);
	            }else{
	            	System.out.println("[" + id + "]" + "\t" + outin + "\t" + sport + "\t" + exer + "\t" + sets +"\t"+ reps+"\t"+ load );
	            }
	            idprev1 = id;
	            
				
		    }
			
			rs_workoutOnID.beforeFirst();
		    
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
			ResultSet rs_workout_ID = null;
			int workout_ID = 0;
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
			int performance = workout_info.nextInt();
			
			System.out.println("Hvordan følte du deg? [1-10]");
			int form = workout_info.nextInt();
			
			System.out.println("Bedrev du en av disse idrettene? Tast [0] for å legge til ny idrett.");
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
				System.out.println("Skriv navn på ny idrett");
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
					System.out.println("Hvordan var været?");
					weather = in1.nextLine();
					System.out.println("Hva var temperaturen?");
					temperature = in1.nextInt();
					rs_workout_ID = acc.addOutsideWorkout(date, time, duration, performance, form, sport_ID, note, weather, temperature);
					//rs_workout_ID.next();
					workout_ID = (int) rs_workout_ID.getLong(1);
				}
				else if(location==1)
				{
					Scanner in2 = new Scanner(System.in);
					invalid = false;
					System.out.println("Hvordan var ventilasjonen?");
					ventilation = in2.nextLine();
					System.out.println("Antall tilskuere?");
					crowd = in2.nextInt();
					rs_workout_ID = acc.addInsideWorkout(date, time, duration, performance, form, sport_ID, note, ventilation, crowd);
					//rs_workout_ID.next();
					workout_ID = (int) rs_workout_ID.getLong(1);
				}
				else{
					System.out.println("Skriv '0' eller '1'");
					}
			}
			
			boolean addExercise = true;
			while (addExercise){
				System.out.println("Her er alle øvelsene du kan velge i. [0] for å legge til en øvelse som ikke finnes.\n");
				rs_exercises = acc.getAllExercises();
				while(rs_exercises.next()){
					int exercise_id = rs_exercises.getInt("ovelse_ID");
					String exercise_name = rs_exercises.getString("navn");
					System.out.println("[" + exercise_id + "]" + "\t" + exercise_name);
				}
				Scanner exercise = new Scanner(System.in);
				int i = exercise.nextInt();
				if(i == 0){
					registerNewExercise(workout_ID);
				}
				else if(i > 0){
					chooseExistingExercise(workout_ID,i);
				}
				System.out.println("[1] for å legge til flere ï¿½velser.");
				System.out.println("[2] Ferdig.");
				int ex = exercise.nextInt();
				if(ex == 2){
					addExercise = false;
				}
			}
				
		}
			
		else{
			ResultSet rs_workout_ID = null;
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
			int performance = workout_info.nextInt();
			
			System.out.println("Hvordan fï¿½lte du deg? [1-10]");
			int form = workout_info.nextInt();
			
			System.out.println("Hvis du vil, legg til et notat.");
			Scanner more = new Scanner(System.in);
			String note = more.nextLine();
			
			template.next();
			String outin  = template.getString("inneUte");
			String exer  = template.getString("ovelse");
			int sport_ID = template.getInt("idrett_ID");
			int exercise_ID = template.getInt("ovelse_ID");
			int workout_ID = template.getInt("trening_ID");
			template.beforeFirst();
			
			ResultSet rs_workout_id = null;
			int workout_id_int = 0;
			
			if (outin.toLowerCase().equals("innetrening")){
				System.out.println("Hvordan var ventilasjonen?");
				String ventilation = more.nextLine();
				System.out.println("Antall tilskuere?");
				int crowd = more.nextInt();
				rs_workout_id = acc.addInsideWorkout(date,time,duration,performance,form,sport_ID,note,ventilation,crowd);
				workout_id_int = (int)rs_workout_id.getLong(1);
				
			}else if (outin.toLowerCase().equals("utetrening")){
				System.out.println("Hvordan var været?");
				String weatherType = more.nextLine();
				System.out.println("Hvordan var temperaturen?");
				int temperature = more.nextInt();
				
				rs_workout_id = acc.addOutsideWorkout(date,time,duration,performance,form,sport_ID,note,weatherType,temperature);
				workout_id_int = (int)rs_workout_id.getLong(1);
			}else{
				System.out.println("Noe gikk galt!!");
			}
			
			while(template.next()){
				exercise_ID = template.getInt("ovelse_ID");
				exer = template.getString("ovelse");
				System.out.println("Skriv inn informasjon om følgende øvelse: " + exer);
				chooseExistingExercise(workout_id_int,exercise_ID);
			}
		}
}
	
	public void chooseExistingExercise(int workout_ID,int exercise_ID) throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		Scanner in_exercise = new Scanner(System.in);
		System.out.println("Belastning [kg]: ");
		int load = in_exercise.nextInt();
		System.out.println("Antall set: ");
		int set = in_exercise.nextInt();
		System.out.println("Antall reps: ");
		int rep = in_exercise.nextInt();
		System.out.println("Hvor lenge varte øvelsen? [HH:MM] ");
		Scanner in_duration = new Scanner(System.in);
		String duration = in_duration.nextLine();
		ResultSet rs_exercise_details = acc.addExerciseDetails(load,set,rep,duration,exercise_ID);
		rs_exercise_details.next();
		int exercise_details_ID = (int) rs_exercise_details.getLong(1);
		if(workout_ID != -1){
			acc.addWorkoutExerciseDetails(workout_ID, exercise_details_ID);
		}
	}
	public int registerNewExercise(int workout_ID) throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		System.out.println("Hva heter ï¿½velsen?");
		Scanner in_exercise = new Scanner(System.in);
		String new_exercise_name = in_exercise.nextLine();
		System.out.println("Gi en kort beskrivelse av ï¿½velsen.");
		String new_exercise_description = in_exercise.nextLine();
		System.out.println("Hviken type ï¿½velse er det?");
		ResultSet rs_ex_type = acc.getWorkoutType();
		while(rs_ex_type.next()){
			int type_ID = rs_ex_type.getInt("treningstype_ID");
			String type_name = rs_ex_type.getString("navn");
			System.out.println("[" + type_ID + "]" + "\t" + type_name);
		}
		int new_exercise_type_ID = in_exercise.nextInt();
		ResultSet rs_ex = acc.addExercise(new_exercise_name, new_exercise_description, new_exercise_type_ID);
		rs_ex.next();
    	int new_exercise_ID= (int) rs_ex.getLong(1);
		System.out.println("Belastning [kg]: ");
		int load = in_exercise.nextInt();
		System.out.println("Antall set: ");
		int set = in_exercise.nextInt();
		System.out.println("Antall reps: ");
		int rep = in_exercise.nextInt();
		System.out.println("Hvor lenge varte øvelsen? [HH:MM] ");
		Scanner in_duration = new Scanner(System.in);
		String duration = in_duration.nextLine();
		ResultSet rs_exercise_details = acc.addExerciseDetails(load,set,rep,duration,new_exercise_ID);
		rs_exercise_details.next();
		int exercise_details_ID = (int) rs_exercise_details.getLong(1);
		
		if(workout_ID != -1){
			acc.addWorkoutExerciseDetails(workout_ID, exercise_details_ID);
			System.out.println(new_exercise_name + " er registrert i treningen din.");
		}
			return new_exercise_ID;
	}
}


