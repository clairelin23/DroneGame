
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Drone implements MoveableShape {
	BufferedImage drone; // image of drone
	int x; // bounding rectangle's x
	int y; // bounding rectangle's y

	public Drone() {
		x = 30;
		y = 230;
		try {
			File file = new File(getClass().getResource("/Images/Drone.png").getFile());
			drone = ImageIO.read(file);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.drawImage(drone.getScaledInstance(60, 50, 0), x, y, null);
	}

	@Override
	public void move() {
	}

	@Override
	public int height() {
		return 50;
	}

	@Override
	public int width() {
		return 60;
	}

	public void up() {
		y = y - 5;
	}

	public void down() {
		y = y + 5;
	}

	public void left() {
		x = x - 5;
	}

	public void right() {
		x = x + 5;
	}
	
	public Shape getShape()
	{
		return new Rectangle2D.Double(x,y,60,50);
	}
}