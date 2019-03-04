 import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Vector; // add the vector class
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
// 1 => check
// 2 => deposit
// 3 => service charge 

// play this one
public class Main {
// global variables
	static int amount0 = 10, amount500 = 5;
	static boolean fine500 = true;
//	static String Name;
	static double Initialnum;
	public static String filename = "C:\\account.txt";
	
	//create a new vector for new accounts
	public static Vector<CheckingAccount> dataStore;
	public static JTextArea ta;
	public static boolean saved=false;
	public static int index = -1;

	// definition of objects
	public static CheckingAccount checkingaccount;
	public static COptionPanel frame;
	
public static void main (String[] args) {	
	//create a new vector
	dataStore = new Vector<CheckingAccount>(); // create my new vector

    frame = new COptionPanel("Checking Account Menu");	// change the format of the menu pane
    //set the font and format
    ta = new JTextArea(10,50);
    ta.setFont(new Font("Monospaced",Font.PLAIN, 12));

    frame.getContentPane().add(ta);
    frame.pack();
    frame.setVisible(true); 								                 
}

public static void game() {
	try {
		Deposit d = new Deposit (checkingaccount.getTransCount(), 2, 10, 0);				
		checkingaccount.addTrans(d);
		checkingaccount.setBalance(10,2);
		String message = "$10 added for playing game";
	    ta.setText(message);
	    
	    JFrame wind = new JFrame("THE GAME");
        Game g = new Game(); 
        wind.add(g);
        wind.pack();
        wind.setLocationRelativeTo(null);
        wind.setVisible(true);
        wind.addMouseMotionListener(g);
        Timer tt = new Timer(17, g);
        tt.start();
        wind.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	} catch (ArrayIndexOutOfBoundsException n) {
		System.out.println("Please add an account");
	} catch (NullPointerException e) {
		System.out.println("Null pointer exception");
	}
}

public static void addAcct(){    //creates a new account and adds to the vector
	String Name = name();
    Initialnum = getInitial();
    
	checkingaccount = new CheckingAccount(Name, Initialnum);

    dataStore.addElement(checkingaccount);
    index++;
    saved = false;
}


public static void findAcct(){  //looks for the account using the name string in getName
     String name, message;

     name = JOptionPane.showInputDialog ("Enter the account name: ");
	// search through vector
	for (int Index=0; Index != dataStore.size(); Index++) {
	  CheckingAccount datum = dataStore.elementAt(Index);
					
	  // check on the name of the element
	  if (name.equals(datum.getName())) {
		  message = "Found account for " + name;
          ta.setText(message);  
          index = Index;
		}
	}
}


public static void textArea(String message) {
	JTextArea ta = new JTextArea();
	ta.setText(message);
	ta.setFont (new Font (Font.MONOSPACED, Font.PLAIN, 18));
	JOptionPane.showMessageDialog (null, ta); 
}


public static void readFile() {		//simpler read file
	 int confirm;  
     if (!saved)
     {
        String  message = "The data in the application is not saved.\n"+
            "would you like to save it before reading the new file in?";
        confirm = JOptionPane.showConfirmDialog (null, message);
        if (confirm == JOptionPane.YES_OPTION) {
			try {
				writeFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
     }
		JFileChooser fileChooser = new JFileChooser(" ");		//ask for file same as write
		int status = fileChooser.showOpenDialog(null);
		try {
			fileChooser.setDialogTitle("Read from file");
			File selected = fileChooser.getSelectedFile();		
			
			if (status == JFileChooser.APPROVE_OPTION) {
             // Use this for reading the data.

				FileInputStream fis = new FileInputStream(selected);
			ObjectInputStream in = new ObjectInputStream(fis);
          	dataStore = (Vector<CheckingAccount>)in.readObject();        
          	in.close();
          	saved = true;
			}
		}
	catch(ClassNotFoundException e)	
       { 
           System.out.println(e);
       }
      catch (IOException e) 
       { 
           System.out.println(e);
       }
}

public static void writeFile() throws FileNotFoundException {			//simpler write file with a filenotfound throw
    	try {   		
    				JFileChooser fileChooser = new JFileChooser(" ");
    				int status = fileChooser.showSaveDialog(null);
    				
    					// Assume default encoding.
    					fileChooser.setDialogTitle("Write to file");
    					//The name of the file
    					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    					File selectedFile = fileChooser.getSelectedFile();
    					if (status == JFileChooser.APPROVE_OPTION) {	
    						
    						FileOutputStream fos = new FileOutputStream(selectedFile);
    						ObjectOutputStream out = new ObjectOutputStream(fos);
    						out.writeObject(dataStore);
    						out.close();
    						saved = true;
    					}
    	}
	catch(IOException e)	{ 
                   System.out.println(e);
              }
}

public static void listAll() {
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	try {
	checkingaccount = dataStore.elementAt(index);			//retrieve the correct checking account
	Transaction transaction;
	double balance = checkingaccount.getBalance();
	String message,type;
	
	message = "Account: " +checkingaccount.getName() + "\n" + "Balance: ";
	
	if (balance < 0) {
		balance = balance * (-1);						//make sure there are parentheses around negative amounts and not negative sign 
		message += "(" + fmt.format(balance) + ") \n";
	}
	else 
		message += fmt.format(balance) + "\n";
	
	
	message += "Total Service Charge: " + fmt.format(checkingaccount.getServiceCharge()) + "\n"+
			  "List all Transactions: \n";
	message += String.format("\n%-11s %-10s   %10s \n", "ID", "Type", "Amount ");
	int i = 0;
	int count = checkingaccount.getTransCount();
	
	while (i < count) { 	
		
		transaction = checkingaccount.getTrans(i);								//access transactions within a checking account
		
		if (transaction.getTransId() == 1)
			type = "Check";
		else if (transaction.getTransId() == 2)
			type = "Deposit";
		else
			type = "Svc. Chrg.";
		String amount = fmt.format(transaction.getTransAmount());
		message += String.format("\n%-10d  %-10s  %10s\n", transaction.getTransNumber(), type,  amount);
		i++;
	}	
	ta.setText(message);
	} catch (ArrayIndexOutOfBoundsException n) {
		System.out.println("Please add an account");
	}
}

//2 is a deposit
public static void listTrans() {
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	try {
	checkingaccount = dataStore.elementAt(index);		//retrieve the correct checking account
	Transaction transaction;
	String message;
	message = "List all Deposits for " + checkingaccount.getName() + ":\n\n";	
	message += String.format("\n%-5s %-10s %10s %10s %10s\n", "ID", "Type", "Checks", "Cash", "Amount");
	int i = 0;

	int count = checkingaccount.getTransCount();	
	
	while (i< count) {
				
		transaction = checkingaccount.getTrans(i);		
		if (2 == transaction.getTransId()) { 
			String amount = fmt.format(transaction.getTransAmount());
			String check = fmt.format(((Deposit)transaction).getCheck());
			String cash = fmt.format(((Deposit)transaction).getCash());
			message += String.format("\n%-5d %-10s %10s %10s %10s\n", transaction.getTransNumber(), "Deposit", check, cash, amount);
			message += "\n";
		}
		i++;
	}		
	ta.setText(message);
	} catch (ArrayIndexOutOfBoundsException n) {
		System.out.println("Please add an account");
	}
}

//1 is a check
public static void listChecks() {
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	try {
	checkingaccount = dataStore.elementAt(index);		//retrieve the correct checking account
	Transaction transaction;
	String message;
	message = "Listing all Checks for " + checkingaccount.getName() + "\n\n";	
	message += String.format("\n%-5s %-8s  %10s \n", "ID", "Check", "Amount ");																					
	int i = 0;
	
	int count = checkingaccount.getTransCount();
	
	while (i < count) {
				
		transaction = checkingaccount.getTrans(i);
		if (1 == transaction.getTransId()){ 
			String amount = fmt.format(transaction.getTransAmount());
			message += String.format("\n%-5d %-8d %10s\n", transaction.getTransNumber(), ((Check)transaction).getCheckNumber(), amount);
		}		
		i++;
	}	
	ta.setText(message);
	} catch (ArrayIndexOutOfBoundsException n) {
		System.out.println("Please add an account");
	}
}

public static void GUI() {
	try {
	checkingaccount = dataStore.elementAt(index);	//retrieve the correct checking account
	frame.setVisible(false);
	boolean Zero = false;
	double amount = 0;	
	double Old;
	double New = checkingaccount.getBalance();
	int checkNum = 0;
	int code = getTransCode();					//get transaction code (1,2,0)
												//find if makes balance negative, add charge and add to array
	switch (code) {
		case 1: 	
			checkNum = getCheckNum();
			amount = getTransAmt();				
			Check c = new Check (checkingaccount.getTransCount(), 1, amount, checkNum);
			checkingaccount.addTrans(c);
			break;
		case 2:				
			amount = getCash();
			break;
		default:
			finalMessage();
	} 	 	
    	checkingaccount.setBalance(amount,code);	    	 			
    	switch (code) {
    		case 1:
//    			Old = New;
//    			New = checkingaccount.getBalance();	    	
 //   			if (Old >= 0 && New < 0) {								//got rid of all this so it charges every time it is in the negatives
//    			if (New < 0) {
//    				checkingaccount.setServiceCharge(amount0);
// 	 			Zero = true;
//	 		}    	 			
    			processCheck(amount, checkNum);
		break;
		case 2: 			
		    	processDeposit(amount);
		break;
	}
    	saved = false;
frame.setVisible(true);
	} catch (ArrayIndexOutOfBoundsException n) {
		System.out.println("Please add an account");
	}
}

public static double getCash() {	 
	  double cashAmt = 0, checkAmt = 0;
	  boolean promptUser = true;
	  do {
		  try {
			  JPanel D = new JPanel();
			  JTextField cash = new JTextField(10);
			  JTextField check = new JTextField(10);
			  D.add(new JLabel("Cash"));
			  D.add(cash);
			  D.add(Box.createHorizontalStrut(15)); // a spacer
			  D.add(new JLabel("Checks"));
			  D.add(check);   
			  D.setPreferredSize(new Dimension(150, 100));
			  JOptionPane.showConfirmDialog(null, D, "Deposit", JOptionPane.OK_CANCEL_OPTION);

			  if (cash.getText().equals("")) 
				  cashAmt = 0;     
    	  		  else 
    	  			  cashAmt = Double.parseDouble(cash.getText());     
    	  		  if (check.getText().equals("")) 
    	  			  checkAmt = 0;      
    	  		  else 
    	  			  checkAmt = Double.parseDouble(check.getText());     
    	  		  Deposit d = new Deposit (checkingaccount.getTransCount(), 2, cashAmt, checkAmt);				
    	  		  checkingaccount.addTrans(d);
    	  		  promptUser = false;
		  }catch (NumberFormatException e) {
    	  		  System.out.println("Number Format Exception");	
		  } 
	  } while(promptUser);
      return (cashAmt + checkAmt); 
}

public static String name() {
	boolean promptUser = true;
	String N = null;
	do {
			N = JOptionPane.showInputDialog ("Enter the account name: ");
		if  (N.isEmpty()) 
			System.out.println("Enter a name");			
		else 
			promptUser = false;		
	} while (promptUser);
	return N;
}

public static double getInitial() {
	boolean promptUser = true;
	double Initialnum = 0;
		do {
			try {
				String initial = JOptionPane.showInputDialog ("Enter your initial balance: ");
				Initialnum = Double.parseDouble(initial);
				promptUser = false;
			} catch (NumberFormatException n) {
				System.out.println("Don't do that");
			}
		} while (promptUser);
	return Initialnum;
}

public static int getTransCode() {
	int Code = 0;
	boolean promptUser = true;
	do {
		try {
			String tCode = JOptionPane.showInputDialog ("Enter the transaction code ('1' for check, '2' for deposit, '0' to end this session):");
			Code = Integer.parseInt(tCode);
			promptUser = false;
		} catch (NumberFormatException n) {
			System.out.println("No no no");
		}
	} while(promptUser);
	return Code;	
}

public static double getTransAmt() {
	double Amt = 0;
	boolean promptUser = true;
	do {
		try {
			String transAmt = JOptionPane.showInputDialog ("Enter Trans Amt: ");
			Amt = Double.parseDouble(transAmt);
			promptUser = false;
		} catch (NumberFormatException n) {
			System.out.println("No no no");
		}	
	} while(promptUser);
	return Amt;
}

public static int getCheckNum() {
	int checkNum = 0;
	boolean promptUser = true;
	do {
		try {
			String checkNumber = JOptionPane.showInputDialog ("Enter the check number: ");
			checkNum = Integer.parseInt(checkNumber);
			promptUser = false;
		} catch (NumberFormatException n) {
			System.out.println("No no no");
		}	
	} while(promptUser);	
	return checkNum;
}

//selected 1
public static void processCheck(double amount, int checkNum) {
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	double balance = checkingaccount.getBalance();
	boolean neg = false;
	String ser = fmt.format(checkingaccount.getServiceCharge());
	String check = checkingaccount.getName() + "'s account: \nTransaction: check #" + checkNum + " in amount of " + fmt.format(amount) + "\nCurrent balance: ";
	
	if (balance < 0) {
		balance = balance * (-1);
		neg = true;
		check += "(" + fmt.format(balance) + ") \n";
		checkingaccount.setServiceCharge(amount0);
	}
	else 
		check += fmt.format(balance) + "\n";  
	check += "Service Charge: Check --- charge $0.15" + "\n";
	String warning = "Warning: Balance below $50.00 \n";
	String zero = "Service charge: Below $0 --- charge $10.00" + "\n";
	String below500 = "Service Charge: Below $500 --- charge $5.00" + "\n";
	
		if (fine500 == true && balance < 500) {		
			checkingaccount.setServiceCharge(amount500);
			ser = fmt.format(checkingaccount.getServiceCharge());
			check += below500;		
			fine500 = false;
			Transaction chargeCk = new Transaction (checkingaccount.getTransCount(), 3, amount500);
			checkingaccount.addTrans(chargeCk);
		}	
		ser = fmt.format(checkingaccount.getServiceCharge());		
		if(balance < 50 || neg == true)
			check += warning;
		if(neg == true) {
			check += zero;	
			Transaction chargeCk = new Transaction (checkingaccount.getTransCount(), 3, amount0);
			checkingaccount.addTrans(chargeCk);
		}
		neg = false;
		String total = "Total Service Charge: " + ser;
		check += total;
	JOptionPane.showMessageDialog(null, check);
}

//select 2
public static void processDeposit(double amount) {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		double balance = checkingaccount.getBalance();
		String ser = fmt.format(checkingaccount.getServiceCharge());
		String deposit = checkingaccount.getName() + "'s account: \nTransaction: deposit in amount of " + fmt.format(amount) + "\nCurrent balance: ";
		boolean neg = false;

		if (balance < 0) {
			balance = balance * (-1);
			neg = true;
			deposit += "(" + fmt.format(balance) + ") \n";  
		}
		else 
			deposit += fmt.format(balance) + "\n"; 
		
		deposit += "Service Charge: Deposit --- charge $0.10" + "\n";
		
		String warning = "Warning: Balance below $50\n";
		String total = "Total Service Charge: " + ser;					
		
		if (balance < 50 || neg == true)
			deposit += warning;
		neg = false;
		deposit += total;
	JOptionPane.showMessageDialog(null, deposit);
}

public static void finalMessage() {
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	double getBalance = checkingaccount.getBalance();
	double getServiceCharge = checkingaccount.getServiceCharge();
    double finalBalance = getBalance-getServiceCharge;
	String Final = "Transaction: End\nCurrent Balance: ";

     if (getBalance >= 0)
    	 	Final += fmt.format(getBalance);
     else {
    	 	getBalance = getBalance * (-1);  	
    	 	Final += "(" + fmt.format(getBalance) + ")";		                 
     }
     Final += "\nTotal Service Charge: "+ fmt.format(getServiceCharge) + "\nFinal Balance: "; 
     
     if (finalBalance >= 0) 
    	 	Final += fmt.format(finalBalance) +"\n";
     else {
         finalBalance = finalBalance * (-1);
    	 	Final += "(" + fmt.format(finalBalance) + ")\n";
     }   
     JOptionPane.showMessageDialog(null, Final);
}

}