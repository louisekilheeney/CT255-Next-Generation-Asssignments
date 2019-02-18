import java.awt.*;

public class GameObject {
	
	//member data
	private int x,y;
	private Color c;
	
	
	//constructor declaring colour and x, y 
	public GameObject() {	
		//rand colour
			int red =  (int)(Math.random()*256);
			int green =  (int)(Math.random()*256);
			int blue =  (int)(Math.random()*256);
			c = new Color(red,green,blue);
			
			x = (int)(Math.random()*600);
			y = (int)(Math.random()*600);
		}

	//public interface  which moves the sqaures when called from the movingSqauresApplication.java file.
	public void move() {
		x = x+ (int)((Math.random()-0.5)*10);
		y = y+ (int)((Math.random()-0.5)*10);
	}
	
	//sets colours and fills the boxs with the colour 
	public void paint(Graphics g) {
				g.setColor(c);
				g.fillRect(x-20, y-20, 40, 40);
			}
	}
