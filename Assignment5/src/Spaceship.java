import java.awt.Image;

public class Spaceship extends Sprite2D {

	public Spaceship(Image i, int windowWidth) {
		super(i, i, windowWidth);
		this.myImage = i;
		this.setImageWidthAndHeight(54, 32);
	}
	
	public void move() {
		x = x+ xSpeed;
	}
}