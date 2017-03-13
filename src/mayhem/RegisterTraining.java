package mayhem;

import java.util.Scanner;

public class RegisterTraining {
	
	public void startRegister () {
	
	System.out.println("Nå skal du registrere en ny trening");
	System.out.println("Ønsker du å benytte en mal? [J/N]");
	
	Scanner in = new Scanner(System.in);
	
	boolean temp = true;
	while(temp) {
			String i = in.nextLine();
			System.out.println(i);
		
			if (i.toLowerCase().equals("j")) {
				
				System.out.println("JAAAAA");
				temp = false;
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
	public void registerWithoutTemplate () {
		
	}
	
}
