package cs316project;

import java.util.HashMap;

class ExpInt extends Exp{
    int i;

    ExpInt(int t){
        i=t;
    }
    void printParseTree(){
        IO.displayln( i+" <ExpInt>");
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	return new IntVal(i);
    };
}
