
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * A plane that can be moved around.
 */
public class Plane implements MoveableShape {

	int x;
	int y;
	private int width;
	private int height;
	private int temp;
	private int scale = 4;

	/**
	 * Constructs a plane item.
	 * 
	 * @param x
	 *            the left of the bounding rectangle
	 * @param y
	 *            the top of the bounding rectangle
	 * @param width
	 *            the width of the bounding rectangle
	 */
	public Plane(int x, int y, int width) {
		this.x = x;
		temp = x;
		this.y = y;
		this.width = scale * 12;
		height = scale * 5;
	}

	public void move() {
		Random rand = new Random();
		x = x - 1;
		// Resets the animation
		if (x < 0) {
			x = 600;
			y = rand.nextInt(400);

		}
	}

	public void collisionMove() {
		Random rand = new Random();
		// Resets the animation
		x = 600;
		y = rand.nextInt(400);

	}

	//Main body of the plain
	public void body(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawPolygon(new int[] { x, (x + 2 * scale), x + 10 * scale, x + 10 * scale, x + scale },
				new int[] { (y + scale * 3), (y + 2 * scale), (y + 2 * scale), (y + scale * 4),(y + 4 * scale)  }, 5);
		g.fillPolygon(new int[] { x, (x + 2 * scale), x + 10 * scale, x + 10 * scale, x + scale },
				new int[] { (y + scale * 3), (y + 2 * scale), (y + 2 * scale), (y + scale * 4),(y + 4 * scale)  }, 5);
	}
	

	//Draws the tail of the plane
	public void tail(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawPolygon(new int[] { x + 9 * scale, x + 10 * scale, (x + 12 * scale), (x + 11 * scale) },
				new int[] { (y + scale * 2), (y + 4 * scale), (y + scale / 2), (y + scale / 2) }, 4);
		g.fillPolygon(new int[] { x + 9 * scale, x + 10 * scale, (x + 12 * scale), (x + 11 * scale) },
				new int[] { (y + scale * 2), (y + 4 * scale), (y + scale / 2), (y + scale / 2) }, 4);
	}

	//A wing to make the plane look like a plane
	public void bottomWing(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.drawPolygon(new int[] { x + 4 * scale, x + 7 * scale, (x + 17 * scale / 2), (x + 15 * scale / 2) },
				new int[] { (y + scale * 3), (y + 3 * scale), (y + scale * 6), (y + 6 * scale) }, 4);
		g.fillPolygon(new int[] { x + 4 * scale, x + 7 * scale, (x + 17 * scale / 2), (x + 15 * scale / 2) },
				new int[] { (y + scale * 3), (y + 3 * scale), (y + scale * 6), (y + 6 * scale) }, 4);
	}

	// A stripe of color at the bottom to make the plane look interesting.
	public void addStripe(Graphics2D g) {
		g.setColor(Color.getHSBColor(0.5f, 0.8f, 0.7f));
		g.drawPolygon(new int[] {  x + 19 * scale/2,x + scale/2, (x + scale), x + 10 * scale },
				new int[] { (y + 7 * scale/2),(y + scale * 7/2),  (y + 4 * scale), (y + scale * 4) }, 4);
		g.drawPolygon(new int[] { x + 19 * scale/2, x + 10 * scale, (x + 12 * scale), (x + 23 * scale/2) },
				new int[] { (y + scale *7/2 ), (y + 4 * scale), (y + scale / 2), (y + scale / 2) }, 4);
		
		g.fillPolygon(new int[] {  x + 19 * scale/2,x + scale/2, (x + scale), x + 10 * scale },
				new int[] { (y + 7 * scale/2),(y + scale * 7/2),  (y + 4 * scale), (y + scale * 4) }, 4);
		g.fillPolygon(new int[] { x + 19 * scale/2, x + 10 * scale, (x + 12 * scale), (x + 23 * scale/2) },
				new int[] { (y + scale *7/2 ), (y + 4 * scale), (y + scale / 2), (y + scale / 2) }, 4);
	}
	
	// draws the airplane
	public void draw(Graphics2D g) {
		body(g);
		tail(g);
		addStripe(g);
		bottomWing(g);
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public int width() {
		return width;
	}

	public Shape getShape() {
		return new Rectangle2D.Double(x, y, width, height);
	}

}
