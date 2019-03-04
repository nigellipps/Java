//defines the cash and check amounts that comprise the total deposit transaction amount
public class Deposit extends Transaction {
		private double cashAmt;
		private double checkAmt;
	
	public Deposit(int tCount, int tID, double cashAmt, double checkAmt) {	
		super(tCount, tID, cashAmt + checkAmt);
		this.cashAmt = cashAmt;
		this.checkAmt = checkAmt;
		transAmt = cashAmt + checkAmt;
	}
	public double getCash() {
		return this.cashAmt;
	}
	public double getCheck() {
		return this.checkAmt;
	}
	public double getTotalDeposit() {
		return transAmt;
	}
}
