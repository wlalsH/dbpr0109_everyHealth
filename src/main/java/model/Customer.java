package model;

import java.util.List;

public class Customer {
	private String customerId;
	private String password;
	private String name;
	private String address;
	private String emailAddr;
	private String phoneNumber;
	private List<Order> orders;
	private int point;
	
	public Customer() {}
	
	public Customer(String customerId, String password, String name, String address, String emailAddr,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.emailAddr = emailAddr;
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameCustomer(String customerid) {
        return this.customerId.equals(customerid);
    }
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", password=" + password + ", name=" + name + ", address="
				+ address + ", emailAddr=" + emailAddr + ", phoneNumber=" + phoneNumber + ", orders=" + orders
				+ ", point=" + point + "]";
	}

}