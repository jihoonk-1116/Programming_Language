package cs316project;

class FunCondExp extends FunExp{
    Cond cond;

    FunCondExp(Cond c){
        cond = c;
    }
    void printParseTree(){
        IO.displayln(" <FunCondExp>");
        cond.printParseTree();
        
    }
}
