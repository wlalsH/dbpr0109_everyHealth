package model;

public class NonMemCustomer {
	private String name;
	private String emailaddr;
	private String phoneNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailaddr() {
		return emailaddr;
	}
	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public NonMemCustomer(String name, String emailaddr, String phoneNumber) {
		super();
		this.name = name;
		this.emailaddr = emailaddr;
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "NonMemCustomer [name=" + name + ", emailaddr=" + emailaddr + ", phoneNumber=" + phoneNumber + "]";
	}
}
