package cs316project;

class Header{
    FunName funName;
    ParameterList pl;

    Header(FunName f, ParameterList p){
        funName = f;
        pl = p;
    }
    void printParseTree(){
        IO.displayln(" <Header>");
        funName.printParseTree();
        pl.printParseTree();
    }
}
