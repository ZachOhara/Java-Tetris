package io.github.zachoahra.javatetris.management;

import java.awt.Dimension;

import javax.swing.JPanel;

import io.github.zachoahra.javatetris.score.NumberImage;
import io.github.zachoahra.javatetris.window.GameWindow;
import io.github.zachoahra.javatetris.window.TitleImage;

public class ScoreManager extends JPanel {

	private GameWindow masterWindow;
	
	private TitleImage scoreTitle;
	private TitleImage linesTitle;
	private TitleImage levelTitle;
	private NumberImage scoreCounter;
	private NumberImage linesCounter;
	private NumberImage levelCounter;
	
	private static final int xPos = 525;
	private static final int yPos = 0;
	
	private static final long serialVersionUID = 1L;
	
	public ScoreManager(GameWindow g) {
		super();
		
		this.setLayout(null);
		this.setLocation(xPos, yPos);
		
		this.masterWindow = g;
		this.scoreTitle = new TitleImage("score");
		this.linesTitle = new TitleImage("lines");
		this.levelTitle = new TitleImage("level");
		this.scoreCounter = new NumberImage();
		this.linesCounter = new NumberImage();
		this.levelCounter = new NumberImage();
		
		int height = 0;
		int width = 0;
		Dimension d;
		
		this.scoreTitle.setLocation(0, height);
		this.add(this.scoreTitle);
		d = this.scoreTitle.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.scoreCounter.setLocation(0, height);
		this.add(this.scoreCounter);
		d = this.scoreCounter.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.linesTitle.setLocation(0, height);
		this.add(this.linesTitle);
		d = this.linesTitle.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.linesCounter.setLocation(0, height);
		this.add(this.linesCounter);
		d = this.linesCounter.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.levelTitle.setLocation(0, height);
		this.add(this.levelTitle);
		d = this.levelTitle.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.levelCounter.setLocation(0, height);
		this.add(this.levelCounter);
		d = this.levelCounter.getSize();
		height += d.getHeight();
		width = (int) Math.max(width, d.getWidth());
		
		this.scoreCounter.setLeftAlign(width);
		this.linesCounter.setLeftAlign(width);
		this.levelCounter.setLeftAlign(width);
		
		this.setSize(width, height);
		this.masterWindow.add(this);
	}
	
	public void addScore(int points) {
		this.scoreCounter.changeNumber(points);
	}
	
	public void addLines(int lines) {
		this.linesCounter.changeNumber(lines);
	}
	
	public void addLevel(int levels) {
		this.levelCounter.changeNumber(levels);
	}
	
	public void setLevel(int level) {
		this.levelCounter.setNumber(level);
	}

}
