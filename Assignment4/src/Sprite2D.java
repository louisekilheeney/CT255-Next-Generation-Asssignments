import java.awt.*;

public class Sprite2D {

		//member data 
	protected double x,y;
	protected double xSpeed = 0;
	protected double ySpeed = 0;
	protected Image myImage;
	int winWidth;
	
	public Sprite2D(Image i, int windowWidth) {
		this.myImage = i; //declaring image to i
		this.winWidth = windowWidth;
	}
	
	public void setPostion(double xx, double yy) {
		x = xx;
		y = yy;
	}
	
	public void setXSpeed(double dx) {
		xSpeed = dx;
	}
	
	public void setYSpeed(double dy) {
		ySpeed = dy;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(myImage, (int)x, (int)y ,null);
	}
}
