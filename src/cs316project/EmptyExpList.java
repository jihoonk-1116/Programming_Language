package cs316project;

class EmptyExpList extends ExpList{
    Exp exp;
    
    EmptyExpList(){
        exp = null;
    }
    void printParseTree(){
        IO.displayln(" <EmptyExp>");
    }
}