package it.finanze.siga.util.tree;

import java.io.Serializable;
import java.util.List;

/**
 * per il fancy tree di profili.
 * 
 * Esempio che si vuole ottenere trasformando in json:
 * 
String jsonTree_4 = 
	"[{\"title\": \"Role Group 1\", \"hideCheckbox\": true, \"expanded\": true, \"children\":["+
		"{\"title\": \"Utente della DCA\", \"selected\": true, \"startSelected\": true},"+
		"{\"title\": \"Utente dell Ufficio Territoriale\", \"startSelected\": false}"+
	  "]},"+
    "{\"title\": \"Role Group 2\", \"hideCheckbox\": true},"+
	"{\"title\": \"Role Group 3\", \"hideCheckbox\": true}"+
"]";
 *	
 */
public class ProfiloTreeBeanRequest extends BaseTreeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4197852330851283645L;
	
	private ProfiloTreeSubBean data;
	
	private List<ProfiloTreeBeanRequest> children;
	
	
	@Override
	public String toString() {
		return super.toString() + 
				"ProfiloTreeBeanRequest [data=" + data + ", children="
				+ children + "]";
	}

	/** CONSTRUCTORS **/
	public ProfiloTreeBeanRequest() {
		super();
	}
	
	/** GETTERS AND SETTERS **/
	public ProfiloTreeSubBean getData() {
		return data;
	}

	public void setData(ProfiloTreeSubBean data) {
		this.data = data;
	}
	
	public List<ProfiloTreeBeanRequest> getChildren() {
		return children;
	}
	public void setChildren(List<ProfiloTreeBeanRequest> children) {
		this.children = children;
	}


	
}
