package cs316project;

class MultiParameterList extends ParameterList{
    Parameter parameter;
    ParameterList parameterList;

    MultiParameterList(Parameter p , ParameterList pl){
        parameter = p;
        parameterList = pl;
    }
    void printParseTree(){
        IO.displayln(" <MultiParameterList>");
        parameter.printParseTree();
        parameterList.printParseTree();
    }
}
