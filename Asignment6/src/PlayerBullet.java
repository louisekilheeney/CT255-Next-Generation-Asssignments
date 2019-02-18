
import java.awt.Image;

public class PlayerBullet extends Sprite2D {

	public PlayerBullet(Image i, int windowWidth) {
		super(i, i, windowWidth);
		this.myImage = i;
		this.setImageWidthAndHeight(6, 16);
	} 
	public void move() {
		y -= ySpeed;
	}
}




