package pobj.expr;

public class Mult extends BinOp implements Expression {
	
	public Mult(Expression left, Expression right) {
		super(left, right);
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(super.getLeft());
		b.append(" * ");
		b.append(super.getRight());
		return b.toString();
	}

	
	@Override
	public <T> T accept(IVisitor<T> iv) {
		return iv.visit(this);
	}

	@Override
	public int eval() {
		return super.getLeft().eval() * super.getRight().eval();
	}
	
}
