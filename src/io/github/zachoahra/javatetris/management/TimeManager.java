/* TimeManager.java | Responsible for timing and delays
 * Copyright (C) 2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
