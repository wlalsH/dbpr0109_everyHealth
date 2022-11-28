package model;

import java.util.Date;

public class ShippingDetail {
	private String shippingAddress;
	private Date dateShipped;
	private String shippingCompany;
	private String trackingNumber;
	private String shippingMessage;
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Date getDateShipped() {
		return dateShipped;
	}
	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}
	public String getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getShippingMessage() {
		return shippingMessage;
	}
	public void setShippingMessage(String shippingMessage) {
		this.shippingMessage = shippingMessage;
	}
	
	public ShippingDetail(String shippingAddress, String shippingMessage) {
		super();
		this.shippingAddress = shippingAddress;
		this.shippingMessage = shippingMessage;
	}
	
	public ShippingDetail(String shippingAddress, Date dateShipped, String shippingCompany, String trackingNumber,
			String shippingMessage) {
		super();
		this.shippingAddress = shippingAddress;
		this.dateShipped = dateShipped;
		this.shippingCompany = shippingCompany;
		this.trackingNumber = trackingNumber;
		this.shippingMessage = shippingMessage;
	}
	
	@Override
	public String toString() {
		return "ShippingDetail [shippingAddress=" + shippingAddress + ", dateShipped=" + dateShipped
				+ ", shippingCompany=" + shippingCompany + ", trackingNumber=" + trackingNumber + ", shippingMessage="
				+ shippingMessage + "]";
	}
}
