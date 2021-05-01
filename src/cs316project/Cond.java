package cs316project;

class Cond{
    Exp exp;
    Exp exp2;
    Exp exp3;

    Cond(Exp e, Exp e2, Exp e3){
        exp = e;
        exp2 = e2;
        exp3 = e3;
    }
    void printParseTree(){
        IO.displayln(" <Cond>");
        exp.printParseTree();
        exp2.printParseTree();
        exp3.printParseTree();
    }
}
