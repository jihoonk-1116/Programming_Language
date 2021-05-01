package cs316project;

class CompExp{
    Exp exp;
    Exp exp2;

    CompExp(Exp e, Exp e2){
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <CompExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
}
