
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
   A plane that can be moved around.
*/
public class Plane implements MoveableShape
{ /**
    Constructs a plane item.
    @param x the left of the bounding rectangle
    @param y the top of the bounding rectangle
    @param width the width of the bounding rectangle
 */
 public Plane(int x, int y, int width)
 {
    this.x = x;
    temp = x;
    this.y = y;
    this.width = width;
    height = width*(4/3);
 }

 public void move()
 {
	 Random rand = new Random();
      x= x-4;
    //Resets the animation
    if(x<0)
    {
  	  x=600;
  	  y = rand.nextInt(400);
  	  
    }
 }
 
 public void collisionMove()
 {
	 Random rand = new Random();
    //Resets the animation
  	  x=600;
  	  y = rand.nextInt(400);
  	 
 }
 
	public void drawTopWing(Graphics2D g)
	{	
		g.setColor(Color.BLACK);
		g.drawPolygon(new int[] {x, x + width, x + width, x + width - width/3}, new int[] {y + height / 2 , y + height/2 -1, y, y},  4);
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[] {x, x + width, x + width, x + width - width/3}, new int[] {y + height / 2 , y + height/2- 1, y, y},  4);
	}

	public void drawBottomWing(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.drawPolygon(new int[] {x, x + width, x + width, x + width - width/3}, new int[] {y + height / 2, y + height/2 + 1, y+ height, y+ height},  4);
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[] {x, x + width, x + width, x + width - width/3}, new int[] {y + height / 2, y + height/2 + 1, y+ height, y+ height},  4);
	}

	//draws the airplane
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.BLACK);
		drawBottomWing(g2); // draw the back wing first
		
		//g2.setColor(Color.WHITE);
		drawTopWing(g2);
	}

 
  int x;
  int y;
 private int width;
 private int height;
 private int temp;
 private Shape shape;
 


@Override
public int height() {
	return width/3 + width/2 + width/2;
}

@Override
public int width() {
	return width;
}

public Shape getShape()
{
	return new Rectangle2D.Double(x,y-height(),width(),height);
}

}
