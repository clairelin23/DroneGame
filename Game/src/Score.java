
public class Score {

	int score;
	int collisionCount;

	public Score() {
		score = 0;
		collisionCount = 0;
	}

	public void incScore() {
		if (collisionCount < 2)
			score++;
	}

	public int getColCount() {
		return collisionCount;
	}

	public void incColCount() {
		collisionCount++;
	}

	public void resetColCount() {
		// TODO Auto-generated method stub
		collisionCount = 0;
	}

}
