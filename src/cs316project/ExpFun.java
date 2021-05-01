package cs316project;

class ExpFun extends Exp{
    FunExp funExp;

    ExpFun(FunExp fe){
        funExp = fe;
    }
    void printParseTree(){
        IO.displayln(" <ExpFun>");
        funExp.printParseTree();
       
    }
}