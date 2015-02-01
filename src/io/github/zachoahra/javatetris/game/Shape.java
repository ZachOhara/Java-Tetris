package io.github.zachoahra.javatetris.game;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

import javax.swing.JPanel;

public class Shape {

	private int xPos;
	private int yPos;
	private boolean falling;
	private int rotation;
	private int rotatability;
	private Block[][] blocks;
	private int xCenter;
	private int yCenter;
	private JPanel masterPanel;

	public Shape(BlockTexture texture, boolean[][] placements, int xc, int yc, int r) {
		this.falling = false;
		this.rotation = 0;
		this.rotatability = r;
		this.xCenter = xc;
		this.yCenter = yc;
		this.masterPanel = null;
		if (placements != null)
			this.initBlocks(texture, placements);
		this.setGridPosition(0, 3);
		this.updateBlockGridPositions();
	}

	public Shape(Shape s) {
		this(null, null, s.xCenter, s.yCenter, s.rotatability);
		this.rotation = s.rotation;
		this.falling = s.falling;
		this.blocks = s.blocks;
		this.masterPanel = s.masterPanel;
	}

	public void setPanel(JPanel panel) {
		this.masterPanel = panel;
		for (Block[] bArr : blocks)
			for (Block b : bArr)
				panel.add(b);
	}
	
	public void setGridPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.updateBlockGridPositions();
	}
	
	private void updateBlockGridPositions() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				blocks[i][j].setGridPos(j + this.xPos, i + this.yPos);
	}

	public Block[][] getBlockGrid() {
		return this.blocks;
	}

	public boolean[][] generatePlacementGrid() {		
		boolean[][] result = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.blocks[i][j] != null)
					result[i][j] = true;
				else
					result[i][j] = false;
			}
		}
		return result;
	}

	public void rotate(int r) {
		r = r % this.rotatability;
		int newR = this.rotation + r;
		while (newR < 0)
			newR += this.rotatability;
		newR %= this.rotatability;
		int dR =  newR - this.rotation;
		if (dR < 0)
			for (int i = 0; i > dR; i--)
				this.rotateBlockArrayCCW();
		else if (dR > 0)
			for (int i = 0; i < dR; i++)
				this.rotateBlockArrayCW();
		this.rotation = newR;
		this.updateBlockGridPositions();
	}

	private void rotateBlockArrayCCW() {
		Block[][] result = new Block[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int yN = i + xCenter - yCenter - 1;
				int xN = j + xCenter + yCenter - 2;
				if ( 0 <= yN && yN <= 3 && 0 <= xN && xN <= 3)
					result[yN][xN] = this.blocks[j][3-i];
			}
		this.blocks = result;
	}

	private void rotateBlockArrayCW() {
		Block[][] result = new Block[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int yN = i + xCenter - yCenter;
				int xN = j + xCenter + yCenter - 3;
				if ( 0 <= yN && yN <= 3 && 0 <= xN && xN <= 3)
					result[yN][xN] = this.blocks[3-j][i];
			}
		this.blocks = result;
	}

	private void initBlocks(BlockTexture texture, boolean[][] placements) {
		this.blocks = new Block[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				this.blocks[i][j] = null;
		for (int i = 0; i < placements.length; i++)
			for (int j = 0; j < placements[i].length; j++)
				if (placements[i][j])
					this.blocks[i][j] = new Block(texture);
	}

	public String toString() {
		String result = "";
		for (Block[] bArr : this.blocks) {
			for (Block b : bArr) {
				if (b == null)
					result += "-";
				else
					result += "X";
			}
			result += "\n";
		}
		return result;
	}	
	
	public static void main(String[] args) {
		Shape s = null;
		for (int i = 0; i <= 6; i++) {
			s = ShapeFactory.makeShapeByID(i);
			System.out.println("New Shape!");
			for (int j = 0; j < 4; j++) {
				System.out.println(s);
				s.rotate(1);
			}
			System.out.println("Change directions");
			for (int j = 0; j < 4; j++) {
				System.out.println(s);
				s.rotate(-1);
			}
		}
	}
	
	/*
	public static void main(String[] args) {
		Shape s = ShapeFactory.makeI();
		System.out.println(s);
		for (int i = 0; i < 3; i++) {
			s.rotate(1);
			System.out.println(s);
			s.rotate(-1);
			//s.rotate(-1);
			System.out.println(s);
		}
	}
	*/

}