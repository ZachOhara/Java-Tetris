/* Shape.java | A collection of Block objects that make up a single
 * falling shape.
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

package io.github.zachoahra.javatetris.game;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

import javax.swing.JPanel;

public class Shape {

	private int xPos; //position of upper-left corner in the grid
	private int yPos;
	private int rotation; // the current rotation of the shape
	private int rotatability; // the possible rotations of the shape
	private int xCenter; // the center point of rotatation
	private int yCenter;
	private Block[][] blocks; // the actual matrix of blocks in this shape; always 4x4
	private JPanel masterPanel; // a reference to the containing panel

	public Shape(BlockTexture texture, boolean[][] placements, int xc, int yc, int r) {
		this.rotation = 0;
		this.rotatability = r;
		this.xCenter = xc;
		this.yCenter = yc;
		this.masterPanel = null;
		if (placements != null) {
			this.initBlocks(texture, placements);
			this.setGridPosition(3, -1);
		}
	}

	public Shape(Shape s) {
		this(null, null, s.xCenter, s.yCenter, s.rotatability);
		this.rotation = s.rotation;
		this.initBlocks(s.blocks);
		this.setGridPosition(3, -1);
	}

	public void setPanel(JPanel panel) {
		this.masterPanel = panel;
		for (Block[] bArr : blocks)
			for (Block b : bArr)
				if (b != null)
					this.masterPanel.add(b);
	}

	public Block[][] getBlockGrid() {
		return this.blocks;
	}

	public void rotate(int r) {
		int newR = this.rotation + r;
		while (newR < 0)
			newR += this.rotatability;
		newR %= this.rotatability;
		int dR =  newR - this.rotation;
		this.blocks = this.rotateBlockArray(dR);
		this.rotation = newR;
		this.updateBlockGridPositions();
	}

	public Block[][] testRotate(int r) {
		return this.rotateBlockArray(r);
	}

	public void translate(int dx, int dy) {
		for (Block[] bArr : this.blocks)
			for (Block b : bArr)
				if (b != null)
					b.translate(dx, dy);
		this.xPos += dx;
		this.yPos += dy;
	}

	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}

	private Block[][] rotateBlockArray(int d) {
		Block[][] result = new Block[4][4];
		// Signum of d is used often, so it's stored to make the lines simpler
		int sd = Integer.signum(d);
		if (sd == 0)
			return this.blocks; // don't rotate? done.
		// (xT, yT) is the post-rotation translation required to keep the shape centered properly
		int yT = this.xCenter - this.yCenter;
		int xT = this.xCenter + this.yCenter - 3;
		// xO and yO are constants (either 3 or 0) that are used to account for the
		// algorithm difference between clockwise and counter-clockwise rotation
		int xO = 0;
		int yO = 0;
		if (sd == -1) {
			yT--;
			xT++;
			xO = 3;
		} else {
			yO = 3;
		}
		int xN, yN;
		for (int r = 0; Math.abs(r) < Math.abs(d); r += sd)
			for (int i = 0; i < this.blocks.length; i++)
				for (int j = 0; j < this.blocks[i].length; j++) {
					xN = j + xT;
					yN = i + yT;
					if (0 <= yN && yN <= 3 && 0 <= xN && xN <= 3)
						result[yN][xN] = this.blocks[yO - (sd * j)][xO + (sd * i)];
				}
		return result;
	}

	private void setGridPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.updateBlockGridPositions();
	}

	private void updateBlockGridPositions() {
		for (int i = 0; i < this.blocks.length; i++)
			for (int j = 0; j < this.blocks[i].length; j++)
				if (this.blocks[i][j] != null)
					this.blocks[i][j].setGridPos(j + this.xPos, i + this.yPos);
	}

	private void initBlocks(BlockTexture texture, boolean[][] placements) {
		this.blocks = new Block[4][4];	
		for (int i = 0; i < placements.length; i++)
			for (int j = 0; j < placements[i].length; j++)
				if (placements[i][j])
					this.blocks[i][j] = new Block(texture);
	}

	private void initBlocks(Block[][] blockMatrix) {
		this.blocks = new Block[4][4];	
		for (int i = 0; i < this.blocks.length; i++)
			for (int j = 0; j < this.blocks[i].length; j++)
				if (blockMatrix[i][j] != null)
					this.blocks[i][j] = new Block(blockMatrix[i][j]);
	}

}
