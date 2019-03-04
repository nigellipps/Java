import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
public class JFrameL extends JFrame {
    /** Creates a new instance of JFrameL */
    public JFrameL(String title) {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }
   private class FrameListener extends WindowAdapter
   {

    public void windowClosing(WindowEvent e) {
        int confirm;
        if (!Main.saved) {
           String  message = "The data in the application is not saved.\n"+
               "Would you like to save it before exiting the application?";
           confirm = JOptionPane.showConfirmDialog (null, message);
           if (confirm == JOptionPane.YES_OPTION)
			try {
				Main.writeFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("oops");
			} 
        }
       System.exit(0);
    }
   }   
}