package it.finanze.siga.finder;

public class DocumentoFinder extends BaseFinder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9216107548929044028L;
	/**
	 * Dati del 
	 */
	public int idTab;
	private Integer progDoc;
	byte[] bFile;// = new byte[(int) file.length()];
	String fileName;

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getbFile() {
		return bFile;
	}

	public void setbFile(byte[] bFile) {
		this.bFile = bFile;
	}

	public Integer getProgDoc() {
		return progDoc;
	}

	public void setProgDoc(Integer progDoc) {
		this.progDoc = progDoc;
	}

	public DocumentoFinder(int idTab){
		this.idTab = idTab;
	}

	public int getIdTab() {
		return idTab;
	}

	public void setIdTab(int idTab) {
		this.idTab = idTab;
	}
	
	

}
