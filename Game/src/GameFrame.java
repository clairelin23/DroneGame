
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import javafx.scene.paint.Color;

public class GameFrame extends JFrame implements KeyListener {

	Drone drone = new Drone();
	Clock clock;
	JLayeredPane lpane;
	JPanel scoreboard;
	PlanesLayerPanel planesPanel;
	PlainLayerPanel plainPanel;
	MoveableShape plain;
	JLabel backgroundlabel;
	JLabel droneLabel;
	JLabel frozen;
	boolean freeze;

	public GameFrame() throws IOException {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		freeze = false;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (!freeze) {
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
	}

	public void freeze() {
		freeze = true;
		frozen.setVisible(true);
	}

	public void defreeze() {
		freeze = false;
		frozen.setVisible(false);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub

	}
}
