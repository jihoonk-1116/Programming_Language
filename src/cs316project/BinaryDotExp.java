package cs316project;

class BinaryDotExp extends BinaryExp{
    DotExp dotExp;

    BinaryDotExp(DotExp de){
        dotExp = de;
    }
    void printParseTree(){
        IO.displayln(" <BinaryDotExp>");
        dotExp.printParseTree();
    
    }
}
