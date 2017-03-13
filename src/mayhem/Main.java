package mayhem;

import mayhem.PropertyHandling;
import mayhem.MySQLAccess;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		System.out.println("sdf");
		PropertyHandling jee = new PropertyHandling();
		jee.CreateDatabaseProperies();
		jee.LoadDatabaseProperies();
	}
}
