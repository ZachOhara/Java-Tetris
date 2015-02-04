package io.github.zachoahra.javatetris.window;

import io.github.zachoahra.javatetris.resource.texture.letter.LetterTexture;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleImage extends JPanel {

	private String word;
	
	private static final int letterWidthPix = 70;
	private static final int letterHeightPix = 90;

	private static final long serialVersionUID = 1L;
	
	public TitleImage(String s) {
		super();
		this.setLayout(null);
		this.word = s;
		this.update();
	}
	
	public TitleImage() {
		this("");
	}

	public static int getLetterwidthpix() {
		return letterWidthPix;
	}

	public static int getLetterheightpix() {
		return letterHeightPix;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String s) {
		this.word = s;
		this.update();
	}
	
	private void update() {
		this.removeAll();
		this.setSize(letterWidthPix * this.word.length(), letterHeightPix);
		JLabel jl;
		char[] letters = this.word.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			jl = LetterTexture.get(letters[i]).getTexture().getLabel();
			jl.setLocation(i * letterWidthPix, 0);
			this.add(jl);
		}
		this.revalidate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setSize(800, 200);
		TitleImage ti = new TitleImage("hello");
		ti.setLocation(0,0);
		
		f.add(ti);
		f.setVisible(true);
	}

}
