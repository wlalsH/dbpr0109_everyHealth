package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Order;
import model.Item;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * customer테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class CustomerDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CustomerDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**
	 * customer 테이블에 새로운 customer 생성.
	 */
	public int createCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer (customerid, password, name, address, emailaddr, phonenumber) VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {customer.getCustomerId(), customer.getPassword(), customer.getName(), 
				customer.getAddress(), customer.getEmailAddr(), customer.getPhoneNumber()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	/**
	 * 기존의 customer 정보를 수정.
	 */
	public int updateCustomer(Customer customer) throws SQLException {
		String sql = "UPDATE customer "
					+ "SET password=?, name=?, address=?, emailaddr=?, phonenumber=? "
					+ "WHERE customerid=?";
		Object[] param = new Object[] {customer.getPassword(), customer.getName(), 
					customer.getAddress(), customer.getEmailAddr(), customer.getPhoneNumber(), 
					customer.getCustomerId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * customerId에 해당하는 사용자를 삭제.
	 */
	public int removeCustomer(String customerId) throws SQLException {
		String sql = "DELETE FROM customer WHERE customerid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/**
	 * 주어진 customerId에 해당하는 사용자 정보를 데이터베이스에서 찾아 Customer 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Customer findCustomer(String customerId) throws SQLException {
        String sql = "SELECT password, name, address, emailAddr, phonenumber, point "
        			+ "FROM customer "
        			+ "WHERE customerid = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId});	// JDBCUtil에 query문과 매개 변수 설정

		Customer customer = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				customer = new Customer(
						customerId,
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("emailaddr"),
						rs.getString("phonenumber"));
				customer.setPoint(rs.getInt("point"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return customer;
	}
	
	/**
	 * 주어진 customerId를 통해 해당 customer의 Order정보들을 list로 반환
	 */
	public List<Order> findOrderList(String customerId) throws SQLException {
		String sql = "SELECT orderid, orderdate, orderstatus "
    			+ "FROM order_main "
    			+ "WHERE customerid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId});
		
		List<Order> orderList = new ArrayList<Order>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setCustomerId(customerId);
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderDate(rs.getDate("orderdate"));
				order.setOrderStatus(rs.getString("orderstatus"));
				orderList.add(order);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return orderList;
	}
	
	/**
	 * 주어진 orderId를 통해 해당 order의 Item정보들을 list로 반환
	 */
	public List<Item> findItemList(int orderId) throws SQLException {
		String sql = "SELECT lineno, productid, quantity "
    			+ "FROM item "
    			+ "WHERE orderid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {orderId});
		
		List<Item> itemList = new ArrayList<Item>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setOrderId(orderId);
				item.setLineNo(rs.getInt("lineno"));
				item.setProductId(rs.getString("productid"));
				item.setQuantity(rs.getInt("quantity"));
				itemList.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return itemList;
	}
	
	public String findProductName(String productId) throws SQLException {
		String sql = "SELECT name "
    			+ "FROM product "
    			+ "WHERE productid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {productId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				return name;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return null;
	}
	
	/**
	 * 주어진 cusotmerId에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingCustomer(String customerId) throws SQLException {
		String sql = "SELECT count(*) FROM customer WHERE customerid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customerId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}