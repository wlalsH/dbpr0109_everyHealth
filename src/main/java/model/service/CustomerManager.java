package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.Item;
import model.dao.CustomerDAO;

public class CustomerManager {
	private static CustomerManager customerMan = new CustomerManager();
	private CustomerDAO customerDAO;

	private CustomerManager() {
		try {
			customerDAO = new CustomerDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CustomerManager getInstance() {
		return customerMan;
	}
	
	public int create(Customer customer) throws SQLException, ExistingCustomerException {
		if (customerDAO.existingCustomer(customer.getCustomerId()) == true) {
			throw new ExistingCustomerException(customer.getCustomerId() + "는 존재하는 아이디입니다.");
		}
		return customerDAO.createCustomer(customer);
	}

	public int update(Customer customer) throws SQLException, CustomerNotFoundException {
		return customerDAO.updateCustomer(customer);
	}
	
	public int remove(String customerId) throws SQLException, CustomerNotFoundException {
		return customerDAO.removeCustomer(customerId);
	}

	public Customer findCustomer(String customerId)
		throws SQLException, CustomerNotFoundException {
		Customer customer = customerDAO.findCustomer(customerId);
		
		if (customer == null) {
			throw new CustomerNotFoundException(customerId + "는 존재하지 않는 아이디입니다.");
		}
		else {
			customer.setOrders(customerDAO.findOrderList(customerId));
		}
		return customer;
	}
	
	public List<Item> findItemList(int orderId)
		throws SQLException, CustomerNotFoundException {
		List<Item> itemList = customerDAO.findItemList(orderId);
		for(Item item : itemList) {
			item.setProductName(customerDAO.findProductName(item.getProductId()));
		}
		return itemList;
	}

	public boolean login(String customerId, String password)
		throws SQLException, CustomerNotFoundException, PasswordMismatchException {
		Customer customer = findCustomer(customerId);

		if (!customer.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public boolean idCheck(String customerId) throws SQLException {
		return customerDAO.existingCustomer(customerId);
	}
	
	public CustomerDAO getCustomerDAO() {
		return this.customerDAO;
	}
}