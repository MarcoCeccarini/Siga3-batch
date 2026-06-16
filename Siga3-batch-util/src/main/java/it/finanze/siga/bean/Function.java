package it.finanze.siga.bean;

/**
 * Una funzione specificata sia come int che come String
 */
public class Function {
	
	private int functionINT;
	private String functionString;
	
	public Function(int functionINT, String functionString) {
		super();
		this.functionINT = functionINT;
		this.functionString = functionString;
	}
	public int getFunctionINT() {
		return functionINT;
	}
	public void setFunctionINT(int functionINT) {
		this.functionINT = functionINT;
	}
	public String getFunctionString() {
		return functionString;
	}
	public void setFunctionString(String functionString) {
		this.functionString = functionString;
	}
	
	

}
