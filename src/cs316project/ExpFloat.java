package cs316project;

class ExpFloat extends Exp{
    float f;

    ExpFloat(float t){
        f=t;
    }
    void printParseTree(){
        IO.displayln(f+" <ExpF>");
     
    }
}
