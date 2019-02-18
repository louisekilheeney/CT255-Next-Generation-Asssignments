import java.awt.Image;

public class Spaceship extends Sprite2D {

	public Spaceship(Image i, int windowWidth) {
		super(i, windowWidth);
		this.myImage = i;
		
	}
	
	public void move() {
		x = x+ xSpeed;
	}
}
