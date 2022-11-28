package model;

public class CashReceipt {
	private String type;
	private String phoneNum;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public CashReceipt(String type, String phoneNum) {
		super();
		this.type = type;
		this.phoneNum = phoneNum;
	}
	
	@Override
	public String toString() {
		return "CashReceipt [type=" + type + ", phoneNum=" + phoneNum + "]";
	}	
}
