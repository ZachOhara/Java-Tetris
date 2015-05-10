/* GameManager.java | Responsible for managing the whole game, mostly by
 * creating and exchanging information between sub-managers.
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

import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.game.ShapeFactory;
import io.github.zachoahra.javatetris.input.GameController;
import io.github.zachoahra.javatetris.window.GameWindow;
import io.github.zachoahra.javatetris.window.NextBlockPanel;

public class GameManager {

	private GameWindow masterWindow;
	private LevelManager levelManager;
	private ScoreManager scoreManager;
	private NextBlockPanel nextShapePanel;
	private GameController currentController;

	private BlockGrid gameGrid;

	private boolean isRunning;
	private boolean isPaused;

	public GameManager(GameWindow master, LevelManager level, ScoreManager score, NextBlockPanel nextPanel) {
		this.masterWindow = master;
		this.levelManager = level;
		this.scoreManager = score;
		this.nextShapePanel = nextPanel;

		this.gameGrid = new BlockGrid(master);
	}

	public boolean isRunning() {
		return this.isRunning;
	}
	
	public boolean isPaused() {
		return this.isPaused;
	}
	
	public boolean isActive() {
		return !this.isPaused && this.isRunning;
	}

	public BlockGrid getBlockGrid() {
		return this.gameGrid;
	}

	public NextBlockPanel getNextShapePanel() {
		return this.nextShapePanel;
	}

	public void setController(GameController gc) {
		if (gc != null) {
			if (this.currentController != null)
				this.currentController.setControl(false);
			this.currentController = gc;
			this.currentController.setGame(this);
			this.currentController.setControl(true);
			this.currentController.start();
		}
	}

	public void startGame() {
		this.isRunning = true;
		this.spawnNewShape();
	}
	
	public void setPaused(boolean b) {
		this.isPaused = b;
	}
	
	public void doGravity() {
		if (this.isActive() && !(this.gameGrid.descendShape())) {
			this.gameGrid.anchorShape();
			this.checkLinesCleared();
			this.spawnNewShape();
			if (!this.gameGrid.isShapeViable()) {
				System.out.println("GAME OVER");
				this.isRunning = false;
				this.masterWindow.endGame();
			}
		}
	}

	private void checkLinesCleared() {
		int[] lines = this.gameGrid.getFullLines();
		this.gameGrid.removeAll(lines);
		this.scoreManager.addLines(lines.length);
		this.scoreManager.addScore(this.levelManager.getLinePoints(lines.length));
		this.levelManager.clearLines(lines.length);
		this.scoreManager.setLevel(this.levelManager.getLevel());
	}

	private void spawnNewShape() {
		this.nextShapePanel.removeAll();
		if (!this.nextShapePanel.shapeIsNull())
			this.gameGrid.setShape(this.nextShapePanel.retrieve());
		else
			this.gameGrid.setShape(ShapeFactory.makeRandomShape());
		this.nextShapePanel.store(ShapeFactory.makeRandomShape());
	}

}
