package Part1;

/**
 *  * LCOM Value = 1 - (3+2+2 / 6*3) = 1 - (7/18) = 0.61
 */
import java.util.Scanner;

public class Menu {
	private String[] items;
	private String title;
	private Scanner input;

	// setters
	public void setTitle(String newTitle) { this.title = newTitle; }
	public void setItems(String[] newItems) { this.items = newItems; }
	public void setInput() { this.input = new Scanner(System.in); }

	
	private void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		
		System.out.println();
		
		int option = 1;
		for (char ch = 'A'; option <= items.length; ch++) {
		    System.out.println(ch + ". " + items[option - 1]);
		    option++;
		}
		
		System.out.println();
	}

	
	public String getUserChoice() {
		if ( items == null || items.length == 0 ) {
			return null;
		}
		
		String res = "";
		display();
		char length = (char) ('A' + items.length-1);
		
		boolean ok = false;
		do {
			System.out.print("Enter Selection: ");
			try {
				res = input.next();
				
				if ( res.charAt(0) >= 'A' && res.charAt(0) <= length ) {
					ok = true;
				}
				else {
					System.out.println("Enter a value between A and " + length);
				}
			}
			catch(Exception ex) {
				System.out.println("Error input.");
				input.nextLine();
			}
		} while (!ok);

		return res;
	}
	
}
