package cs316project;

class FunDef{
    Header header;
    Exp exp;

    FunDef(Header h, Exp e){
        header = h;
        exp = e;
    }
    void printParseTree(){
        IO.displayln(" <FunDef>");
        header.printParseTree();
        exp.printParseTree();
    }
}