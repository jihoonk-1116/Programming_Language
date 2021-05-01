package cs316project;

class BinaryArithExp extends BinaryExp{
    ArithExp arithExp;

    BinaryArithExp(ArithExp ae){
        arithExp = ae;
    }
    void printParseTree(){
        IO.displayln(" <BinaryArithExp>");
        arithExp.printParseTree();
        
    }
}
