package cs316project;

class BinaryCompExp extends BinaryExp{
    CompExp compExp;

    BinaryCompExp(CompExp cp){
        compExp = cp;
    }
    void printParseTree(){
        IO.displayln(" <BinaryCompExp>");
        compExp.printParseTree();
    }
}
