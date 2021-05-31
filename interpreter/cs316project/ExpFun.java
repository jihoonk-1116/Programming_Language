package cs316project;

import java.util.HashMap;

class ExpFun extends Exp{
    FunExp funExp;

    ExpFun(FunExp fe){
        funExp = fe;
    }
    void printParseTree(){
        IO.displayln(" <ExpFun>");
        funExp.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTables) {
    	return funExp.Eval(symbolTables);
    };
}