package it.finanze.siga.workflow.core;

import java.util.HashMap;

import jakarta.mail.internet.InternetAddress;

public class WorkFlowMailBean {
	public InternetAddress[] a=null, cc=null;
	public String oggetto=null, template=null;
	public HashMap<String,String> param=null;
	
	public WorkFlowMailBean(InternetAddress[] a, InternetAddress[] cc, String oggetto, String template, HashMap<String,String> param) {
		this.a=a;
		this.cc=cc;
		this.oggetto=oggetto;
		this.template=template;
		this.param=param;
	}
}
