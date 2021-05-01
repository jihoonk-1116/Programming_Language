package cs316project;

class NE_FieldVarList extends FieldVarList{
    FieldVar fieldVar;
    FieldVarList fieldVarList;

    NE_FieldVarList(FieldVar fv, FieldVarList fvl){
        fieldVar = fv;
        fieldVarList = fvl;
    }
    void printParseTree(){
        IO.displayln(" <FieldVarList>");
        fieldVar.printParseTree();
        fieldVarList.printParseTree();
        
    }
}
