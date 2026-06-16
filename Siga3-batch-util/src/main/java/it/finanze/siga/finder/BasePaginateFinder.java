package it.finanze.siga.finder;

import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.finder.PaginateFinder;

public class BasePaginateFinder extends PaginateFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6746833727454339078L;
	
	private SigaCache cache;

	public SigaCache getCache() {
		return cache;
	}

	public void setCache(SigaCache cache) {
		this.cache = cache;
	}
	
}
