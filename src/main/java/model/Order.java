package model;

import java.util.Date;
import java.util.List;

public class Order {
	
	private int orderId;
	private Date orderDate;
	private String orderStatus;
	private String customerId;
	private int usedPoint;
	private List<Item> items;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Order() {};
	
	public Order(int orderId, Date orderDate, String orderStatus, String customerId, int usedPoint, List<Item> items) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.customerId = customerId;
		this.usedPoint = usedPoint;
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", customerId=" + customerId + ", usedPoint=" + usedPoint + ", items=" + items + "]";
	}
}
