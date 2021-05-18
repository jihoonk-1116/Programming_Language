package cs316project;

import java.util.HashMap;

class BinaryBoolExp extends BinaryExp{
    BoolExp boolExp;

    BinaryBoolExp(BoolExp bo){
        boolExp = bo;
    }
    void printParseTree(){
        IO.displayln(" <BinaryBoolExp>");
        boolExp.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	
    	return boolExp.Eval(symbolTable);
    }
}