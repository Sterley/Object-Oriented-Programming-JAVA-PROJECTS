package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval {
	
	private Map<String,Integer> env;
	
	public VisitorEvalVar(Map<String,Integer> env) {
		this.env = env;
	}
	
	public Integer visit(Var v) {
		if(this.env.containsKey(v.getName())) {
			return this.env.get(v.getName());
		}
		throw new UnsupportedOperationException();
	}
	
}
