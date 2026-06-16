package it.finanze.siga.bean;

import java.util.HashMap;
import java.util.List;

public class BatchMailBean {

	public List<String> a;
	public List<String> cc;
	public String oggetto=null, template=null;
	public HashMap<String,String> param=null;
	
	public BatchMailBean(List<String> a, List<String> cc, String oggetto, String template, HashMap<String,String> param) {
		this.a=a;
		this.cc=cc;
		this.oggetto=oggetto;
		this.template=template;
		this.param=param;
	}
}
