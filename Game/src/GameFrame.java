import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.paint.Color;

public class GameFrame extends JFrame implements KeyListener {

	Drone drone = new Drone();
	Clock clock;
	JLayeredPane lpane;
	JPanel scoreboard;
	JPanel messages;
	PlanesLayerPanel planesPanel;
	PlainLayerPanel plainPanel;
	MoveableShape plain;
	JLabel backgroundlabel;
	JLabel droneLabel;
	JLabel penalty;
	JLabel startgame;
	boolean freeze;
	boolean run;

	public GameFrame() throws IOException {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		freeze = false;
		run = false;
		lpane = new JLayeredPane();
		clock = new Clock();
		scoreboard = new JPanel();

		// create background
		plainPanel = new PlainLayerPanel();
		plainPanel.setBounds(0, 0, 537, 340);
		plainPanel.setOpaque(false);

		// create the panel with planes and drones
		planesPanel = new PlanesLayerPanel();
		planesPanel.setLayout(new BorderLayout());
		planesPanel.setBounds(0, 0, 537, 340);
		planesPanel.setOpaque(false);

		droneLabel = new JLabel(new ShapeIcon(drone));
		planesPanel.add(droneLabel);

		// create a messages panel to display messages
		messages = new JPanel();
		messages.setOpaque(false);
		messages.setBounds(170, 120, 200, 100);
		penalty = new JLabel("PENALTY");
		startgame = new JLabel("PRESS SPACE");
		messages.add(penalty);
		messages.add(startgame);
		startgame.setVisible(false);
		penalty.setVisible(false);

		scoreboard.setLayout(new FlowLayout());
		scoreboard.add(clock.timelabel);
		scoreboard.add(clock.scorelabel);
		planesPanel.add(scoreboard, BorderLayout.NORTH);
		scoreboard.setOpaque(false);
		planesPanel.add(clock.collisionlabel, BorderLayout.SOUTH);

		lpane.setBounds(0, 0, 600, 400);
		lpane.add(plainPanel, 0, 0);
		lpane.add(planesPanel, 0, 0);
		lpane.add(messages, 0, 0);

		setPreferredSize(new Dimension(537, 370));
		setLayout(new BorderLayout());
		add(lpane);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public void freeze() {
		freeze = true;
	}

	public void defreeze() {
		freeze = false;
	}

	public void rungame() {
		if (run) {
			clock.runclock();
			Timer t2 = new javax.swing.Timer(1000, event -> {
				int freezeTime = clock.seconds;
				int counter = 0;
				for (Plane p : planesPanel.planes) {
					Area areaA = new Area(p.getShape());
					areaA.intersect(new Area(drone.getShape()));
					if (!areaA.isEmpty()) {
						p.collisionMove();
						clock.score.incColCount();
						break;
					}
				}
				if (clock.score.getColCount() >= 2) {
					freezeTime = counter;
					freeze();
				}

				if (freezeTime - counter == 5) {

					defreeze();
				}

				System.out.println(counter - freezeTime);
				System.out.println(freeze);
			});

			t2.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (!freeze && run) {
			// TODO Auto-generated method stub
			if (key.getKeyCode() == KeyEvent.VK_UP)
				drone.up();
			if (key.getKeyCode() == KeyEvent.VK_DOWN)
				drone.down();
			if (key.getKeyCode() == KeyEvent.VK_LEFT)
				drone.left();
			if (key.getKeyCode() == KeyEvent.VK_RIGHT)
				drone.right();
		}
		if (key.getKeyCode() == KeyEvent.VK_SPACE) {
			run = true;
			rungame();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) throws IOException {

		GameFrame gf = new GameFrame();
	}
}