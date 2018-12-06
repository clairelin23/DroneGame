

import java.awt.Graphics2D;

/**
A shape that can be moved around.
 */
public interface MoveableShape 
{
	/**
	Draws the shape.
	@param g2 the graphics context	
    */
	void draw(Graphics2D g2);	

   /** 
	Moves the shape.	
	It is up to the shape to move itself, for example by tracking the time
	its last movement, its position, and velocity.
    */
	void move();
	
	int height();
	
	int width();
}
