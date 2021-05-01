package cs316project;

class FunNotExp extends FunExp{
    Not not;

    FunNotExp(Not n){
        not = n;
    }
    void printParseTree(){
        IO.displayln(" <Not>");
        not.printParseTree();
        
    }
}
