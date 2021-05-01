package cs316project;

class NE_FunDefList extends FunDefList{
    FunDef funDef;
    FunDefList funDefList;

    NE_FunDefList(FunDef fd, FunDefList fdl){
        funDef = fd;
        funDefList = fdl;
    }
    void printParseTree(){
        IO.displayln(" <FunDefList>");
        funDef.printParseTree();
        funDefList.printParseTree();
    }
}
