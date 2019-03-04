//defines check number and get method for processing the check number for each check transaction
public class Check extends Transaction{
	private int checkNum; // check number for each check transaction
	
	public Check (int tCount, int tId, double tAmt, int checkNum) { 	
		super(tCount, tId, tAmt);
		this.checkNum = checkNum;
	}
	
	public int getCheckNumber() { 
		return checkNum;
	}
	
	public void setCheckNumber(int checkNum) { 
		this.checkNum = checkNum;
	} 
}
