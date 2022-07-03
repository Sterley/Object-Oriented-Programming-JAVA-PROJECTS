package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {
	
	@SuppressWarnings("unused")
	private Var x;
	
	public VisitorDerive(Var x) {
		this.x = x;
	}

	@Override
	public Expression visit(Constant c) {
		return new Constant(0);
	}

	@Override
	public Expression visit(Add e) {
		VisitorDerive vd = new VisitorDerive(this.x);
		return new Add(e.getLeft().accept(vd), e.getRight().accept(vd));
	}

	@Override
	public Expression visit(Mult e) {
		VisitorDerive vd = new VisitorDerive(this.x);
		return new Add(new Mult(e.getLeft(), e.getRight().accept(vd)), new Mult(e.getLeft().accept(vd), e.getRight()));
	}

	@Override
	public Expression visit(Var v) {
		if(v.equals(this.x)) {
			return new Constant(1);
		}
		return new Constant(0);
	}
	
}
