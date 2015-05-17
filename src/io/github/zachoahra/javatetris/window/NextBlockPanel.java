/* NextBlockPanel.java | A JPanel to show the next upcoming block next
 * to the main play area.
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

package io.github.zachoahra.javatetris.window;

import io.github.zachoahra.javatetris.game.Block;
import io.github.zachoahra.javatetris.game.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NextBlockPanel extends JPanel {

	private Shape nextShape = null;

	private static final int xPos = 650;
	private static final int yPos = 650;

	private static final long serialVersionUID = 1L;

	public NextBlockPanel(JFrame window) {
		super();
		this.setLayout(null);
		this.setLocation(xPos, yPos);
		this.setSize(getLength(), getLength());
		window.add(this);
	}

	public Shape retrieve() {
		this.removeAll();
		this.update();
		this.nextShape.translate(3, 0);
		Shape temp = this.nextShape;
		this.nextShape = null;
		return temp;
	}

	public Shape read() {
		return this.nextShape;
	}

	public void store(Shape nextShape) {
		this.nextShape = nextShape;
		this.nextShape.translate(-3, 0);
		this.nextShape.setPanel(this);
		this.update();
	}

	public boolean shapeIsNull() {
		return this.nextShape == null;
	}

	private void update() {
		this.revalidate();
		this.repaint();
	}

	public static int getLength() {
		return Block.getLength() * 4;
	}

}
