import java.io.Serializable;

//parent to checking account class
public class Account implements Serializable{
	protected String name; // The person who owns the account
	protected double balance;// do not define this in CheckingAccount class

	public Account(String acctName, double initBalance){ 
		balance = initBalance;
		name = acctName; 
	}
	
	public String getName() { 
		return name;
	}
	
	public void setName(String name) { 
		this.name = name;
	}
	
	public double getBal() { 
		return balance;
	}
	
	public void setBal(double balance) { 
		this.balance = balance;
	} 
}
