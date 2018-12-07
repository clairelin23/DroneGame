
	
	import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


	public class PlanesLayerPanel extends JPanel 
	{
	    private static final int D_W = 600;
	    private static final int D_H = 400;
	    public final int planecount = 7;
	    ArrayList<Plane> planes;
	    public PlanesLayerPanel() 
	    {
	    		planes = new ArrayList<>();
	    		Random rand1 = new Random();
	    		Random rand2 = new Random();
	    	for (int i=0;i<planecount;i++)
	    	{  
	        planes.add(new Plane(rand2.nextInt(500)+500,rand2.nextInt(340), 20));     
	    	}
	    	
	        Timer t = new javax.swing.Timer(30, event -> 
			{
	                for (Plane x : planes) 
	                {
	                    x.move();
	                    repaint();
	                }
	            });
	        t.start();
	    }

	    @Override
	    protected void paintComponent(Graphics g) 
	    {
	      	Graphics2D g2 = (Graphics2D) g;
	        super.paintComponent(g);
	        for (Plane x : planes) {
	            x.draw(g2);
	        }
	    }

	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(D_W, D_H);
	    }
	   
	}

