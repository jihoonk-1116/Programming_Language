package cs316project;

import java.util.HashMap;

class BinaryDotExp extends BinaryExp{
    DotExp dotExp;

    BinaryDotExp(DotExp de){
        dotExp = de;
    }
    void printParseTree(){
        IO.displayln(" <BinaryDotExp>");
        dotExp.printParseTree();
    
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	return dotExp.Eval(symbolTable);
    }
}
