/**
 * 
 */
package coen275.chronos.service.impl;

/**
 * @author kaushik
 *
 */
public class WorkspaceException extends Exception {
	public static  enum REASON {
		DUPLICATE,INTERNAL_ERROR
	}
	
	private REASON reason;
	public WorkspaceException(REASON r, String message) {
		super(message);
		this.reason = r;
	}
	/**
	 * @return the reason
	 */
	public REASON getReason() {
		return reason;
	}

}
