package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		VisitorSimplify vs = new VisitorSimplify();
		Expression l = e.getLeft().accept(vs);
		Expression r = e.getRight().accept(vs);
		
		VisitorConstant vc = new VisitorConstant();
		boolean l2 = e.getLeft().accept(vc);
		boolean r2 = e.getRight().accept(vc);
		
		if(l2 == true) {
			if(l.eval() == 0) {
				return r;
			}
		}
		if(r2 ==  true) {
			if(r.eval() == 0) {
				return l;
			}
		}
		
		if(l2 == true && r2 == true) {
			return new Constant(l.eval() + r.eval());
		}
		
		return new Add(l, r);
	}

	@Override
	public Expression visit(Mult e) {
		
		VisitorSimplify vs = new VisitorSimplify();
		Expression l = e.getLeft().accept(vs);
		Expression r = e.getRight().accept(vs);
		
		VisitorConstant vc = new VisitorConstant();
		boolean l2 = e.getLeft().accept(vc);
		boolean r2 = e.getRight().accept(vc);
		
		if(l2 == true) {
			if(l.eval() == 1) {
				return r;
			}
			if(l.eval() == 0) {
				return l;
			}
		}
		
		if(r2 == true) {
			if(r.eval() == 1) {
				return l;
			}
			if(r.eval() == 0) {
				return r;
			}
		}

		if(l2 == true && r2 == true) {
			return new Constant(l.eval() * r.eval());
		}
		
		return new Mult(l, r);
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
