package cs316project;

class DotExp{
    Exp exp;
    Exp exp2;

    DotExp(Exp e, Exp e2){
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <DotExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
}