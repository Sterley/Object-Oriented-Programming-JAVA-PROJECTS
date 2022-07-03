package pobj.expr;

public class Add extends BinOp implements Expression {
	
	public Add(Expression left, Expression right) {
		super(left, right);
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append("( ");
		b.append(super.getLeft());
		b.append(" + ");
		b.append(super.getRight());
		b.append(" )");
		return b.toString();
	}
	

	@Override
	public <T> T accept(IVisitor<T> iv) {
		return iv.visit(this);
	}

	@Override
	public int eval() {
		return super.getLeft().eval() + super.getRight().eval();
	}
}
