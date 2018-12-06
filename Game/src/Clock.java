
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class Clock {
	JLabel timelabel;
	JLabel scorelabel;
	JLabel collisionlabel;
	TimerTask timertask;
	int seconds;
	int minutes;
	int timeInMilliSecs;
	Timer timer;
	Score score;
	
	boolean collisionDetected;

	public Clock() {

		seconds = 0;
		minutes = 0;
		timeInMilliSecs = 0;
		collisionDetected = false;
		timer = new Timer();
		score = new Score();

		scorelabel = new JLabel("Score is " + score.score);
		timelabel = new JLabel(minutes + ":" + seconds);
		collisionlabel = new JLabel("Collision count: "+ score.collisionCount);
		// The timer task is a task that is meant to be run every 100
		// miliseconds
		timertask = new TimerTask() {

			@Override
			public void run() {
				timeInMilliSecs++;
				System.out.println("seconds" + timeInMilliSecs);
				timelabel.repaint();
				minutes = timeInMilliSecs / 600;
				seconds = (int) timeInMilliSecs % 600;
		
				if (score.getColCount() >= 2) {
					resetTimer();
					System.out.println("collisionDetected" + score.collisionCount);
					score.resetColCount();
					timelabel.repaint();
				}

				if (timeInMilliSecs == 900) {
					resetTimer();
					score.incScore();
					score.resetColCount();
					scorelabel.repaint();
				}

				timelabel.setText(minutes + ":" + seconds);
				scorelabel.setText("Score is: " + score.score);
				collisionlabel.setText("Collision count: "+ score.collisionCount);
			}
		};

		timer.scheduleAtFixedRate(timertask, 100, 1000);
	}

	public void resetTimer() {
		seconds = 0;
		minutes = 0;
		timeInMilliSecs = 0;
	}

}