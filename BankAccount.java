package Part1;

/**
 * LCOM Value = 1 - (2+2 / 5*2) = 1 - (4/10) = 0.6
 *
 */
public class BankAccount {

	// Instance Variables
	private byte bankAccountID;
	private double balance;
	
	// Setters
	public void setBankAccountID(byte newBankAccountID) { this.bankAccountID = newBankAccountID; }		
	public void setBalance(double newBalance) { this.balance = newBalance; }
	
	
	// Getters
	public byte getBankAccountID() { return this.bankAccountID; }		
	public double getBalance() { return this.balance; }
	
	
	// additional methods
	public String getDetails() {
		String res = getBankAccountID() + " " + getBalance();
		return res;
	}

}