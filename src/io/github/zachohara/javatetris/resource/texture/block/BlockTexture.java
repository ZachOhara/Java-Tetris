/* BlockTexture.java | A set of Textures for the available block colors.
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

package io.github.zachohara.javatetris.resource.texture.block;

import io.github.zachohara.javatetris.game.Block;
import io.github.zachohara.javatetris.resource.texture.Texture;

public enum BlockTexture {

	BLUE ("blue.png"),
	GREEN ("green.png"),
	ORANGE ("orange.png"),
	PINK ("pink.png"),
	PURPLE ("purple.png"),
	RED ("red.png"),
	YELLOW ("yellow.png");

	private Texture texture;

	private BlockTexture(String filename) {
		this.texture = new Texture(filename, Block.getLength(), Block.getLength(), this.getClass());
	}

	public Texture getTexture() {
		return this.texture;
	}

}
