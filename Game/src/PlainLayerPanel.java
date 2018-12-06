
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PlainLayerPanel extends JPanel
{
	int width;
	int height;
	private MoveableShape plain;
	private static final int D_W = 600;
    private static final int D_H = 400;

    ArrayList<Plane> planes;
    public PlainLayerPanel() 
    {
    		plain = new Background();
    	
    		final int DELAY = 100;
    		// Milliseconds between timer ticks
    		// Milliseconds between timer ticks
    		Timer t1 = new javax.swing.Timer(DELAY, event -> {
    			plain.move();
    			repaint();

    		});

    		t1.start();
    }
    @Override
    protected void paintComponent(Graphics g) 
    {
      	Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
            plain.draw(g2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(D_W, D_H);
    }

}
