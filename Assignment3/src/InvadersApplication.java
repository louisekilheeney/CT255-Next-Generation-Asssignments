import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //needed to be able to use KeyListener
import javax.swing.*;


public class InvadersApplication extends JFrame implements Runnable, KeyListener { 

	//member data
	private static final Dimension WindowSize = new Dimension(600,600);
	private static String workingDirectory;
	private static final int NUMALIENS = 30; 
	private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
	private Sprite2D playerShip;
	private static boolean name = false;

	//constructor 
	public InvadersApplication() {

		this.setTitle("Assignment 3"); //title of assignment
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width / 2 - WindowSize.width / 2;
		int y = screensize.height / 2 - WindowSize.height / 2;
		setBounds(x, y, WindowSize.width, WindowSize.height); //setting bounds of JFrame
		setVisible(true); //setting to true allows us to see the JFrame
		addKeyListener(this); //IMPORTANT: KeyListener need to have this to work!!
		
		// load image from my computer using getImage() creating the image
		ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
		Image Alienspic = icon.getImage(); 
	
		

		//creating 29 of aliens and placing them in random places using setPosition for each X,& Y, also 
		//sending alien  picture to the Sprite2D method. 
		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray[i] = new Sprite2D(Alienspic);
			AliensArray[i].setPosition((int)(Math.random()*600), (int)(Math.random()*600));
		}
	//creating a single playership
		ImageIcon playship = new ImageIcon(workingDirectory + "\\player_ship.png");
		Image playerspic = playship.getImage(); 
		
		//drawing player ship and setting it at bottom of the jfame so that it doesnt move around the jframe
		playerShip = new Sprite2D(playerspic);
		playerShip.setPosition(250, 550); // fixed postion. 

		name = true;
		//creating threads 
		Thread t = new Thread(this);
		
		t.start();
	}
	
	//threads entry point 
		@Override 
		public void run() {
			
			//System.out.printf("THIS WORKS");
			while(true) {
				for(int i = 0; i < NUMALIENS; i++) {
					AliensArray[i].moveEnemy(); //calling moveEnemy method in sprite2D to move the image 
					
				} 
				playerShip.movePlayer(); //move the player ship across the screen 
				repaint();
				
				try{
					Thread.sleep(20); //forces us to  catch exception 
				}
				
				catch(InterruptedException e) {}
				
			}
		}
	
	
	//three keyboard event-handler functions 
		@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // search for keyCode
		if(key == KeyEvent.VK_RIGHT) { //if right key is used move plus 10 places to the right
			playerShip.setXSpeed(10);
			
		}
		
		if(key == KeyEvent.VK_LEFT) {
			playerShip.setXSpeed(-10); //if left key is used move minus 10 places to the left
		}

	}
		@Override
	public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT) {
				playerShip.setXSpeed(0); //setting speed to zero as when released we dont want it to move 
				
			}
			
			if(key == KeyEvent.VK_LEFT) {
				playerShip.setXSpeed(0); //setting speed to zero as when released we dont want it to move 
			}
	}
		@Override
	public void keyTyped(KeyEvent e) {
		//printEventInfo("Key Typed", e);

	}

	
	
	//applications paint method
	public void paint(Graphics g) {
		g.clearRect(0, 0, WindowSize.width, WindowSize.height); //clear jfame
		g.setColor(Color.BLACK); //setting background colour
		
		g.fillRect(0, 0, 600, 600); 
		playerShip.paint(g); //print the player ship once
		for(int i = 0; i < NUMALIENS; i++) {
		AliensArray[i].paint(g); //print the aliens to the set number of 30. 
		
		}
			
	}
	
	//application entry point 
	public static void main(String[] args) {
		workingDirectory = System.getProperty("user.dir"); //finding the file/Image in my own computer. 
		System.out.println(System.getProperty("user.dir"));
		System.out.println("Working Directory = " + workingDirectory);
		InvadersApplication m = new InvadersApplication();
		
	}
	
}
