import java.awt.*;
import javax.swing.*;


public class MovingSquaresApplication extends JFrame implements Runnable {
	
	private static final Dimension WindowSize = new Dimension(600, 600);
	private static final int NUMGAMEOBJECTS = 30;
	private GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];
	
	// constructor 
	public MovingSquaresApplication() {
		//Create and set up the window.

		this.setTitle("Assignment 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Display the window, centered on the screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width / 2 - WindowSize.width / 2;
		int y = screensize.height / 2 - WindowSize.height / 2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
		
		//creating objects in the array. 
		for(int i = 0; i < NUMGAMEOBJECTS; i++) {
			GameObjectsArray[i] = new GameObject();
		}
		
		
		Thread t = new Thread(this);
		t.start();
	}
	
	//threads entry point while there is objects in the array call the move method on each object. repaint() keeps reload the 
	//squares. thread.sleep slows them down, the exception is needed to catch any error. 
	@Override 
	public void run() {
		
		//System.out.printf("THIS WORKS");
		while(true) {
			for(int i = 0; i < NUMGAMEOBJECTS; i++) {
				GameObjectsArray[i].move();
			} 
			repaint();
			
			try{
				Thread.sleep(70);
			}
			
			catch(InterruptedException e) {}
			
		}
	}
	
	//applications paint method draws the GameObjects by calling their paint(Graphics g) methods using clearRect clears the 
	//field so that the boxes don't over write each other. i then looped through the array to print each box and calling the 
	//paint method to do so. 
	public void paint(Graphics g) {
		g.clearRect(0, 0, WindowSize.width, WindowSize.height);
		for(int i = 0; i < NUMGAMEOBJECTS; i++) {
			
			GameObjectsArray[i].paint(g);
		}
			
	}
	
	//applications entry point (main) declare new application 
	public static void main(String[] args) {
		
		MovingSquaresApplication  a = new MovingSquaresApplication();
	}
}
