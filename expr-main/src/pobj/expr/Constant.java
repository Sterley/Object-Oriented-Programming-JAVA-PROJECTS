package pobj.expr;

public class Constant implements Expression {
	
	private int entier;
	
	public Constant(int entier) {
		this.entier = entier;
	}
	
	public Constant() {
		this.entier = 0;
	}
	
	public int getValue() {
		return this.entier;
	}
	
	public boolean equals(int e) {
		if(this.entier == e) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.entier);
		return b.toString();
	}

	
	@Override
	public <T> T accept(IVisitor<T> iv) {
		return iv.visit(this);
	}

	@Override
	public int eval() {
		return this.entier;
	}
	
}
