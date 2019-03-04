import java.io.Serializable;

//defines transactions in array from CheckingAccount
//parent of the Check class
public class Transaction implements Serializable{
	private int transNumber; 
	private int transId; 
	protected double transAmt;
	
	public Transaction( int number, int id, double amount) {
		transNumber = number;
		transId = id; //1 ==> check | 2 ==> deposit | 3 ==> service charge | 4 ==> game
		transAmt = amount;
	}
	public int getTransNumber() {  
		return transNumber; 
	}
	public int getTransId() {   //1 ==> check | 2 ==> deposit | 3 ==> service charge | 4 ==> game
		return transId; 
	}
	public double getTransAmount() {
		return transAmt; 
	}
}
