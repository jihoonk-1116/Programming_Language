package cs316project;

class Not{
    Exp exp;

    Not(Exp e){
        exp = e;
    }
    void printParseTree(){
        IO.displayln(" <Not>");
        exp.printParseTree();
        
    }
}
