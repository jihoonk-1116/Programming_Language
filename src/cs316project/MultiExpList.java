package cs316project;

class MultiExpList extends ExpList{
    Exp exp;
    ExpList expList;

    MultiExpList(Exp e, ExpList el){
        exp = e;
        expList = el;
    }
    void printParseTree(){
        IO.displayln(" <MultiExpList>");
        exp.printParseTree();
        expList.printParseTree();
    }
}
