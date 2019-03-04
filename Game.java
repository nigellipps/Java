import java.awt.*;
import java.awt.event.*;
//import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.*;
import javax.swing.*;

public class Game extends JComponent implements ActionListener, MouseMotionListener, KeyListener {

	private final int WIDTH = 800, HEIGHT = 600;
	private final int B_WIDTH = 30;
    private int ball1, ball2;
    private int rec = 0;
    private int ball2move, ball1move;
    public int score = 0, scoreNext = 0;
    private int Best;
    public boolean youLose, out; 
    
    protected void paintComponent(Graphics g) {
        Color GOLD = new Color(255,215,0);		//paints all the parts if the program and the new ball
        
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 550, WIDTH, 50);
        
        g.setColor(GOLD);
        g.fillRect(rec, 500, 100, 20);
        
        g.setColor(Color.black);
        g.fillOval(ball1, ball2, B_WIDTH, B_WIDTH);
        
        g.setColor(GOLD);
        g.setFont(new Font("Arial", 8, 50));
        g.drawString(String.valueOf("Score: " + score), 29 / 1 - 15, 80);
        
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 8, 50));
        if (youLose) {              
            g.drawString(String.valueOf(" Best Score :" + Best), 50 / 1 - 15, 200);            
        	}
    }
    
    public void newball(int ball1, int ball2, int ball1move, int ball2move) {
        ball1 = B_WIDTH;		//creates a new ball
        ball2 = B_WIDTH;
        ball1move = 5;
        ball2move = 7;
        return;
    }
    
    public Dimension getPreferredSize() {	//sets the dimensions for the screen
        return new Dimension(WIDTH, HEIGHT);
    }
    
    public void actionPerformed(ActionEvent e) {
        ball1 = ball1 + ball1move;	// the balls movements off the walls
        ball2 = ball2 + ball2move;
         
        if (ball1 >= rec && ball1 <= rec + 100 && ball2 >= 475) {
            ball2move = -7;
            score++;
        }
        if (ball2 >= 700 ) {   //when the ball misses rec
            score = 0;
            ball2 = B_WIDTH;
            youLose = true;
            out = true;
        }
        
        if (ball2 <= 0) {
            ball2move = 7;
        }
        
        if (ball1 >= 775) {
            ball1move = -5;
        }
        
        if (ball1 <= 0) {
            ball1move = 5;
        }
        //presents the scores
        if (score > Best) {
            Best = score;
            try {		//plays the sound every time the ball scores
            	String url = "file:C:/Users/nigellipps/eclipse-workspace/A2/src/one1.wav";
            		PlaySound.myPlay(url); 
            		System.out.println("df");
            		youLose = false;
            } catch (InterruptedException ie) {
            		System.out.println(ie);
            } 
        }        
        repaint();
    }
    //used to follow the mouse
    public void mouseMoved(MouseEvent e) {
        rec = e.getX() - 50;
        repaint();
    } //other necessary ones that do nothing  
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	public void keyReleased(KeyEvent sp) {
		// TODO Auto-generated method stub
	}	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	public void keyTyped(KeyEvent arg0) {
				
	}
}
