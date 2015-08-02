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

package io.github.zachohara.javatetris.window;

import io.github.zachohara.javatetris.resource.texture.letter.LetterTexture;

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
		return TitleImage.letterWidthPix;
	}

	public static int getLetterheightpix() {
		return TitleImage.letterHeightPix;
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
		this.setSize(TitleImage.letterWidthPix * this.word.length(), TitleImage.letterHeightPix);
		JLabel jl;
		char[] letters = this.word.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			jl = LetterTexture.get(letters[i]).getTexture().getLabel();
			jl.setLocation(i * TitleImage.letterWidthPix, 0);
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
		ti.setLocation(0, 0);

		f.add(ti);
		f.setVisible(true);
	}

}
