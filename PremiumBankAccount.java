package Part1;

/**
 * LCOM Value = 1 - (2+2 / 5*2) = 1 - (4/10) = 0.6
 *
 */
public class PremiumBankAccount extends BankAccount {
	
	// Instance Variables
	private float fee;
	private double cashback;
	
	
	// Setters
	public void setFee(float newFee) { this.fee = newFee; }		
	public void setCashback(double newCashback) { this.cashback = newCashback; }
	
	
	// Getters
	public float getFee() { return this.fee; }	
	public double getCashback() { return this.cashback; }

// additional methods
	@Override
	public String getDetails() {
		String res = super.getDetails();
		res += " " + getFee() + " " + getCashback();
		return res;
	}
	
}
