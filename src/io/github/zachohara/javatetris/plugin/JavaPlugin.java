/* Copyright (C) 2015 Zach Ohara
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

package io.github.zachohara.javatetris.plugin;

import io.github.zachohara.javatetris.game.Block;
import io.github.zachohara.javatetris.game.BlockGrid;
import io.github.zachohara.javatetris.game.Shape;
import io.github.zachohara.javatetris.input.GameController;
import io.github.zachohara.javatetris.management.GameManager;

public abstract class JavaPlugin extends Thread implements GameController {

	private GameManager game;
	private boolean hasControl;

	public JavaPlugin() {
		this.game = null;
		this.hasControl = false;
	}

	@Override
	public boolean hasControl() {
		return this.hasControl;
	}

	@Override
	public void setControl(boolean b) {
		this.hasControl = b;
	}

	@Override
	public void setGame(GameManager game) {
		this.game = game;
	}

	public GameManager getGame() {
		return this.game;
	}

	public boolean canControlGame() {
		return this.game.isRunning() && this.hasControl();
	}

	public BlockGrid getBlockGrid() {
		return this.getGame().getBlockGrid();
	}

	public Block[][] getBlockGridArray() {
		return this.getBlockGrid().getBlockgridArray();
	}

	public Shape getCurrentShape() {
		return this.getGame().getBlockGrid().getCurrentShape();
	}

	public Block[][] getCurrentShapeArray() {
		return this.getCurrentShape().getBlockGrid();
	}

	public Shape getNextShape() {
		return this.getGame().getNextShapePanel().read();
	}

	public Block[][] getNextShapeArray() {
		return this.getNextShape().getBlockGrid();
	}

	public void shiftShape(int dir) {
		this.getBlockGrid().shiftShape(dir);
	}

	public void rotateShape(int dir) {
		this.getBlockGrid().rotateShape(dir);
	}

	public void hardDropShape() {
		this.getBlockGrid().hardDrop();
	}

}
