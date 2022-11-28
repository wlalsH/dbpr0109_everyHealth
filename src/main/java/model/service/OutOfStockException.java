package model.service;

public class OutOfStockException extends Exception {
	private static final long serialVersionUID = 1L;

	public OutOfStockException() {
		super();
	}
	
	public OutOfStockException(String arg0) {
		super(arg0);
	}
}
