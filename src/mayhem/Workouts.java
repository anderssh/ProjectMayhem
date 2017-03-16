package mayhem;

import java.sql.ResultSet;
import java.util.Scanner;

public class Workouts {

	public void prevWorkouts () throws Exception {
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		
		ResultSet chosen_template = null;
		boolean found_template = false;
		
		while(found_template == false){
			System.out.println("Her er en liste over dine tidligere treninger:");
			System.out.println("");
			System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Idrett"+ "\t" +  "\t" + "Tid" + "\t" +  "\t" +  "Varighet");
			System.out.println("-----------------------------------------------------------------------------------------------");
			ResultSet rs_workouts = acc.getAllWorkouts();
			int idprev = 0;
		    while (rs_workouts.next()) {
	            int id = rs_workouts.getInt("Trening_ID");
	            String date = rs_workouts.getString("dato");
	            String sport = rs_workouts.getString("idrett");
	            String exer = rs_workouts.getString("ovelse");
	            String time = rs_workouts.getString("tid");
	            String duration = rs_workouts.getString("varighet");
	            if (id == idprev){
	            	//System.out.println("\t" + "-" + exer );
	            }else{
	            	//System.out.println("");
	            	System.out.println("[" + id + "]" + "\t" + date +  "\t" + sport+"\t" + time+"\t" + duration);
	            	//System.out.println("\t" + "-" + exer );
	            }
	            idprev = id;
	        }
		    System.out.println("");
		    System.out.println("Skriv inn en [id] for ï¿½ utforske en spesifikk trening:");
		    Scanner in = new Scanner(System.in);
			int id = in.nextInt();
			ResultSet rs_workoutOnID = acc.getWorkoutOnID(id);
			System.out.println("Id" + "\t" + "Inne/Ute" + "\t"+ "Idrett" + "\t" + "\t" + "Øvelse" + "\t" + "Antall sett"+ "\t" + "Antall reps"+ "\t" + "Belastning(kg)"+ "\t" + "Øvelsevarighet");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		    int idprev1 = 0;
			while (rs_workoutOnID.next()) {
		    	String  outin	= rs_workoutOnID.getString("inneUte");
	            String sport	= rs_workoutOnID.getString("idrett");
	            String  exer	= rs_workoutOnID.getString("ovelse");
	            String  exerTime = rs_workoutOnID.getString("ovelse_varighet");
	            int sets  	= rs_workoutOnID.getInt("antall_set");
	            int reps 	= rs_workoutOnID.getInt("antall_repetisjoner");
	            int load 	= rs_workoutOnID.getInt("belastning_kg");
	            
	            if (id == idprev1){
	            	System.out.println("\t"  + "\t" + "\t"  + "\t" + "\t" + exer + "\t" + sets +"\t"+  reps +"\t"+ load+"\t"+ exerTime);
	            }else{
	            	System.out.println("[" + id + "]" + "\t" + outin + "\t" + sport + "\t" + exer + "\t" + sets +"\t"+ reps+"\t"+ load+"\t"+ exerTime );
	            }
	            idprev1 = id;
	            
				
		    }
			
			rs_workoutOnID.beforeFirst();
		    
		    System.out.println("");
		    System.out.println("Tast [0] for å gå tilbake");
		    Scanner in1 = new Scanner(System.in);
		    boolean temp = true;
		    while(temp) {
				String i = in1.nextLine();
				System.out.println(i);
			
				if (i.toLowerCase().equals("0")) {
					temp = false;

				}
				else {
					System.out.println("Tast [0] for å gå tilbake! ");
				}
			}
		    
		}		
	}	
	
	
	
	
	
}
