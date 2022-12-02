package model;

public class Item {
	private int orderId;
	private int lineNo;
	private String productId;
	private int quantity;
	private String productName;
	private String image;
	
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Item() {};
	
	public Item(int orderId, String productId, int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Item(String productId, String productName, int quantity, String image) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.image = image;
	}


	public Item(int orderId, int lineNo, String productId, int quantity, String productName) {
		super();
		this.orderId = orderId;
		this.lineNo = lineNo;
		this.productId = productId;
		this.quantity = quantity;
		this.productName = productName;
	}
	
	@Override
	public String toString() {
		return "Item [orderId=" + orderId + ", lineNo=" + lineNo + ", productId=" + productId + ", quantity=" + quantity
				+ ", productName=" + productName + "]";
	}
	
}
