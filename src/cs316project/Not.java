package cs316project;

import java.util.HashMap;

class Not{
    Exp exp;

    Not(Exp e){
        exp = e;
    }
    void printParseTree(){
        IO.displayln(" <Not>");
        exp.printParseTree();
        
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	Val e = exp.Eval(symbolTable);
    	
    	if(e instanceof BoolVal) {
    		if(((BoolVal) e).val == true) {
    			((BoolVal) e).val = false;
    			return e;
    		}
    		((BoolVal) e).val = true;
    		return e;
    	}
    	return null;
    }
}
