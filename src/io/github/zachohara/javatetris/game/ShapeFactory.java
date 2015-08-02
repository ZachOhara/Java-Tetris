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

package io.github.zachohara.javatetris.game;

import io.github.zachohara.javatetris.resource.texture.block.BlockTexture;

public abstract class ShapeFactory {

	private static final boolean[][] Ibool = { {}, {true, true, true, true}};
	private static final boolean[][] Obool = { {}, {true, true}, {true, true}};
	private static final boolean[][] Tbool = { {}, {true, true, true}, {false, true}};
	private static final boolean[][] Sbool = { {}, {false, true, true}, {true, true}};
	private static final boolean[][] SRbool = { {}, {true, true}, {false, true, true}};
	private static final boolean[][] Lbool = { {}, {true, true, true}, {true}};
	private static final boolean[][] LRbool = { {}, {true, true, true}, {false, false, true}};

	private static final Shape I = new Shape(BlockTexture.BLUE, ShapeFactory.Ibool, 1, 1, 2);
	private static final Shape O = new Shape(BlockTexture.YELLOW, ShapeFactory.Obool, 0, 0, 1);
	private static final Shape T = new Shape(BlockTexture.PURPLE, ShapeFactory.Tbool, 1, 1, 4);
	private static final Shape S = new Shape(BlockTexture.GREEN, ShapeFactory.Sbool, 1, 1, 2);
	private static final Shape SR = new Shape(BlockTexture.RED, ShapeFactory.SRbool, 1, 1, 2);
	private static final Shape L = new Shape(BlockTexture.ORANGE, ShapeFactory.Lbool, 1, 1, 4);
	private static final Shape LR = new Shape(BlockTexture.PINK, ShapeFactory.LRbool, 1, 1, 4);

	public static Shape makeRandomShape() {
		return ShapeFactory.makeShapeByID((int) (Math.random() * 7));
	}

	public static Shape makeShapeByID(int id) {
		switch (id) {
			case 0:
				return ShapeFactory.makeI();
			case 1:
				return ShapeFactory.makeO();
			case 2:
				return ShapeFactory.makeT();
			case 3:
				return ShapeFactory.makeS();
			case 4:
				return ShapeFactory.makeSR();
			case 5:
				return ShapeFactory.makeL();
			case 6:
				return ShapeFactory.makeLR();
			default:
				return null;
		}
	}

	public static Shape makeI() {
		return new Shape(ShapeFactory.I);
	}

	public static Shape makeO() {
		return new Shape(ShapeFactory.O);
	}

	public static Shape makeT() {
		return new Shape(ShapeFactory.T);
	}

	public static Shape makeS() {
		return new Shape(ShapeFactory.S);
	}

	public static Shape makeSR() {
		return new Shape(ShapeFactory.SR);
	}

	public static Shape makeL() {
		return new Shape(ShapeFactory.L);
	}

	public static Shape makeLR() {
		return new Shape(ShapeFactory.LR);
	}

}
