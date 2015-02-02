package io.github.zachoahra.javatetris.management;

public class LevelManager {
	
	private int level;
	
	public LevelManager(int level) {
		this.level = level;
	}
	
	public LevelManager() {
		this(0);
	}
	
	public void levelUp() {
		this.level++;
	}

}
