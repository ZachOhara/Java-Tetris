package io.github.zachoahra.javatetris.management;

public class TimeManager extends Thread {

	private LevelManager level;
	private GameManager game;
	
	private static final double fastFactor = 0.05 ;

	public TimeManager(LevelManager level, GameManager game) {
		super();
		this.level = level;
		this.game = game;
	}

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			int time = 0;
			try {
				if (this.game.isFastMode())
					time = (int)(level.getDropTicks() * fastFactor);
				else
					time = level.getDropTicks();
				Thread.sleep(time);
			} catch (InterruptedException expected) {}
			game.doGravity();
		}
	}

}
