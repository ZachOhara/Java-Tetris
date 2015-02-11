package io.github.zachoahra.javatetris.management;

public class LevelManager {
	
	private int level;
	private int dropSpeed; // in frames per line @ 60fps
	private int linesToNext; // ...level
	
	private static final int[] linePoints = {0, 40, 100, 300, 1200};
	
	public LevelManager() {
		this.level = 0;
		this.dropSpeed = 48;
	}
	
	public void levelUp() {
		this.level++;
		if (this.level <= 8)
			this.dropSpeed -= 5;
		else if (this.level == 9)
			this.dropSpeed = 6;
		else if (this.level <= 12)
			this.dropSpeed = 5;
		else if (this.level <= 15)
			this.dropSpeed = 4;
		else if (this.level <= 18)
			this.dropSpeed = 3;
		else if (this.level <= 28)
			this.dropSpeed = 2;
		else
			this.dropSpeed = 1;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getLinePoints(int lines) {
		if (lines < 0 || lines > 4)
			return 0;
		return linePoints[lines] * (this.level + 1);
	}
	
	public void clearLines(int lines) {
		this.linesToNext += lines;
		while (this.linesToNext > 9) {
			this.linesToNext -= 10;
			this.levelUp();
		}
	}
	
	public int getDropTicks() {
		return (int)(((double)this.dropSpeed / 60) * 1000);
	}
	
	public int getDropFrames() {
		return this.dropSpeed;
	}

}
