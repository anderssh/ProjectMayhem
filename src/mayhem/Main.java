package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		boolean done = false;
		while(!done){
			System.out.println("Velg handling:");
			System.out.println("[1] Registrer trening");
			System.out.println("[2] Se tidligere trening");
			System.out.println("[3] Statistikk");
			System.out.println("[4] Notater");
			System.out.println("[5] Se tidligere trening");
			System.out.println("[6] Avslutt");
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			switch(i){
				case 1:
						System.out.println("jjaja");
					
						break;
				case 2: 
						System.out.println("asasas");
						break;
				case 3: 
						break;
				case 4:
						break;
				case 5: 
						break;
				case 6: done = true;
						break;
				default: 
					System.out.println("Skriv inn tall fra 1-6");
					break;
			}
		}
		System.out.print("Takk for idag");
	/*	MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		System.out.println("sdf");
		PropertyHandling jee = new PropertyHandling();
<<<<<<< HEAD
		//jee.CreateDatabaseProperties();
		jee.LoadDatabaseProperies();
		jee.CreateDatabaseProperties();*/
	}
}
