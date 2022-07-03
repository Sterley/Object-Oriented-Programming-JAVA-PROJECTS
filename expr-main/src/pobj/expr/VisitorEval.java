package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {
	
	public Integer visit(Constant c) {
		return c.getValue();
	}
	
	public Integer visit(Var v) {
		throw new UnsupportedOperationException();
	}
		
	public Integer visit(Add a) {
		Integer e1 = a.getLeft().accept(this);
		Integer e2 = a.getRight().accept(this);
		return e1+e2;
	}
	
	public Integer visit(Mult a) {
		Integer e1 = a.getLeft().accept(this);
		Integer e2 = a.getRight().accept(this);
		return e1*e2;
	}
	
}
