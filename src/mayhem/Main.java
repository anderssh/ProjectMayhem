package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Velg handling:");
		System.out.println("Registrer trening [1]");
		System.out.println("Se tidligere trening [2]");
		System.out.println("Statistikk [3]");
		System.out.println("Notater [4]");
		System.out.println("Se tidligere trening [5]");
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		switch(i){
			case 1:
					System.out.println("jjaja");
					break;
			case 2: System.out.println("asasas");
					break;
			case 3: 
		}
		
	/*	MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		System.out.println("sdf");
		PropertyHandling jee = new PropertyHandling();
		//jee.CreateDatabaseProperties();
		jee.LoadDatabaseProperies();*/
	}
}
