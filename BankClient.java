package Part1;

/**
 * LCOM Value = 1 - (2+2 / 4*2) = 1 - (4/8) = 0.5
 */
public class BankClient {
	
	// Instance Variables
	private short bankClientID;
	private BankAccount[] accounts = new BankAccount[5];
	
	
	// Setters
	public void setBankClientID(short newBankClientID) { this.bankClientID = newBankClientID; }		
	public void setAccount(BankAccount newAccount, int position) { this.accounts[position] = newAccount; }
	
	
	// Getters
	public short getBankClientID() { return this.bankClientID; }		
	public BankAccount[] getAccounts() { return this.accounts; }

}

