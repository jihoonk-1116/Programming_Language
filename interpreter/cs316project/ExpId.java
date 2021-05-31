package cs316project;

import java.util.HashMap;

class ExpId extends Exp{
    String id;

    ExpId(String t){
        id = t;
    }
    void printParseTree(){
        IO.displayln( id + " <ExpId>");
        
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	return null;
    };
}