import java.awt.Graphics;
import java.awt.Image;

public class Sprite2D {

		//member data 
	protected double x,y;
	protected double xSpeed = 0;
	protected double ySpeed = 0;
	protected double width = 0;
	protected double height = 0;
	protected Image myImage, myImage2;
	int framesDrawn = 0;
	int winWidth;
	
	public Sprite2D(Image i,Image i2, int windowWidth) {
		this.myImage = i; //declaring image to i
		this.myImage2 = i2;
		this.winWidth = windowWidth;
		this.width = 0;
		this.height = 0;
	}
	
	public void setPostion(double xx, double yy) {
		x = xx;
		y = yy;
	}
	
	public void setImageWidthAndHeight(double newW, double newH) {
		this.width = newW;
		this.height = newH;	
	}
	
	public void setXSpeed(double dx) {
		xSpeed = dx;
	}
	
	public void setYSpeed(double dy) {
		ySpeed = dy;
	}
	
	public void paint(Graphics g) {
		framesDrawn++;
		if(framesDrawn%100<50) {
		g.drawImage(myImage, (int)x, (int)y ,null);
		}
		else {
			g.drawImage(myImage2, (int)x, (int)y ,null);
			}
		}
	}
