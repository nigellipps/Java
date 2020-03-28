//This file does not compile with the project 
//SEPERATE

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.text.NumberFormat;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Red {
	public static Menu frame;
	public static JTextArea na;
	 public static void  main(String[] args) {
		    frame = new Menu("Final Lab");	// change the format of the menu pane
		    //set the font and format
		    na = new JTextArea(10,50);
		    na.setFont(new Font("Monospaced",Font.PLAIN, 12));

		    frame.getContentPane().add(na);
		    frame.pack();
		    frame.setVisible(true); 
	 }
	 
	 public static void quadSolver() {
		  double A = 0, B = 0, C = 0, answer1 = 0, answer2 = 0;

				  JPanel D = new JPanel();
				  JTextField a = new JTextField(10);
				  JTextField b = new JTextField(10);
				  JTextField c = new JTextField(10);
				  D.add(new JLabel("A = "));
				  D.add(a);
				  D.add(Box.createHorizontalStrut(15)); // a spacer
				  D.add(new JLabel("B = "));
				  D.add(b); 
				  D.add(Box.createHorizontalStrut(15)); // a spacer
				  D.add(new JLabel("C = "));
				  D.add(c);  
				  D.setPreferredSize(new Dimension(550, 200));
				  JOptionPane.showConfirmDialog(null, D, "Quadratic", JOptionPane.OK_CANCEL_OPTION);

				  if (a.getText().equals("")) 
					  A = 0;     
	    	  		  else 
	    	  			  A = Double.parseDouble(a.getText());     
	    	  		  if (b.getText().equals("")) 
	    	  			  B = 0;      
	    	  		  else 
	    	  			  B = Double.parseDouble(b.getText()); 
	    	  		  if (c.getText().equals("")) 
	    	  			  C = 0;      
	    	  		  else 
	    	  			  C = Double.parseDouble(c.getText());
	    	  		  
	    	  		  answer1 = (-B + Math.sqrt((B * B) - (4 * A * C))) / (2 * A);
	    	  		  answer2 = (-B - Math.sqrt((B * B) - (4 * A * C))) / (2 * A);
	  
		 String message = answer1 + " and " +answer2;
			na.setText(message);
	 }
	 public static void FacSolver() {		 
			int factorial = 0;

						String Fac = JOptionPane.showInputDialog ("Enter the  number ");
						factorial = Integer.parseInt(Fac);

		 sum(factorial);
	 }
	 
	 public static void sum (int num) {
	 double result;
	 
	 if (num == 1)
		 result = 1; 
	 else
		 result = num + sum((num– 1));
	 
	 String message = "Answer: " + result;
		na.setText(message); 
	 }
	 
	 public static void areaCirc() {
		 boolean promptUser = true;
			double area = 0;
				do {
					try {
						String initial = JOptionPane.showInputDialog ("Enter the radius of a circle ");
						area = Double.parseDouble(initial);
						promptUser = false;
					} catch (NumberFormatException n) {
						System.out.println("Don't do that");
					}
				} while (promptUser);	
		 
		 String message = "The area of the circle is ";
		 message += area;
			na.setText(message);
	 }
	 public static void Lookup() {
		 String message = "Lookup chemical element selected";
			na.setText(message);
	 }
	 public static void Velocity() {
		 String message = "Calcualte exit velocity selected"; 
			na.setText(message);
	 }
}
