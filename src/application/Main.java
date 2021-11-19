package application;

import java.util.Scanner;

public class Main {

	private static Scanner kb = new Scanner(System.in);
	
	private static void printLineSeparator() {
		System.out.println("==================================================");
	}
	
	public static void main(String args[]) {
		
		System.out.println("===== Ajarn Run ! =====");
		while (true) {
			try {
				System.out.println("===== MAIN MENU =====");
				System.out.println("0) PLAY GAME");
				System.out.println("1) Stat");
				System.out.println("2) ....");
				System.out.println("3) Exit");

				String choice = kb.nextLine();
				int choiceNumber = Integer.parseInt(choice);

				System.out.println("====================");
				switch (choiceNumber) {
				case 0:
					
					Thread.sleep(500);
					System.out.println("  .. Tutorials ..");
					
					Thread.sleep(1000);
					System.out.println("Ajarn run! start in ..");
					System.out.println(" .. 3 .. ");
					Thread.sleep(1000);
					System.out.println(" .. 2 .. ");
					Thread.sleep(1000);
					System.out.println(" .. 1 .. ");
					Thread.sleep(800);
					
					System.out.println(" LET'S GOO ");
					System.out.println("  ////// Level " + "1");
					
				case 1:
					
					String newName = kb.nextLine();
					
					System.out.println("====================");
					break;
				case 2:
					
					System.out.println("====================");
					break;
				case 3:
					
					System.out.println("====================");
					break;
					
				default:
					System.out.println("Not a valid command. Please input again.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error detected! Returning to main menu.");
			}
		}
	}
	
	//public int

}
