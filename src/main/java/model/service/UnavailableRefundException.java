package model.service;

public class UnavailableRefundException extends Exception {
	private static final long serialVersionUID = 1L; 

	public UnavailableRefundException() {
		super();
	}
	
	public UnavailableRefundException(String arg0) {
		super(arg0);
	}
}
