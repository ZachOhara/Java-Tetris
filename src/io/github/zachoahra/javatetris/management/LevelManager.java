/* LevelManager.java | Responsible for keepint track of the player's
 * level, and for updating the display to reflect the level.
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
