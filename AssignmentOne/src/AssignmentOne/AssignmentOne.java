package AssignmentOne;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;



public class AssignmentOne extends JFrame {
	private static final Dimension WindowSize = new Dimension(600, 600);

	public AssignmentOne() {
		
		this.setTitle("Assignment One");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Display the window, centered on the screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width / 2 - WindowSize.width / 2;
		int y = screensize.height / 2 - WindowSize.height / 2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		int i = 0;
		int j = 0;
		int squares = 50;
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		//System.out.println(screensize.height);
		
		for (i = 0; i < screensize.width/squares; i++) {
			for (j = 0; j < screensize.height/squares; j++) {
			int red =  (int)(Math.random()*256);
			int green =  (int)(Math.random()*256);
			int blue =  (int)(Math.random()*256);
			Color c = new Color(red,green,blue);
			g.setColor(c);
			g.drawRect(i*squares, j*squares, screensize.width/squares, screensize.height/squares);
			g.fillRect(i*squares, j*squares, screensize.width/squares, screensize.height/squares);
			
		}
		}
		
	}


	public static void main(String[] args) {
		AssignmentOne w = new AssignmentOne();
	}
}