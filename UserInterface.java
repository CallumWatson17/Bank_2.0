package Part1;

/*

 * LCOM Value = 1 -
 * (6+4+6 / 12*4) = 1 - (16/48) = 0.66
 */
import java.util.Scanner;

public class UserInterface {

	private final int MAX_CLIENTS = 1000;
	BankClient[] clients = new BankClient[MAX_CLIENTS];
	private final byte MAX_ACCOUNTS = 5;
	BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];
	private Scanner in = new Scanner(System.in);
	private ClientProfile profile;

	public static void main(String[] args) {
		UserInterface UserInterface = new UserInterface();

		// Use-case 1 - Batch Registration
		System.out.println("Use Case 1 : Batch Registration");
		for (short ID = 0; ID <= 5; ID++) {
			UserInterface.batchRegistration(ID);
		}
		for (short index = 0; index <= 5; index++) {
			System.out
					.println("Registration of bank client with ID = " + UserInterface.clients[index].getBankClientID());
		}
		System.out.println();

		// Use-case 2 - Present Management Options
		UserInterface.managementOptions();

	}

	// Use Case 1
	public void batchRegistration(short ID) {

		BankClient client = new BankClient(); // create a bank-client object
		client.setBankClientID(ID);
		clients[ID] = client; // insertion of object into array

	}

	// Use-case 2
	public void managementOptions() {

		// Ask for ID
		System.out.println("Please Enter ID:");
		short bankClientID = in.nextShort();
		in.nextLine();

		System.out.println("Check ID");
		if (clients[bankClientID] == null) {
			System.out.println("Invalid ID");
			return;
		}

		// Check for ID
		String title = "Management Options";
		String options[] = { "Create Profile", "Update Profile", "Create bank account", "Delete Bank Account",
				"Money Transfer", "Print Profile", "Print Bank Account", "Exit" };

		// Menu myMenu = new Menu(title, options);
		Menu myMenu = new Menu();

		myMenu.setTitle(title);
		myMenu.setItems(options);
		myMenu.setInput();

		String choice = "";

		do {
			choice = myMenu.getUserChoice();
			processChoice(choice, bankClientID);

		} while (!choice.contentEquals("H"));

		System.out.println("You have exited the system");
		
	}

	public void createProfile() {
		
		ClientProfile profile = new ClientProfile();

		String FirstName, LastName, address;
		byte age;

		if (profile != null) {

			// Ask for required information - extend
			System.out.println("Enter Full Name"); // include
			System.out.println("***************"); // include

			System.out.println("Enter First Name: "); // include

			System.out.print("\n> ");
			FirstName = in.nextLine();

			if (FirstName.isEmpty()) {
				System.out.println("Error input - could not add first name\n");
				return;
			}

			System.out.println("\n" + "Enter Last Name: "); // include

			System.out.print("\n> ");
			LastName = in.nextLine();

			if (LastName.isEmpty()) {
				System.out.println("Error input - could not add last name\n");
				return;
			}

			profile.setFullName(FirstName, LastName);

			System.out.print("\n" + "Enter Address: ");
			System.out.print("\n> ");
			address = in.nextLine();

			if (address.isEmpty()) {
				System.out.println("Error input - could not add address\n");
				return;
			}

			profile.setAddress(address);

			System.out.print("\n" + "Enter Age: ");
			System.out.print("\n> ");
			age = in.nextByte();

			in.nextLine();

			if (age < 18) {
				System.out.println("Error input - invalid age\n");
				return;
			}

			profile.setAge(age);

			System.out.println("\n" + "Bank Profile Successfully Created"); // confirmation message - extend

		}

	}

	// Use-case 4
	public void updateProfile() {
		System.out.println("\nUse Case 4 : Update the profile of a bank client");

		String title = "Update Options";
		String[] options = { "Update Address", "Update Age", "Update Address & Age" };

		// Menu menu = new Menu(title, options);
		Menu menu = new Menu();

		menu.setTitle(title);
		menu.setItems(options);
		menu.setInput();

		String choice = menu.getUserChoice();

		processProfileUpdate(choice);

		System.out.println("Profile Successfully Updated\n");
	}

	/**
	 * Use-case 5 - Creating a Bank Account checks what type of bank account the
	 * user wants to create adds the bank account object to the array of bank
	 * accounts balance should always be zero for new bank accounts prints error
	 * message if missing/invalid information prints confirmation message if
	 * successful
	 */
	public void createBankAccount() {
		byte counter = 0;
		BankAccount bankAccount = null;
		System.out.println("Use Case 5. Create a bank account");
		if (accounts[4] == null) { // counter counts number of bank accounts as max is 5
			System.out.println("Ask for account type: A. Basic; B. Premium");
			String accountType = in.next();
			for (byte i = 0; i < accounts.length; i++) {
				if (accounts[i] == null) {
					counter = i;
					break;
				}
			}
			if (accountType.equals("A")) {
				bankAccount = new BasicBankAccount();
				System.out.println("New Basic Bank Account created!");
				bankAccount.setBankAccountID(counter);
				accounts[counter] = bankAccount;
				counter++; // increments counter
			} else if (accountType.equals("B")) {
				bankAccount = new PremiumBankAccount();
				System.out.println("New Premium Bank Account created!");
				bankAccount.setBankAccountID(counter);
				accounts[counter] = bankAccount;
				counter++;
			} else {
				System.out.println("Error"); // error message
			}
		} else {
			System.out.println("Max Bank Accounts for this Client created."); // confirmation message
		}

	}

	/**
	 * Use-case 6 - Deleting a Bank Account asks for bank account ID then checks
	 * that ID in the system deletes that bank account in the accounts array prints
	 * error message if missing/invalid information prints confirmation message if
	 * successful
	 */
	public void deleteBankAccount() {
		System.out.println("Use Case 6. Delete a bank account");
		System.out.println("Enter Bank Account ID you wish to delete:");
		byte accountID = in.nextByte();
		if (accountID >= 0 && accountID < accounts.length && accounts[accountID] != null) { // checks id is valid
			accounts[accountID] = null;
			byte swaps;
			do { // do while to swap active bank accounts to the front of the array
				swaps = 0;
				for (int i = 0; i < accounts.length - 1; i++) {
					if (accounts[i] == null && accounts[i + 1] != null) {
						BankAccount temp = accounts[i];
						accounts[i] = accounts[i + 1];
						accounts[i + 1] = temp;
						swaps++;
					}
				}
			} while (swaps > 0); // if swaps are 0 no more are needed
			System.out.println("Bank Account Deleted!"); // confirmation message
		} else {
			System.out.println("Error - Bank Account does not exist"); // error message
		}
	}

	/**
	 * Use-case 7 - Transfer Money asks for required information then checks
	 * information prints error message is missing/invalid information prints error
	 * message if cash amount is unavailable prints confirmation message if
	 * successful and alters bank balances
	 */
	public void transferMoney() {
		System.out.println("Use Case 7. Money Transfer");
		// ask for information
		System.out.println("Ask for the ID of the source Bank Account \n>"); // include
		byte sourceAccountID = in.nextByte();
		System.out.println("Ask for the ID of the target Bank Account \n>"); // include
		byte targetAccountID = in.nextByte();
		System.out.println("Ask for the ID of the target Bank Client\n>"); // include
		short targetClientID = in.nextShort();
		System.out.println("Ask for the cash amount\n>"); // include
		double cashAmount = in.nextDouble();

		// check information
		System.out.println("Check the ID of the bank client of target bank account");
		System.out.println("Check the ID of the source bank account");
		System.out.println("Check the ID of the target bank account");
		System.out.println("Check the cash amount");
		System.out.println("Check whether the transfer can be made");
		System.out.println("Print out sucessful transfer or error");
		if (accounts[sourceAccountID] != null && accounts[targetAccountID] != null && clients[targetClientID] != null) { // validates
																															// info
			if (cashAmount <= accounts[sourceAccountID].getBalance()) { // checks cash amount is valid
				accounts[sourceAccountID].setBalance(accounts[sourceAccountID].getBalance() - cashAmount); // subtracts
																											// cash
																											// amount
				accounts[targetAccountID].setBalance(accounts[targetAccountID].getBalance() + cashAmount); // adds cash
																											// amount
				System.out.println("Transfer of " + String.format("£%.2f", cashAmount) + "successful!"); // confirmation
																											// message -
																											// extend
			} else {
				System.out.println("Cash Amount Error"); // error message - extend
			}
		} else {
			System.out.println("Information Error"); // error message - extend
		}
	}

	// Use Case 8
	public void printProfile() {
		System.out.println("\nUse Case 8 : Printing the profile of a bank client");

		System.out.println("Profile Details");
		System.out.println("+++++++++++++++");

		String name = profile.getFullName();
		String address = profile.getAddress();
		byte age = profile.getAge();

		System.out.println(name + " " + address + " " + age + "\n");

	}

	// Use Case 9
	public void printAccounts(short ID) {
		System.out.println("\nUse Case 9 : Printing the bank accounts of a bank client");

		BankAccount[] myAccounts = clients[ID].getAccounts();

		System.out.println("Account Details");
		System.out.println("+++++++++++++++");

		// find number of non-null accounts
		int numAccts = 0;
		for (BankAccount ba : myAccounts) {
			if (ba != null) {
				numAccts++;
			}
		}

		// print account details
		int count = 0;
		for (int i = 0; i < myAccounts.length; i++) {
			if (myAccounts[i] == null && myAccounts[i + 1] != null) {
				i++;
			}
			if (myAccounts[i] == null && count == numAccts) {
				break;
			}

			if (myAccounts[i] instanceof PremiumBankAccount) {
				PremiumBankAccount premAccount = (PremiumBankAccount) myAccounts[i];
				count++;
				System.out.println(premAccount.getDetails());

			} else {
				count++;
				System.out.println(myAccounts[i].getDetails());
			}
		}

		System.out.println();

	}

	// additional methods
	public void processChoice(String choice, short ID) {
		switch (choice) {
		case "A":
			createProfile();
			return;

		case "B":
			updateProfile();
			return;

		case "C":
			createBankAccount();
			return;

		case "D":
			deleteBankAccount();
			return;

		case "E":
			transferMoney();
			return;

		case "F":
			printProfile();
			return;

		case "G":
			printAccounts(ID);
			return;

		}

	}

	public void processProfileUpdate(String choice) {

		String address;
		byte age;

		if (choice.equals("A")) {
			System.out.print("Enter updated address");
			System.out.print("\n> ");
			address = in.nextLine();

			System.out.println("Check updated address");
			if (address.isBlank()) {
				System.out.println("Error input - could not update address\n");
				return;
			}

			profile.setAddress(address);

		} else if (choice.equals("B")) {
			System.out.print("Enter updated age");
			System.out.print("\n> ");
			age = in.nextByte();

			in.nextLine();

			System.out.println("Check updated age");
			if (age < 18) {
				System.out.println("Error input - invalid age\n");
				return;
			}

			profile.setAge(age);

		} else if (choice.equals("C")) {
			System.out.print("Enter updated address");
			System.out.print("\n> ");
			address = in.nextLine();

			System.out.print("Enter updated age");
			System.out.print("\n> ");
			age = in.nextByte();

			in.nextLine();

			System.out.println("Check updated address");
			if (address.isBlank()) {
				System.out.println("Error input - could not update address\n");
				return;
			}

			System.out.println("Check updated age");
			if (age < 18) {
				System.out.println("Error input - invalid age\n");
				return;
			}

			// update profile
			profile.setAddress(address);
			profile.setAge(age);

		}

	}

}
