package cs316project;

class ErrorVal extends Val{
	String msg;
	
	ErrorVal(String m){
		msg = "Error: ";
		msg += m;
	}
	String getMsg() {
		return msg;
	}
	void setMsg(String m) {
		msg = m;
	}
	public String toString() {
		return msg;
	}
	Val cloneVal() {return null;}
	float floatVal() {return 0;} // conversion to floating-point
	boolean isNumber() {return false;}
	boolean isZero() {return false;}
}
