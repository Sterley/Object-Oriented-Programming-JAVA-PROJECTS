package pobj.expr;

public class VisitorConstant implements IVisitor<Boolean> {

	@Override
	public Boolean visit(Constant c) {
		return true;
	}

	@Override
	public Boolean visit(Add e) {
		VisitorConstant vc = new VisitorConstant();
		boolean l = e.getLeft().accept(vc);
		boolean r = e.getRight().accept(vc);
		if(l == true && r == true) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean visit(Mult e) {
		VisitorConstant vc = new VisitorConstant();
		boolean l = e.getLeft().accept(vc);
		boolean r = e.getRight().accept(vc);
		if(l == true && r == true) {
			return true;
		}
		return false;
		
	}

	@Override
	public Boolean visit(Var v) {
		return false;
	}


}
