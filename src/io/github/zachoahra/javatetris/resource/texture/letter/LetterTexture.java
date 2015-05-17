/* LetterTexture.java | A Texture object for any possible letter in the
 * English alphabet.
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

package io.github.zachoahra.javatetris.resource.texture.letter;

import io.github.zachoahra.javatetris.resource.texture.Texture;
import io.github.zachoahra.javatetris.window.TitleImage;

public enum LetterTexture {

	A ("a.png"),
	B ("b.png"),
	C ("c.png"),
	D ("d.png"),
	E ("e.png"),
	F ("f.png"),
	G ("g.png"),
	H ("h.png"),
	I ("i.png"),
	J ("j.png"),
	K ("k.png"),
	L ("l.png"),
	M ("m.png"),
	N ("n.png"),
	O ("o.png"),
	P ("p.png"),
	Q ("q.png"),
	R ("r.png"),
	S ("s.png"),
	T ("t.png"),
	U ("u.png"),
	V ("v.png"),
	W ("w.png"),
	X ("x.png"),
	Y ("y.png"),
	Z ("z.png");

	private Texture texture;

	private LetterTexture(String filename) {
		this.texture = new Texture(filename, TitleImage.getLetterwidthpix(), TitleImage.getLetterheightpix(), this.getClass());
	}

	public Texture getTexture() {
		return this.texture;
	}

	public static LetterTexture get(String s) {
		return get(s.toCharArray()[0]);
	}

	public static LetterTexture get(char c) {
		c = Character.toLowerCase(c);
		switch(c) {
		case 'a':
			return A;
		case 'b':
			return B;
		case 'c':
			return C;
		case 'd':
			return D;
		case 'e':
			return E;
		case 'f':
			return F;
		case 'g':
			return G;
		case 'h':
			return H;
		case 'i':
			return I;
		case 'j':
			return J;
		case 'k':
			return K;
		case 'l':
			return L;
		case 'm':
			return M;
		case 'n':
			return N;
		case 'o':
			return O;
		case 'p':
			return P;
		case 'q':
			return Q;
		case 'r':
			return R;
		case 's':
			return S;
		case 't':
			return T;
		case 'u':
			return U;
		case 'v':
			return V;
		case 'w':
			return W;
		case 'x':
			return X;
		case 'y':
			return Y;
		case 'z':
			return Z;
		default:
			return null;
		}
	}

}