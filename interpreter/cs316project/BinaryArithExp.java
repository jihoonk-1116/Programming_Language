package cs316project;

import java.util.HashMap;

class BinaryArithExp extends BinaryExp{
    ArithExp arithExp;

    BinaryArithExp(ArithExp ae){
        arithExp = ae;
    }
    void printParseTree(){
        IO.displayln(" <BinaryArithExp>");
        arithExp.printParseTree();
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	return arithExp.Eval(symbolTable);
    }
}
