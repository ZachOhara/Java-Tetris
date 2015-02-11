package io.github.zachoahra.javatetris.management;

public class TimeManager extends Thread {

	private LevelManager level;
	private GameManager game;
	private boolean isFastMode;
	
	private static final double fastFactor = 0.1 ;

	public TimeManager(LevelManager level, GameManager game) {
		super();
		this.level = level;
		this.game = game;
		this.isFastMode = false;
	}

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			int time = 0;
			try {
				if (this.isFastMode())
					time = (int)(level.getDropTicks() * fastFactor);
				else
					time = level.getDropTicks();
				Thread.sleep(time);
			} catch (InterruptedException expected) {}
			game.doGravity();
		}
	}
	
	public boolean isFastMode() {
		return this.isFastMode;
	}
	
	public void setFastMode(boolean fastmode) {
		this.isFastMode = fastmode;
	}

}
