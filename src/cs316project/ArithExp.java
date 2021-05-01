package cs316project;

class ArithExp{
    Exp exp;
    Exp exp2;

    ArithExp(Exp e, Exp e2){
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <ArithExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
}