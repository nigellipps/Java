import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrameL {

public static final int WIDTH = 300;
public static final int HEIGHT = 200;
private JMenuBar bar;
private JMenu mathMenu, scienceMenu;
private JMenuItem quadSolver, FacSolver, areaCirc, Lookup, Velocity;
    
public Menu(String title) {
    	super(title);
    	setSize(WIDTH, HEIGHT);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	
	MenuListener ml = new MenuListener();
	
	mathMenu = new JMenu("Math");
	      
	quadSolver = new JMenuItem("Quadratic Solver");
    quadSolver.setFont(new Font("Helvetica", Font.BOLD, 12));
	quadSolver.addActionListener(ml);
	mathMenu.add(quadSolver);
	
	FacSolver = new JMenuItem("Factorial Solver");
    FacSolver.setFont(new Font("Helvetica", Font.BOLD, 12));
    FacSolver.addActionListener(ml);
	mathMenu.add(FacSolver);
	
	areaCirc = new JMenuItem("Area of a Circle");
    areaCirc.setFont(new Font("Helvetica", Font.BOLD, 12));
    areaCirc.addActionListener(ml);
	mathMenu.add(areaCirc);
    
	scienceMenu = new JMenu("Science");

	Lookup = new JMenuItem("Find Element");
    Lookup.setFont(new Font("Helvetica", Font.BOLD, 12));
    Lookup.setBackground(Color.cyan);
    Lookup.addActionListener(ml);
    scienceMenu.add(Lookup);
    																//add action listener
	Velocity = new JMenuItem("Calc Exit Velocity");
    Velocity.setFont(new Font("Helvetica", Font.BOLD, 12));
    Velocity.setBackground(Color.cyan);
    Velocity.addActionListener(ml);
    scienceMenu.add(Velocity);
    
	
	
	bar = new JMenuBar( );
    bar.add(mathMenu);
    bar.add(scienceMenu);
    setJMenuBar(bar);	        
}
    
private class MenuListener implements ActionListener {
	public void actionPerformed (ActionEvent event) {
	    	  
	    String source = event.getActionCommand();

	    if (source.equals("Quadratic Solver")) 
	    		Red.quadSolver();	    	      	
        else if (source.equals("Factorial Solver")) 
        		Red.FacSolver();
        else if (source.equals("Area of a Circle")) 
        	   	Red.areaCirc();	
        else if (source.equals("Find Element"))
			Red.Lookup();
        else if (source.equals("Calc Exit Velocity"))
			Red.Velocity();

    }
}
}
