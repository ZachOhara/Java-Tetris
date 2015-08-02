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

package io.github.zachohara.javatetris.game;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlockGrid extends JPanel {

	private Shape currentShape;
	private Block[][] blockgrid;

	private static final int xPos = 0;
	private static final int yPos = 0;
	private static final int gridWidth = 10;
	private static final int gridHeight = 17;

	private static final long serialVersionUID = 1L;

	public BlockGrid(JFrame window) {
		super();
		this.blockgrid = new Block[BlockGrid.gridHeight][BlockGrid.gridWidth];
		this.setSize(BlockGrid.getStaticSize());
		this.setLocation(BlockGrid.xPos, BlockGrid.yPos);
		this.setLayout(null);
		window.add(this);
	}

	public Block[][] getBlockgridArray() {
		return this.blockgrid;
	}

	public Shape getCurrentShape() {
		return this.currentShape;
	}

	public void setShape(Shape s) {
		this.currentShape = null;
		this.currentShape = s;
		s.setPanel(this);
		this.update();
	}

	public boolean descendShape() {
		if (this.currentShape != null) {
			if (this.isTranslationViable(0, 1)) {
				this.currentShape.translate(0, 1);
			} else {
				return false;
			}
			this.update();
			return true;
		} else {
			return false;
		}
	}

	public boolean shiftShape(int d) {
		if (this.currentShape != null) {
			if (this.isTranslationViable(d, 0)) {
				this.currentShape.translate(d, 0);
			} else {
				return false;
			}
			this.update();
			return true;
		} else {
			return false;
		}
	}

	public boolean rotateShape(int d) {
		if (this.currentShape != null) {
			if (this.isTranslationViable(this.currentShape.testRotate(d), 0, 0)) {
				this.currentShape.rotate(d);
			} else {
				return false;
			}
			this.update();
			return true;
		} else {
			return false;
		}
	}

	public void hardDrop() {
		if (this.currentShape != null) {
			int i = 0;
			while (this.isTranslationViable(0, i)) {
				i++;
			}
			this.currentShape.translate(0, i - 1);
			this.anchorShape();
			this.currentShape = null;
		}
	}

	public void anchorShape() {
		if (this.currentShape != null) {
			Block[][] grid = this.currentShape.getBlockGrid();
			int x = this.currentShape.getXPos();
			int y = this.currentShape.getYPos();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (grid[i][j] != null) {
						this.blockgrid[i + y][j + x] = grid[i][j];
					}
				}
			}
			this.update();
		}
	}

	public boolean isShapeViable() {
		if (this.currentShape == null) {
			return true;
		} else {
			return this.isTranslationViable(0, 0);
		}
	}

	private boolean isTranslationViable(int dx, int dy) {
		return this.isTranslationViable(this.currentShape.getBlockGrid(), dx, dy);
	}

	private boolean isTranslationViable(Block[][] prospective, int dx, int dy) {
		int x = dx + this.currentShape.getXPos();
		int y = dy + this.currentShape.getYPos();
		int xN, yN; // the 'new' coordinates of elements
		for (int i = 0; i < prospective.length; i++) {
			yN = i + y;
			for (int j = 0; j < prospective[i].length; j++) {
				if (prospective[i][j] != null) {
					xN = j + x;
					// if new coordinate is out of range (don't check for y < 0)
					if ( !(yN < BlockGrid.gridHeight) || !(0 <= xN && xN < BlockGrid.gridWidth)) {
						return false;
					}
					// if prospective space is alread occupied (check for y<0 here)
					if (yN >= 0 && this.blockgrid[yN][xN] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void removeAll(int[] lines) {
		int correction = 0;
		for (int i : lines) {
			this.clearLine(i + correction);
			correction++;
		}
		this.update();
	}

	private void clearLine(int l) {
		for (int i = 0; i < BlockGrid.gridWidth; i++) {
			if (this.blockgrid[l][i] != null) {
				this.remove(this.blockgrid[l][i]);
			}
			this.blockgrid[l][i] = null;
		}
		for (int i = l - 1; i >= 0; i--) {
			for (int j = 0; j < BlockGrid.gridWidth; j++) {
				if (this.blockgrid[i][j] != null) {
					this.blockgrid[i][j].translate(0, 1);
				}
				this.blockgrid[i + 1][j] = this.blockgrid[i][j];
			}
		}
	}

	public int[] getFullLines() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = BlockGrid.gridHeight - 1; i >= 0; i--) {
			if (this.lineIsFull(i)) {
				list.add(i);
			}
		}
		Integer[] temp = list.toArray(new Integer[list.size()]);
		return BlockGrid.toIntArray(temp);
	}

	private boolean lineIsFull(int l) {
		for (Block b : this.blockgrid[l]) {
			if (b == null) {
				return false;
			}
		}
		return true;
	}

	private void update() {
		this.revalidate();
		this.repaint();
	}

	private static int[] toIntArray(Integer[] intArr) {
		int[] result = new int[intArr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = intArr[i];
		}
		return result;
	}

	public static Dimension getStaticSize() {
		return new Dimension(BlockGrid.getStaticWidth(), BlockGrid.getStaticHeight());
	}

	public static int getStaticWidth() {
		return Block.getLength() * BlockGrid.gridWidth;
	}

	public static int getStaticHeight() {
		return Block.getLength() * BlockGrid.gridHeight;
	}

}
