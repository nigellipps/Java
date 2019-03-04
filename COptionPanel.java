import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class COptionPanel extends JFrameL {
	
	public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
	static CheckingAccount checkingaccount;
	static Game gameo;
	private JMenuBar bar;
	private JMenu fileMenu, acctMenu, transMenu, gameMenu;
	private JMenuItem fileRead, fileWrite, addAcct, listTrans, listChecks, listDeposits,
					  findAcct, addTrans, game;
	    
	public COptionPanel(String title) {
	    	super(title);
	    	setSize(WIDTH, HEIGHT);
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
		
		MenuListener ml = new MenuListener();
		
		fileMenu = new JMenu("File");
		      
		fileRead = new JMenuItem("read from file");
        fileRead.setFont(new Font("Helvetica", Font.BOLD, 12));
        fileRead.setBackground(Color.cyan);
		fileRead.addActionListener(ml);
		fileMenu.add(fileRead);
		
		fileWrite = new JMenuItem("write to file");
        fileWrite.setFont(new Font("Helvetica", Font.BOLD, 12));
        fileWrite.setBackground(Color.cyan);
        fileWrite.addActionListener(ml);
		fileMenu.add(fileWrite);
        
		acctMenu = new JMenu("Account");

		addAcct = new JMenuItem("add new account");
        addAcct.setFont(new Font("Helvetica", Font.BOLD, 12));
        addAcct.setBackground(Color.cyan);
        addAcct.addActionListener(ml);
        acctMenu.add(addAcct);
        																//add action listener
		listTrans = new JMenuItem("list accounts transactions");
        listTrans.setFont(new Font("Helvetica", Font.BOLD, 12));
        listTrans.setBackground(Color.cyan);
        listTrans.addActionListener(ml);
        acctMenu.add(listTrans);
        
		listChecks = new JMenuItem("list all checks");
        listChecks.setFont(new Font("Helvetica", Font.BOLD, 12));
        listChecks.setBackground(Color.cyan);
        listChecks.addActionListener(ml);
        acctMenu.add(listChecks);
        
		listDeposits = new JMenuItem("list all deposits");
        listDeposits.setFont(new Font("Helvetica", Font.BOLD, 12));
        listDeposits.setBackground(Color.cyan);
        listDeposits.addActionListener(ml);
        acctMenu.add(listDeposits);
        
		findAcct = new JMenuItem("find an account");
        findAcct.setFont(new Font("Helvetica", Font.BOLD, 12));
        findAcct.setBackground(Color.cyan);
        findAcct.addActionListener(ml);
		acctMenu.add(findAcct);
        
		transMenu = new JMenu("Transaction");

		addTrans = new JMenuItem("add transaction");
        addTrans.setFont(new Font("Helvetica", Font.BOLD, 12));
        addTrans.setBackground(Color.cyan);
        addTrans.addActionListener(ml);
		transMenu.add(addTrans);
		
		gameMenu = new JMenu("Game");
		
		game = new JMenuItem("Game");
		game.setFont(new Font("Helvetica", Font.BOLD, 12));
	    game.setBackground(Color.cyan);
	    game.addActionListener(ml);
	    gameMenu.add(game);
		
		bar = new JMenuBar( );
        bar.add(fileMenu);
        bar.add(acctMenu);
        bar.add(transMenu);
        bar.add(gameMenu);
        setJMenuBar(bar);	        
	}
	    
	private class MenuListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
		    	  
		    String source = event.getActionCommand();

		    if (source.equals("read from file")) 
		    		Main.readFile();	    	      	
	        else if (source.equals("write to file")) { 
				try {
					Main.writeFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        else if (source.equals("add new account"))	        	
	        		Main.addAcct();	
	        else if (source.equals("list accounts transactions"))
				Main.listAll();
	        else if (source.equals("list all checks"))
				Main.listChecks();
	        else if (source.equals("list all deposits")) 
				Main.listTrans();
	        else if (source.equals("find an account"))
				Main.findAcct();
	        else if (source.equals("add transaction"))
        			Main.GUI();
	        else if (source.equals("Game"))  
		    		Main.game();	        
	    }
	}
}

///*
// * Databases
// * 	Collection of info that is organized in a way that is easy to:
// * 		Access (get info)
// * 		Update
// * 		Manage (for example, re-organization of data)
// * Types of Data Bases
// * 	Relational: tables that are organized and accessed in different ways
// * 	Distributed: data that is replicated or dispersed among different access points
// * 	Object-oriented: data defined in classes and subclasses
// * Connect to a Database
// * 	Setup a driver to connect to the database; it needs the following info:
// * 		Location of the database
// * 		Username
// * 		Password
// * 	Once we setup the driver, we use it to open the connection
// * 	At the end, you must close the connection to the database
// * Structured Query Language
// * 
// * 
// * 
// * SELECT * <== what information do we want
// * FROM BOOK <== what data are we looking at
// * 
// * 
// * 1) Create connection to database
// * 2) Form the query or question you're going to ask
// * 3) Put it in a statement query
// * 4) Ask the question or query and get the results
// * 
// * 
// * Audio Clip
// * 	3 basic methods
// * 		play()	
// * 		loop()
// * 		stop()
// * 	URL can point to:
// * 		Audio clip on the Internet (http://www...)
// * 		local file (for example, file: C:/User/eamberosio/music.wav)
// * Timer class
// * 	Need an event to happen at regular intervals
// * 	Use this class to create the defined amount between events
// * 	The time is in milliseconds
// * 
// * 		1) set the interval
// * 		2) set the image to be painted
// * 		3) set the location
// * 		4) repaint()
// * 		5) repeat steps 2-4 at  the interval set in step 1
