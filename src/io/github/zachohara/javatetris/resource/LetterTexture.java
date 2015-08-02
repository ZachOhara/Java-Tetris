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

package io.github.zachohara.javatetris.resource;

import io.github.zachohara.javatetris.window.TitleImage;

public enum LetterTexture {

	A, B, C, D, E, F, G,
	H, I, J, K, L, M, N,
	O, P, Q, R, S, T, U,
	V, W, X, Y, Z;

	private Texture texture;
	
	private static final String LETTER_TEXTURE_FOLDER = "letter";
	
	@SuppressWarnings("deprecation")
	private LetterTexture() {
		this.texture = new Texture(LETTER_TEXTURE_FOLDER, this.name().toLowerCase(),
				TitleImage.getLetterwidthpix(), TitleImage.getLetterheightpix());
	}

	public Texture getTexture() {
		return this.texture;
	}

	public static LetterTexture get(String s) {
		return LetterTexture.get(s.toCharArray()[0]);
	}

	public static LetterTexture get(char c) {
		c = Character.toLowerCase(c);
		switch (c) {
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
