package cs316project;

import java.util.*;

class ClassDefEntry {
	String superClass;
	LinkedList<String> fields;
	HashMap<String, LinkedList<String>> funMap;
	HashMap<String, LinkedList<String>> funExpMap;
	HashMap<String, Val> state;

	
	ClassDefEntry(){
		superClass = "";
		fields = new LinkedList<String>();
		funMap = new HashMap<String, LinkedList<String>>();
		funExpMap = new HashMap<String, LinkedList<String>>();
		state = new HashMap<String, Val>();
//		params = new LinkedList<String>();
	}
	public void setSuperClass(String t) {
		superClass = t;
	}
	public void appendFields(String t) {
		fields.addLast(t);
	}
	public void setKey(String funName) {
		funMap.put(funName, new LinkedList<String>());
		funExpMap.put(funName, new LinkedList<String>());
	}
	public void mapFunc(String funName, String token) {
		if(!funMap.containsKey(funName)) {
			this.setKey(funName);
			//if the key is a new one, create new key to map
		}
		funMap.get(funName).addLast(token);
	}

	public String toString()
	{
		return "\nsuperclass=" + superClass + "\nfields=" + fields.toString() + "\nfunctions=" 
	+ funMap.toString() + "\nfuncExp=" + funExpMap.toString()+"\nState="+ state.toString() + "\n";
	}
}
