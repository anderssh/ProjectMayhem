package mayhem;
import java.sql.*;
import java.util.Scanner;
import mayhem.MySQLAccess;

public class Statistics {
	
	public void statsMenu() throws Exception{
		System.out.println("Velg hva slags statistikk du vil se");
		System.out.println("[1] Beste form/prestasjon for en dag");
		System.out.println("[2] Månedlig statistikk");
		System.out.println("[3] Årlig statistikk");
		System.out.println("Velg hva slags statistikk du vil se");
		Scanner in_stats = new Scanner(System.in);
		int chosen_stat = in_stats.nextInt();
		switch(chosen_stat){
			case 1:
				viewStats();
				break;
			case 2: 
				viewMonthlyStats();
				break;
			case 3:
				viewYearlyStats();
				break;
			default: 
				System.out.println("Skriv inn tall fra 1-3");
		}
	}

	public void viewStats() throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();

		System.out.println("Se hvilke treninger du hadde best form/prestasjon? ['f' eller 'p']");
		Scanner in = new Scanner(System.in);
		
		boolean invalid = true;
		while(invalid){
			String i = in.nextLine();
			if (i.toLowerCase().equals("f")) {
				ResultSet rs_stats = acc.getMaxForm();
				invalid = false;
				
				while (rs_stats.next()) {
		            int trening_ID = rs_stats.getInt("Trening_ID");
		            String personlig_form = rs_stats.getString("personlig_form");
		            String prestasjon = rs_stats.getString("prestasjon");
		            System.out.println("TreningsID" + "\t" + "Personlig form" + "\t" + "prestasjon");
					System.out.println("-------------------------------------------");
					System.out.println("   [" + trening_ID + "]" + "\t\t\t" + personlig_form + "           " + prestasjon);
				}
			}
			else if(i.toLowerCase().equals("p")) {
				ResultSet rs_stats = acc.getMaxPerformance();
				invalid = false;
				
				while (rs_stats.next()) {
		            int trening_ID = rs_stats.getInt("Trening_ID");
		            String personlig_form = rs_stats.getString("personlig_form");
		            String prestasjon = rs_stats.getString("prestasjon");
		            System.out.println("TreningsID" + "\t" + "Personlig form" + "\t" + "prestasjon");
					System.out.println("-------------------------------------------");
					System.out.println("   [" + trening_ID + "]" + "\t\t\t" + personlig_form + "           " + prestasjon);
				}
			}
			else {
				System.out.println("Skriv 'form' eller 'prestasjon' ");
			}
		}
	}
	public void viewMonthlyStats() throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		ResultSet rs_exercises = acc.getAllExercises();
		System.out.println("Her ser du alle treningene dine i kronologisk rekkefølge");
		
		System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Varighet");
		System.out.println("-------------------------------------------");
		ResultSet rs_workouts = acc.getAllWorkoutsAndDates();
	    while (rs_workouts.next()) {
            int id = rs_workouts.getInt("Trening_ID");
            String date = rs_workouts.getString("dato");
            String duration = rs_workouts.getString("varighet");
            System.out.println("[" + id + "]" + "\t" + date +  "\t " + duration);
	    }
	    
	    System.out.println("Hvilket år vil du se statistikk fra? [YYYY]");
	    Scanner in_date = new Scanner(System.in);
		String chosen_year = in_date.nextLine();
		System.out.println("Her er alle treningene for " + chosen_year);
		ResultSet rs_workouts_by_year = acc.getWorkoutsOnYear(chosen_year);
		System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Varighet");
		System.out.println("-------------------------------------------");
		while (rs_workouts_by_year.next()) {
	            int id = rs_workouts_by_year.getInt("Trening_ID");
	            String date = rs_workouts_by_year.getString("dato");
	            String duration = rs_workouts_by_year.getString("varighet");
	            System.out.println("[" + id + "]" + "\t" + date +  "\t" + duration);
		}
		 
		System.out.println("Hvilken måned vil du se statistikk fra? [MM]");
	    //Scanner in_date = new Scanner(System.in);
		String chosen_month = in_date.nextLine();
		System.out.println("Her er alle treningene for måned " + chosen_month + " i år " + chosen_year);
		ResultSet rs_workouts_by_month = acc.getWorkoutsOnMonth(chosen_year,chosen_month);
		System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Varighet");
		System.out.println("-------------------------------------------");
		int number_of_workouts = 0;
		int total_hours = 0;
		int total_minutes = 0;
		int total_seconds = 0;
		String total_workout_time = "";
		while (rs_workouts_by_month.next()) {
				number_of_workouts++;
	            int id = rs_workouts_by_month.getInt("Trening_ID");
	            String date = rs_workouts_by_month.getString("dato");
	            total_hours += rs_workouts_by_month.getInt("hour");
	            total_minutes += rs_workouts_by_month.getInt("minute");
	            total_seconds += rs_workouts_by_month.getInt("second");
	            total_workout_time = total_workout_time + rs_workouts_by_month.getString("Varighet");
	            System.out.println("[" + id + "]" + "\t" + date +  "\t " + total_hours);
		}
		total_minutes += secondsToMinutes(total_seconds);
		total_seconds -=secondsToMinutes(total_seconds)*60;
		total_hours += minutesToHours(total_minutes);
		total_minutes -= minutesToHours(total_hours)*60;
		System.out.println("Du trente " + number_of_workouts + " ganger i måned " + chosen_month + " i år " + chosen_year);
		System.out.println("Du trente " + total_hours + " timer, " + total_minutes + " minutter, " + total_seconds + " sekunder i måned " + chosen_month + " i år " + chosen_year);
	}
	
	public void viewYearlyStats() throws Exception{
		MySQLAccess acc = new MySQLAccess();
		acc.makeConnection();
		ResultSet rs_exercises = acc.getAllExercises();
		System.out.println("Her ser du alle treningene dine i kronologisk rekkefølge");
		
		System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Varighet");
		System.out.println("-------------------------------------------");
		ResultSet rs_workouts = acc.getAllWorkoutsAndDates();
	    while (rs_workouts.next()) {
            int id = rs_workouts.getInt("Trening_ID");
            String date = rs_workouts.getString("dato");
            String duration = rs_workouts.getString("varighet");
            System.out.println("[" + id + "]" + "\t" + date +  "\t " + duration);
	    }
	    
	    System.out.println("Hvilket år vil du se statistikk fra? [YYYY]");
	    Scanner in_date = new Scanner(System.in);
		String chosen_year = in_date.nextLine();
		System.out.println("Her er alle treningene for " + chosen_year);
		ResultSet rs_workouts_by_year = acc.getWorkoutsOnYear(chosen_year);
		System.out.println("Id" + "\t" + "Dato" + "\t" +  "\t" + "Varighet");
		System.out.println("-------------------------------------------");
		int number_of_workouts = 0;
		int total_hours = 0;
		int total_minutes = 0;
		int total_seconds = 0;
		String total_workout_time = "";
		while (rs_workouts_by_year.next()) {
				number_of_workouts++;
	            int id = rs_workouts_by_year.getInt("Trening_ID");
	            String date = rs_workouts_by_year.getString("dato");
	            total_hours += rs_workouts_by_year.getInt("hour");
	            total_minutes += rs_workouts_by_year.getInt("minute");
	            total_seconds += rs_workouts_by_year.getInt("second");
	            total_workout_time = total_workout_time + rs_workouts_by_year.getString("Varighet");
	            System.out.println("[" + id + "]" + "\t" + date +  "\t " + total_hours);
		}
		total_minutes += secondsToMinutes(total_seconds);
		total_seconds -=secondsToMinutes(total_seconds)*60;
		total_hours += minutesToHours(total_minutes);
		total_minutes -= minutesToHours(total_hours)*60;
		System.out.println("Du trente " + number_of_workouts + " i år " + chosen_year);
		System.out.println("Du trente " + total_hours + " timer, " + total_minutes + " minutter, " + total_seconds + " sekunder i år " + chosen_year);
	}
	
	public int minutesToHours(int minutes){
		return minutes/60;
	}
	
	public int secondsToMinutes(int seconds){
		return seconds/60;
	}
}
