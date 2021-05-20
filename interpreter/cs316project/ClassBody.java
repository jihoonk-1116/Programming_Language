package cs316project;

class ClassBody{
    FieldVarList fieldVarList;
    FunDefList funDefList;

    ClassBody(FieldVarList fvl, FunDefList fdl){
        fieldVarList = fvl;
        funDefList = fdl;
    }
    void printParseTree(){
        IO.displayln(" <ClassBody>");
        fieldVarList.printParseTree();
        funDefList.printParseTree();
    }
}