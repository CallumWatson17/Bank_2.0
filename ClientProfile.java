package Part1;

/**
* LCOM Value = 1 - (2+2+2 / 6*3) = 1 - (6/18) = 0.66
*/
public class ClientProfile {
	// Instance Variables
	private String fullName;
	private String address;
	private byte age;
	

	// setters
	public void setFullName(String newFirstName, String newLastName) { this.fullName = newFirstName + " " + newLastName; }
	public void setAddress(String newAddress) { this.address = newAddress; }		
	public void setAge(byte newAge) { this.age = newAge; }		

	
	// Getters
	public String getFullName() { return this.fullName; }		
	public String getAddress() { return this.address; }	
	public byte getAge() { return this.age; }		

}
