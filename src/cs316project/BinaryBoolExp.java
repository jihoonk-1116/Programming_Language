package cs316project;

class BinaryBoolExp extends BinaryExp{
    BoolExp boolExp;

    BinaryBoolExp(BoolExp bo){
        boolExp = bo;
    }
    void printParseTree(){
        IO.displayln(" <BinaryBoolExp>");
        boolExp.printParseTree();
    }
}