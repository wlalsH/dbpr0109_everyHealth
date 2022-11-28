package model.service;

public class UnavailableCancelException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnavailableCancelException() {
		super();
	}
	
	public UnavailableCancelException(String arg0) {
		super(arg0);
	}
}
