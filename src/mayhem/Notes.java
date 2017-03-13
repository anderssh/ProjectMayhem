package mayhem;
import java.util.Scanner;
import java.sql.*;

public class Notes {
	public void notesHandler() throws SQLException{
		System.out.println("Dette er alle treninger som inneholder notater");
		//getworkoutswithnotes();
		System.out.println("Velg hva du vil se\n");

		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		ResultSet resultset = null;
		//resultset=getNotes(i);
		while (resultset.next()) {
		       for (int j = 1; j <= 3; j++) {
		           if (j > 1) System.out.print(",  ");
		           String columnValue = resultset.getString(j);
		           System.out.print(columnValue);// + " " + rsmd.getColumnName(i));
		       }
		       System.out.println("");
		   }
	}
}
