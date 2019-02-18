import java.awt.*;


public class Sprite2D {
	//member data 
	private double x,y;
	private double xSpeed = 0 ; //setting speed to zero
	private Image myImage;

	
	
	//constructor 
	public Sprite2D(Image i) {
		this.myImage = i; //declaring image to i

	}
	
	//public interface 
	public void moveEnemy() { //method to move aliens around the jframe
		x = x+ (int)((Math.random()-0.5)*10);
		y = y+ (int)((Math.random()-0.5)*10);
	}
	
	public void setPosition(double xx, double yy) { //setPostion for both aliens and space ship
		x = xx;
		y = yy;
	}
	
	public void movePlayer() { //allows space ship to move right and left
		x = x+ xSpeed;
	}
	
	public void setXSpeed(double dx) { //setting speed equal to dx which is zero at firsted
		xSpeed = dx;
	}
	
	public void paint(Graphics g) { //drawing image
		 
		g.drawImage(myImage, (int)x, (int)y ,null);
	}
	
}
