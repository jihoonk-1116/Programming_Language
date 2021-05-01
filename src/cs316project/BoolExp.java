package cs316project;

class BoolExp{
    Exp exp;
    Exp exp2;

    BoolExp(Exp e, Exp e2){
        exp = e;
        exp2 = e2;
    }
    void printParseTree(){
        IO.displayln(" <boolExp>");
        exp.printParseTree();
        exp2.printParseTree();
    }
}