package cs316project;

class FunBinaryExp extends FunExp{
    BinaryExp binaryExp;
    
    FunBinaryExp(BinaryExp be){
        binaryExp = be;
    }
    void printParseTree(){
        IO.displayln(" <FunBinaryExp>");
        binaryExp.printParseTree();
    }
}
