package mayhem;
import java.sql.*;
import java.util.Scanner;
import mayhem.MySQLAccess;

public class Statistics {

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

}
