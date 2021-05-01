package cs316project;

class ExpInt extends Exp{
    int i;

    ExpInt(int t){
        i=t;
    }
    void printParseTree(){
        IO.displayln( i+" <ExpInt>");
    }
}
