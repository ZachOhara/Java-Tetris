/* Block.java | A single colored block in the game environment
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

import java.awt.Point;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Block extends JPanel {
	
	private BlockTexture texture;

	private static int lengthPixels = 50;
	
	private static final long serialVersionUID = 1L;
	
	public Block(BlockTexture texture) {
		super();
		this.texture = texture;
		this.setSize(lengthPixels, lengthPixels);
		JLabel textureLabel = this.texture.getTexture().getLabel();
		textureLabel.setLocation(0, 0);
		textureLabel.setSize(lengthPixels, lengthPixels);
		this.setLayout(null);
		this.add(textureLabel);
	}
	
	public Block(Block other) {
		this(other.texture);
	}
	
	public static int getLength() {
		return lengthPixels;
	}
	
	public static void setLength(int length) {
		lengthPixels = length;
	}
	
	public void setGridPos(int x, int y) {
		this.setLocation(x * lengthPixels, y * lengthPixels);
	}
	
	public void translate(int dx, int dy) {
		Point p = this.getLocation();
		p.x += dx * lengthPixels;
		p.y += dy * lengthPixels;
		this.setLocation(p);
	}
	
	public String toString() {
		return this.texture.toString();
	}
	
}
