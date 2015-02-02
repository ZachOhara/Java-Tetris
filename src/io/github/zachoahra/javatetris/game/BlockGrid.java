package io.github.zachoahra.javatetris.game;

import javax.swing.JFrame;
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
		this.setSize(width * Block.getLength(), (int)((height + Block.getCorrection()) * Block.getLength()));
	}
	
	public void setShape(Shape s) {
		this.currentShape = null;
		this.currentShape = s;
		s.setPanel(this);
		this.update();
	}
	
	public void descendShape() {
		this.currentShape.descend(1);
		this.update();
	}
	
	public void anchorShape() {
		this.currentShape.anchor();
		Block[][] grid = this.currentShape.getBlockGrid();
		int x = this.currentShape.getXPos();
		int y = this.currentShape.getYPos();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] != null)
					this.blockgrid[i+y][j+x] = grid[i][j];
			}
		}
		this.update();
	}
	
	public boolean canShapeDescend() {
		Block[][] shapeGrid = this.currentShape.getBlockGrid();
		int x = this.currentShape.getXPos();
		int y = this.currentShape.getYPos();
		y++;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i + y >= this.height && shapeGrid[i][j] != null)
					return false;
				if (i + y >= 0 && shapeGrid[i][j] != null && this.blockgrid[i+y][j+x] != null)
					return false;
			}
		}
		return true;
	}
	
	private void update() {
		this.revalidate();
		this.repaint();
	}
	
	public String toString() {
		String result = "";
		for (Block[] bArr : this.blockgrid) {
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
	
	public static void main(String[] args) throws InterruptedException {		
		JFrame f = new JFrame();
		f.setSize(500, 900);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BlockGrid g = new BlockGrid(10, 17);
		g.setLayout(null);
		g.setLocation(0, 0);
		f.add(g);
		
		g.setShape(ShapeFactory.makeRandomShape());
		g.repaint();
		f.repaint();
		//System.out.println(g);
		
		f.setVisible(true);
		//System.exit(0);
		while (true) {
			//System.out.println("point A");
			while (g.canShapeDescend()) {
				Thread.sleep(300);
				g.descendShape();
				f.revalidate();
				f.repaint();			
			}
			g.anchorShape();
			System.out.println("Can't descend!");
			//System.out.println(g);
			g.setShape(ShapeFactory.makeRandomShape());
		}
		
	}

}
