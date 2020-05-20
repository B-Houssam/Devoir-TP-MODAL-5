package spec;

public class Formule {

	public double F1(int a, int b) {
		Arithmitique ar = new Arithmitique();
		return ar.Add(a, b)/ar.Mul(a, b);
	}
	
	public double F2(int c, int d) {
		Arithmitique ar = new Arithmitique();
		return ar.Div(c)/ar.Div(d);
	}
	
}
