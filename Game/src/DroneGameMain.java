import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DroneGameMain {

	public static void main(String[] args) throws IOException {
		
		JLabel droneLabel;
		GameFrame gf = new GameFrame();
		
		// create main layered pane and panels
		gf.lpane = new JLayeredPane();
		gf.clock = new Clock();
		gf.scoreboard = new JPanel();

		// create background
		gf.plainPanel = new PlainLayerPanel();
		gf.plainPanel.setBounds(0, 0, 537, 340);
		gf.plainPanel.setOpaque(false);

		//create the panel with planes and drones
		gf.planesPanel = new PlanesLayerPanel();
		gf.planesPanel.setLayout(new BorderLayout());
		gf.planesPanel.setBounds(0, 0, 537, 340);
		gf.planesPanel.setOpaque(false);

		droneLabel = new JLabel(new ShapeIcon(gf.drone));
		gf.planesPanel.add(droneLabel);
		
		//create a messages panel to display messages
		gf.messages = new JPanel();
		gf.messages.setOpaque(false);
		gf.messages.setBounds(170, 120, 200, 100);
		gf.penalty  = new JLabel("PENALTY");
		gf.startgame = new JLabel("PRESS SPACE");
		gf.messages.add(gf.penalty);
		gf.messages.add(gf.startgame);
		gf.startgame.setVisible(false);
		gf.penalty.setVisible(false);
		
		gf.scoreboard.setLayout(new FlowLayout());
		gf.scoreboard.add(gf.clock.timelabel);
		gf.scoreboard.add(gf.clock.scorelabel);
		gf.planesPanel.add(gf.scoreboard, BorderLayout.NORTH);
		gf.scoreboard.setOpaque(false);
		gf.planesPanel.add(gf.clock.collisionlabel, BorderLayout.SOUTH);

		gf.lpane.setBounds(0, 0, 600, 400);
		gf.lpane.add(gf.plainPanel, 0, 0);
		gf.lpane.add(gf.planesPanel, 0, 0);
		gf.lpane.add(gf.messages,0,0);

		gf.setPreferredSize(new Dimension(537, 370));
		gf.setLayout(new BorderLayout());
		gf.add(gf.lpane);
		gf.setLocationRelativeTo(null);
		gf.pack();
		gf.setVisible(true);

		Timer t2 = new javax.swing.Timer(1000, event -> 
		{
			int freezeTime=gf.clock.seconds;
			int counter =0;
			for (Plane p : gf.planesPanel.planes) 
			{
				Area areaA = new Area(p.getShape());
				  areaA.intersect(new Area(gf.drone.getShape()));
				  if (!areaA.isEmpty())
				  {
			        p.collisionMove();
					gf.clock.score.incColCount();
					break;
				  }
			}  
			if (gf.clock.score.getColCount()>=2)
			{
				 freezeTime =counter; 
				 gf.freeze();	  
			 }
				  
		    if (freezeTime-  counter  == 5)
		    {
			
					    gf.defreeze();
			 }
      	     
      	     System.out.println(counter - freezeTime);
      	     System.out.println(gf.freeze);
		});

		t2.start();
	
	}
}