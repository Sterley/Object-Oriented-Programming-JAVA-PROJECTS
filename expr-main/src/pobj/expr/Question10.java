package pobj.expr;

public class Question10 {
	// retourne vrai si e est un arbre d’expression constant
	public static boolean isConstant(Expression e) {
		VisitorConstant vc = new VisitorConstant();
		return e.accept(vc);
	}
	// retourne la valeur d’une expression constante
	// signale une exception si l’expression n’est pas constante
	public static int evalConstantExpression (Expression e) {
		if(isConstant(e) == true) {
			return e.eval();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}
	VisitorSimplify vs = new VisitorSimplify();
	Expression simple = Question4.e1().accept(vs);
}
