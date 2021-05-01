package cs316project;

class FunCallExp extends FunExp{
    FunCall funCall;

    FunCallExp(FunCall fc){
        funCall = fc;
    }
    void printParseTree(){
        IO.displayln(" <FunCallExp>");
        funCall.printParseTree();
    }
}
