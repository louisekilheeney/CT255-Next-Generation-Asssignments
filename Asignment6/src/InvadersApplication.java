import java.awt.Color;
import java.lang.Object;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {


	//member data
	private static final Dimension WindowSize = new Dimension(800,600);
	private static boolean isGraphicsInitialised = false;
	private BufferStrategy strategy;
	private Graphics offscreenGraphics;
	private static String workingDirectory;
	private static final int NUMALIENS = 30;
	private ArrayList <Alien> AliensArray = new ArrayList<Alien>();
	private Spaceship playerShip;
	private Image bulletImage;
	private ArrayList<PlayerBullet> bulletsList = new ArrayList<PlayerBullet>();

	//Constructor 
	public InvadersApplication() {

		this.setTitle("Assignment 5"); //title of assignment
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width / 2 - WindowSize.width / 2;
		int y = screensize.height / 2 - WindowSize.height / 2;
		setBounds(x, y, WindowSize.width, WindowSize.height); //setting bounds of JFrame
		setVisible(true); //setting to true allows us to see the JFrame


		// load image from my computer using getImage() creating the image
		ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
		Image aliensPic = icon.getImage(); 
		ImageIcon icon2 = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
		Image aliensPic2 = icon2.getImage(); 


		//creating 30 aliens set their x and y speeds.
		//sending alien  picture to the Sprite2D method, with window screen width
		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray.add (new Alien(aliensPic,aliensPic2,WindowSize.width));
			AliensArray.get(i).setXSpeed(10);
			AliensArray.get(i).setYSpeed(5);

		}

		int xPos = 0, yPos = 50, currentPos=0; //xPos and yPos are grid positions, currentPos keeps track of the position in the aliens array. 
		while(currentPos != NUMALIENS) {  //6 Columns 
			yPos = 50; //set to 50 so not to hit top screen bound
			for(int j = 0; j < 5; j++) { //five rows 
				AliensArray.get(currentPos).setPostion(xPos, yPos); //set positions 
				yPos += 50; //spacing between aliens on y axis
				currentPos++;
			}
			xPos += 50; //spacing between aliens on x axis
		}

		//creating a single playership
		ImageIcon playship = new ImageIcon(workingDirectory + "\\player_ship.png");
		Image playerspic = playship.getImage(); 

		//creating a bullet
		ImageIcon bullet = new ImageIcon(workingDirectory + "\\bullet.png");
		bulletImage = bullet.getImage(); 

		//drawing player ship and setting it at bottom of the Jframe so that it doesn't move around the Jframe
		playerShip = new Spaceship(playerspic, WindowSize.width);
		playerShip.setPostion(250, 550); // fixed position. 


		//creating threads 
		Thread t = new Thread(this);

		t.start();
		addKeyListener(this); //IMPORTANT: KeyListener need to have this to work!!

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		offscreenGraphics = strategy.getDrawGraphics();
		isGraphicsInitialised = true;
	}

	@Override 
	public void run() {

		while(true) { 
			try{ //threads entry point 
				Thread.sleep(20); //forces us to  catch exception 
			}

			catch(InterruptedException e) {}
			boolean hasAlienHitBoundary = false; 
			boolean boundaryHit = false;
			for(int i = 0; i < AliensArray.size() ; i++) {
				hasAlienHitBoundary = AliensArray.get(i).move(); 
				if(hasAlienHitBoundary) {
					boundaryHit = true;
				}

			} 
			if (boundaryHit) {
				for(int j = 0; j< AliensArray.size(); j++) {
					AliensArray.get(j).reverseDirection();
				}
			}
			playerShip.move(); //move the player ship across the screen 
			for( int i = 0; i < bulletsList.size(); i++) {
				bulletsList.get(i).move(); 
				//check if cullision has happened between alien and bullet
				for(int j = 0; j < AliensArray.size(); j++) {
					if (collision( AliensArray.get(j), bulletsList.get(i))) {
						AliensArray.remove(AliensArray.get(j));
						bulletsList.remove(bulletsList.get(i));
						break;
					}
				}
			} 
			//check if cullision has happened between alien and player
			for(int j = 0; j < AliensArray.size(); j++) {
				if (collision( AliensArray.get(j), playerShip)) {
					//GAME OVER
					System.out.println("GAME OVER");
					break;
				}
			}
			this.repaint();
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
		if (key == KeyEvent.VK_SPACE) {
			shootBullet();
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

	public void shootBullet() {
		//add a new bullet to our list
		//
		PlayerBullet b = new PlayerBullet(bulletImage, WindowSize.width);
		b.setPostion(playerShip.x+54/2, playerShip.y);
		b.setYSpeed(5);
		bulletsList.add(b);

	}

	public boolean collision(Sprite2D sprite1, Sprite2D sprite2) {
		return (( (sprite1.x < sprite2.x && sprite1.x+sprite1.width > sprite2.x)
				|| (sprite2.x < sprite1.x && sprite2.x + sprite2.width > sprite1.x) ) && ( (sprite1.y < sprite2.y && sprite1.y + sprite1.height > sprite2.y)
						|| (sprite2.y < sprite1.y && sprite2.y + sprite2.height > sprite1.y) )) ;	
	}

	//applications paint method
	public void paint(Graphics g) {

		if (isGraphicsInitialised) {
			g = strategy.getDrawGraphics();
			g.setColor(Color.BLACK); //setting background color

			g.fillRect(0, 0, WindowSize.width, WindowSize.height); 
			for(int i = 0; i < AliensArray.size(); i++) {
				AliensArray.get(i).paint(g); //print the aliens to the set number of 30. 
			}
			playerShip.paint(g); //print the player ship once

			//for each bullet in the Arraylist (print)
			Iterator<PlayerBullet> iterator = bulletsList.iterator();
			while(iterator.hasNext()){
				PlayerBullet b = (PlayerBullet) iterator.next();
				b.paint(g);
			}
			strategy.show();

		}
	}

	//application entry point 
	public static void main(String[] args) {
		workingDirectory = System.getProperty("user.dir"); //finding the file/Image in my own computer. 
		System.out.println("Working Directory = " + workingDirectory);
		InvadersApplication m = new InvadersApplication();

	}

}
