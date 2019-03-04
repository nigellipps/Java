import java.util.ArrayList;

public class CheckingAccount extends Account {	
 private double balance = 0;
 private double totalServ;
 private double code1 = 0.15;
 private double code2 = 0.10;
 private ArrayList<Transaction> AL = new ArrayList<Transaction>();  // keeps a list of Transaction objects for the account
 private int transCount; // the count of Transaction objects and used as the transNumber for each transaction
 
 public CheckingAccount(String name, double initialBalance) {		
	 	super (name, initialBalance);
	 	balance = initialBalance;
		totalServ = 0;												
		transCount = 0;
		
	}	
 
 public int getNumofAccounts() {
	 return AL.size();
 }

 public void addTrans(Transaction newTrans) {  // adds a transaction object to the transList
	AL.add(newTrans);
	transCount++;
 }
 public int getTransCount() { //returns the current value of transCount;
	return transCount;
 }
 public Transaction getTrans(int i) { // returns the i-th Transaction object in the list
	return AL.get(i);
 }

 public double getBalance(){
		return balance;
	}
 public void setBalance(double transAmt, int tCode){
		if(tCode == 1) {
			setServiceCharge(code1);
			Transaction trans = new Transaction (getTransCount(), 3, code1);
			addTrans(trans);
			balance = balance - transAmt;
		}
		else { 
			setServiceCharge(code2);
			Transaction trans = new Transaction (getTransCount(), 3, code2);
			addTrans(trans);
			balance = balance + transAmt;
		}
	}
 public double getServiceCharge(){
		return totalServ;
	}
 public void setServiceCharge(double currentServ){
		totalServ = totalServ + currentServ;
	}
}
