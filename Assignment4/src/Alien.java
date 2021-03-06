import java.awt.Image;

public class Alien extends Sprite2D {
	
	private boolean direction = true;

	public Alien(Image i, int windowWidth) {
		super(i, windowWidth);
		this.myImage = i;	
		x = 0;
		y = 0;
	}

	// move method checks the bounds against the x and y of the alien, returns true if alien has hit a bound(wall).  
	public boolean move() {
		if (direction) { //direction shows if we move positive or negative(back and forth across  the screen).
			x += xSpeed;
		}
		else {
			x -= xSpeed;
		}
		if(x<=0) {
			x = 0;
			return true;
		}
		else if(x>=super.winWidth - 50) {
			x=super.winWidth - 50 ;
			return true;
		}
		if (y<=50) {
			y=50;
			return true;
		}
		else if(y >= 500) {
			y=500;
			return true;
		}
		return false;
	}

	public void reverseDirection() {
		direction = !direction ; //direction if true flip or if false flip..toggling
		y += ySpeed;	//drop down alien down the screen by y speed. 
	}
}
