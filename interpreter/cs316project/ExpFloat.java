package cs316project;

import java.util.HashMap;

class ExpFloat extends Exp{
    float f;

    ExpFloat(float t){
        f=t;
    }
    void printParseTree(){
        IO.displayln(f+" <ExpF>");
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	Val fl = new FloatVal(f);
    	return fl;
    };
}
