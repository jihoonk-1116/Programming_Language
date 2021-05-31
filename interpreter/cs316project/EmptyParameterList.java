package cs316project;

class EmptyParameterList extends ParameterList{
    Parameter p;

    EmptyParameterList(){
        p = null;
    }
    void printParseTree(){
        IO.displayln(" <EmptyParameterList>");
    }
}
