package cs316project;

class FunCall{
    FunName funName;
    ExpList expList;

    FunCall(FunName fn, ExpList el){
        funName = fn;
        expList = el;
    }
    void printParseTree(){
        IO.displayln(" <FunCall>");
        funName.printParseTree();
        expList.printParseTree();
    }
}
