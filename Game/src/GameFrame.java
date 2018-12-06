import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JFrame implements KeyListener {

	Drone drone;
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

		// Add key listener
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		// declare the booleans false
		freeze = false;
		run = false;

		// the layered pane is to allow multiple panels to overlap
		lpane = new JLayeredPane();
		clock = new Clock();

		// create background
		plainPanel = new PlainLayerPanel();
		plainPanel.setBounds(0, 0, 537, 340);
		plainPanel.setOpaque(false);

		// create the panel with planes and drones
		planesPanel = new PlanesLayerPanel();
		planesPanel.setLayout(new BorderLayout());
		planesPanel.setBounds(0, 0, 537, 340);
		planesPanel.setOpaque(false);

		// create drone and add to panel
		drone = new Drone();
		droneLabel = new JLabel(new ShapeIcon(drone));
		planesPanel.add(droneLabel);

		// create a messages panel to display messages
		messages = new JPanel();
		messages.setOpaque(false);
		penalty = new JLabel("PENALTY ");
		startgame = new JLabel("PRESS SPACEBAR TO BEGIN");
		messages.setBounds(150, 190, 240, 140);
		penalty.setFont(new Font("SansSerif", Font.ITALIC, 24));
		penalty.setForeground(Color.WHITE);
		startgame.setForeground(Color.WHITE);
		messages.add(penalty);
		messages.add(startgame);
		startgame.setVisible(true);
		penalty.setVisible(false);

		// create a scoreboard panel to display score and stopwatch
		scoreboard = new JPanel();
		scoreboard.setLayout(new FlowLayout());
		scoreboard.add(clock.timelabel);
		scoreboard.add(clock.scorelabel);
		planesPanel.add(scoreboard, BorderLayout.NORTH);
		scoreboard.setOpaque(false);
		// add collisions count to bottom of screen
		planesPanel.add(clock.collisionlabel, BorderLayout.SOUTH);

		// add multiple panels to layered pane
		lpane.setBounds(0, 0, 537, 350);
		lpane.add(plainPanel, 0, 0);
		lpane.add(planesPanel, 0, 0);
		lpane.add(messages, 0, 0);

		setTitle("Drone Game");
		setPreferredSize(new Dimension(537, 362));
		setResizable(false);
		setLayout(new BorderLayout());
		add(lpane);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	/**
	 * Sets the freeze boolean to true and displays penalty message
	 */
	public void freeze() {
		freeze = true;
		penalty.setVisible(true);

	}

	/**
	 * Defreezes resets freeze to false and removes penalty message 
	 */
	public void defreeze() {
		freeze = false;
		penalty.setVisible(false);
	}

	/**
	 * Runs the game when spacebar is pressed by starting the clock and checking for
	 * collisions
	 */
	public void rungame() {
		if (run) {	//Checks if run is true
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
					freezeTime = counter;  //sets freezetime to counter
					freeze();
				}

				if (freezeTime - counter == 5) { //Waits for freeze time counter to become 5

					defreeze();
				}
			});

			t2.start();  //starts the timer 
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
			startgame.setVisible(false);
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
		new GameFrame();
	}
}