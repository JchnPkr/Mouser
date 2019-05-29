package dance;

public class Step {
	private int x;
	private int y;
	
	public Step() {
		this.x = 0;
		this.y = 0;
	}
	
	public Step(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
