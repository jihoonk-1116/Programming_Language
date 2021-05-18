package cs316project;

import java.util.HashMap;

class ArithExp{
	String operator;
    Exp exp;
    Exp exp2;

    ArithExp(String op, Exp e, Exp e2){
    	operator = op;
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <ArithExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    
    	Val e = exp.Eval(symbolTable);
		Val e2 = exp2.Eval(symbolTable);
		
		if(e instanceof ErrorVal) return e;
		if(e2 instanceof ErrorVal) return e2;
		if(e instanceof BoolVal || e2 instanceof BoolVal) {
			String m = operator +" operator cannot be applied to true";
		    return new ErrorVal(m); 
		}
    	if(operator == "Add") {
    		if(e instanceof FloatVal && e2 instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val + ((FloatVal)e2).val;
        		return e;
    		}
    		else if(e instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val + e2.floatVal();
        		return e;
    		}
    		else if(e2 instanceof FloatVal) {
    			((FloatVal)e2).val = e.floatVal() + ((FloatVal)e2).val;
        		return e2;
    		}
    		else {
    			((IntVal)e).val = ((IntVal)e).val + ((IntVal)e2).val;
        		return e;
    		}
    		
    	}
    	else if(operator == "Sub") {
    		if(e instanceof FloatVal && e2 instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val - ((FloatVal)e2).val;
        		return e;
    		}
    		else if(e instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val - e2.floatVal();
        		return e;
    		}
    		else if(e2 instanceof FloatVal) {
    			((FloatVal)e2).val = e.floatVal() - ((FloatVal)e2).val;
        		return e2;
    		}
    		else {
    			if(!(e instanceof IntVal) || !(e2 instanceof IntVal)) {
    				return null;
    			}
    			((IntVal)e).val = ((IntVal)e).val - ((IntVal)e2).val;
        		return e;
    		}
    		
    	}
    	else if(operator == "Div") {
    		if(e2.floatVal() == 0) {
    			String m =" integer division by 0";
    		    return new ErrorVal(m); 
    		}
    		if(e instanceof FloatVal && e2 instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val / ((FloatVal)e2).val;
        		return e;
    		}
    		else if(e instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val / e2.floatVal();
        		return e;
    		}
    		else if(e2 instanceof FloatVal) {
    			((FloatVal)e2).val = e.floatVal() / ((FloatVal)e2).val;
        		return e2;
    		}
    		else {
    			((IntVal)e).val = ((IntVal)e).val / ((IntVal)e2).val;
        		return e;
    		}
    	}
    	else if(operator == "Mul") {
    		if(e instanceof FloatVal && e2 instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val * ((FloatVal)e2).val;
        		return e;
    		}
    		else if(e instanceof FloatVal) {
    			((FloatVal)e).val = ((FloatVal)e).val * e2.floatVal();
        		return e;
    		}
    		else if(e2 instanceof FloatVal) {
    			((FloatVal)e2).val = e.floatVal() * ((FloatVal)e2).val;
        		return e2;
    		}
    		else {
    			if(!(e instanceof IntVal)) {
    				return new ErrorVal("variable " + e.toString() + "does not have a value");
    			}
    			if(!(e2 instanceof IntVal)) {
    				return new ErrorVal("variable " + e2.toString() + "does not have a value");
    			}
    			
    			((IntVal)e).val = ((IntVal)e).val * ((IntVal)e2).val;
        		return e;
    		}
    	}
    	return null;
    }
}