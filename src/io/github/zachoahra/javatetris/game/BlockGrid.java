package io.github.zachoahra.javatetris.game;

import java.util.LinkedList;

import javax.swing.JPanel;

public class BlockGrid extends JPanel {

	private int width;
	private int height;
	private Shape currentShape;
	private Block[][] blockgrid;
	
	private static final long serialVersionUID = 1L;
	
	public BlockGrid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.blockgrid = new Block[height][width];
		this.setSize(width * Block.getLength(), height * Block.getLength());
		this.setLayout(null);
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
		if (this.isTranslationViable(0, 1))
			this.currentShape.translate(0, 1);
		else
			return false;
		this.update();
		return true;
	}
	
	public boolean shiftShape(int d) {
		if (this.isTranslationViable(d, 0))
			this.currentShape.translate(d, 0);
		else
			return false;
		this.update();
		return true;
	}
	
	public boolean rotateShape(int d) {
		if (this.isTranslationViable(this.currentShape.testRotate(d), 0, 0))
			this.currentShape.rotate(d);
		else
			return false;
		this.update();
		return true;
	}
	
	public void hardDrop() {
		int i = 0;
		while (this.isTranslationViable(0, i))
			i++;
		this.currentShape.translate(0, i - 1);
		this.anchorShape();
	}
	
	public void anchorShape() {
		Block[][] grid = this.currentShape.getBlockGrid();
		int x = this.currentShape.getXPos();
		int y = this.currentShape.getYPos();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (grid[i][j] != null)
					this.blockgrid[i+y][j+x] = grid[i][j];
		this.update();
	}
	
	public boolean isShapeViable() {
		return this.isTranslationViable(0, 0);
	}
	
	private boolean isTranslationViable(int dx, int dy) {
		return this.isTranslationViable(this.currentShape.getBlockGrid(), dx, dy);
	}
	
	private boolean isTranslationViable(Block[][] prospective, int dx, int dy) {
		int x = dx + this.currentShape.getXPos();
		int y = dy + this.currentShape.getYPos();
		int xN, yN; //the 'new' coordinates of elements
		for (int i = 0; i < prospective.length; i++) {
			yN = i + y;
			for (int j = 0; j < prospective[i].length; j++)
				if (prospective[i][j] != null) {
					xN = j + x;
					// if new coordinate is out of range (don't check for y < 0)
					if (!(yN < this.height) || !(0 <= xN && xN < this.width))
						return false;
					// if prospective space is alread occupied (check for y<0 here)
					if (yN >= 0 && this.blockgrid[yN][xN] != null)
						return false;
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
		for (int i = 0; i < this.width; i++) {
			if (this.blockgrid[l][i] != null)
				this.remove(this.blockgrid[l][i]);
			this.blockgrid[l][i] = null;
		}
		for (int i = l - 1; i >= 0; i--) {
			for (int j = 0; j < this.width; j++) {
				if (this.blockgrid[i][j] != null)
					this.blockgrid[i][j].translate(0, 1);
				this.blockgrid[i + 1][j] = this.blockgrid[i][j];
			}
		}
	}
	
	public int[] getFullLines() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = this.height - 1; i >= 0; i--) {
			if (this.lineIsFull(i))
				list.add(i);
		}
		Integer[] temp = list.toArray(new Integer[list.size()]);
		return toIntArray(temp);
	}
	
	private boolean lineIsFull(int l) {
		for (Block b : this.blockgrid[l])
			if (b == null)
				return false;
		return true;
	}
	
	private void update() {
		this.revalidate();
		this.repaint();
	}
	
	private static int[] toIntArray(Integer[] intArr) {
		int[] result = new int[intArr.length];
		for (int i = 0; i < result.length; i++)
			result[i] = intArr[i];
		return result;
	}

}
