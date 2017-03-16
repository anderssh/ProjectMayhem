package mayhem;

import java.sql.*;
import java.util.Scanner;
import mayhem.MySQLAccess;
import mayhem.RegisterTraining;

public class Goals {
	
	public void updateGoal() throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		
		RegisterTraining reg = new RegisterTraining();
		
		System.out.println("Vil du oppdatere mål? ('ja'/'nei'");
		Scanner in_exercise = new Scanner(System.in);
		boolean invalid = true;
		while(invalid){
			String i = in_exercise.nextLine();
			if (i.toLowerCase().equals("ja")) {
				int workout_ID;
				ResultSet rs_exercises = null;
				int exercise_details_ID = 0;
				boolean addExercise = true;
				while (addExercise){
					System.out.println("Her er alle �velsene du kan velge i. [0] for � legge til en �velse som ikke finnes.\n");
					rs_exercises = acc.getAllExercises();
					while(rs_exercises.next()){
						int exercise_id = rs_exercises.getInt("ovelse_ID");
						String exercise_name = rs_exercises.getString("navn");
						System.out.println("[" + exercise_id + "]" + "\t" + exercise_name);
					}
					Scanner exercise = new Scanner(System.in);
					int j = exercise.nextInt();
					if(j == 0){
						exercise_details_ID = reg.registerNewExercise(-1);
						
					}
					else if(j > 0){
						reg.chooseExistingExercise(-1,j);
					}
						addExercise = false;
				}

				System.out.println("Har du oppnådd målene?");
				String achieved = in_exercise.nextLine();
				
				System.out.println("Legg til notat: ");
				String note = in_exercise.nextLine();
				
				acc.addGoal(exercise_details_ID, achieved, note);
				System.out.println("hadebraaa");
				invalid = false;
			}
			else if (i.toLowerCase().equals("nei")){
				invalid = false;
			}
		}
	}
}