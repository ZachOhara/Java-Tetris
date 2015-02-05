package io.github.zachoahra.javatetris.game;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

public abstract class ShapeFactory {

	private static final boolean[][] Ibool = {
		{},
		{true, true, true, true}
	};
	private static final boolean[][] Obool = {
		{},
		{true, true},
		{true, true}
	};
	private static final boolean[][] Tbool = {
		{},
		{true, true, true},
		{false, true}
	};
	private static final boolean[][] Sbool = {
		{},
		{false, true, true},
		{true, true}
	};
	private static final boolean[][] SRbool = {
		{},
		{true, true},
		{false, true, true}
	};
	private static final boolean[][] Lbool = {
		{},
		{true, true, true},
		{true}
	};
	private static final boolean[][] LRbool = {
		{},
		{true, true, true},
		{false, false, true}
	};

	private static final Shape I = new Shape(BlockTexture.BLUE, Ibool, 1, 1, 2);
	private static final Shape O = new Shape(BlockTexture.YELLOW, Obool, 0, 0, 1);
	private static final Shape T = new Shape(BlockTexture.PURPLE, Tbool, 1, 1, 4);
	private static final Shape S = new Shape(BlockTexture.GREEN, Sbool, 1, 1, 2);
	private static final Shape SR = new Shape(BlockTexture.RED, SRbool, 1, 1, 2);
	private static final Shape L = new Shape(BlockTexture.ORANGE, Lbool, 1, 1, 4);
	private static final Shape LR = new Shape(BlockTexture.PINK, LRbool, 1, 1, 4);

	public static Shape makeRandomShape() {
		return makeShapeByID((int)(Math.random() * 7));
	}
	
	public static Shape makeShapeByID(int id) {
		switch(id) {
		case 0:
			return makeI();
		case 1:
			return makeO();
		case 2:
			return makeT();
		case 3:
			return makeS();
		case 4:
			return makeSR();
		case 5:
			return makeL();
		case 6:
			return makeLR();
		default:
			return null;
		}
	}

	public static Shape makeI() {
		return new Shape(I);
	}

	public static Shape makeO() {
		return new Shape(O);
	}

	public static Shape makeT() {
		return new Shape(T);
	}

	public static Shape makeS() {
		return new Shape(S);
	}

	public static Shape makeSR() {
		return new Shape(SR);
	}

	public static Shape makeL() {
		return new Shape(L);
	}

	public static Shape makeLR() {
		return new Shape(LR);
	}

}
