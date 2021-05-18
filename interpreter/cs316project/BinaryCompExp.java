package cs316project;

import java.util.HashMap;

class BinaryCompExp extends BinaryExp{
    CompExp compExp;

    BinaryCompExp(CompExp cp){
        compExp = cp;
    }
    void printParseTree(){
        IO.displayln(" <BinaryCompExp>");
        compExp.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	return compExp.Eval(symbolTable);
    }
}
