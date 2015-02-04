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