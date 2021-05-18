package cs316project;

import java.util.HashMap;

class BoolExp{
	String op;
    Exp exp;
    Exp exp2;

    BoolExp(String t, Exp e, Exp e2){
    	op = t;
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <boolExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    
    	Val e = exp.Eval(symbolTable);
    	Val e2 = exp2.Eval(symbolTable);
    	String m="";
    	if(e == null || e == null) {
    		return new BoolVal(true); 
    	}
    	if(e instanceof ErrorVal || e2 instanceof ErrorVal) {
    		if(e instanceof ErrorVal)
    			return e;
    		else
    			return e2;
    	}
    	if(e instanceof BoolVal && e2 instanceof BoolVal) {
    		if(op == "Or") {
    			System.out.println(e.toString() + " " + e2.toString());
        		if(((BoolVal)e).val || ((BoolVal)e2).val) {
        			return new BoolVal(true);
        		}
        		return new BoolVal(false);
        	}
        	else if(op == "And") {
        		if(((BoolVal)e).val && ((BoolVal)e2).val) {
        			return new BoolVal(true);
        		}
        		return new BoolVal(false);
        	}
        	
    	}
    	if(!(e instanceof BoolVal)) {
    		m = op +" operator cannot be applied to " + e.toString() + "\n";	
    	}else if(!(e2 instanceof BoolVal)) {
    		m = op +" operator cannot be applied to " + e2.toString()+ "\n";	
    	}
    	return new ErrorVal(m);
    }
}