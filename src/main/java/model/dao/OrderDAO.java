package model.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import model.*;

public class OrderDAO {
	private static JDBCUtil jdbcUtil = null;
	
	public OrderDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
//	/**
//	 * productId를 이용하여 상품의 재고를 int 타입으로 반환한다. 
//	 */
//	public int checkProductStock(String productId) {
//		String sql = "SELECT stock "
//				+ "FROM product "
//				+ "WHERE productid=?";
//		Object[] param = new Object[] {productId};
//		
//		ResultSet rs = null;
//		try {
//			jdbcUtil.setSqlAndParameters(sql, param);
//			rs = jdbcUtil.executeQuery();
//			
//			int stock = -1;
//			while (rs.next()) {
//				stock = rs.getInt("stock");
//			}
//			
//			return stock;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//			jdbcUtil.close();
//		}
//		return 0;
//	}
	
	/**
	 * 회원 사용의 주문을 생성하고 Order DTO를 반환한다.
	 */
	public Order createMemberOrder(Order order, List<Item> items, CashReceipt cr, BillingInfo bi, ShippingDetail sd) {
		String omSql = "INSERT INTO order_main "
				+ "VALUES (orderid_seq.nextval, ?, ?, ?)";
		String odSql = "INSERT INTO order_detail "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String itemSql = "INSERT INTO item "
				+ "VALUES (?, ?, ?, lineno_seq.nextval)";
		String sdSql = "INSERT INTO shipping_detail "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String productSql = "UPDATE product "	// 재고 관리 (	product테이블의 재고 컬럼인 stock에서 item quantity만큼 차감)
				+ "SET stock=stock-?, sell=sell+? "
				+ "WHERE productid=?";	
		
		Date date = order.getOrderDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(date);
		java.sql.Date orderDate = java.sql.Date.valueOf(formattedDate);
		
		Object[] omParams = new Object[] {orderDate , order.getOrderStatus(), order.getCustomerId()};
		Object[] odParams = new Object[] {null, bi.getAccountHolder(), bi.getBankName(), cr.getType(), cr.getPhoneNum(), order.getUsedPoint()};
		Object[] itemParams = new Object[] {null, null, null};
		Object[] sdParams = new Object[] {null, sd.getDateShipped(), sd.getShippingCompany(), sd.getTrackingNumber(), sd.getShippingMessage(), sd.getShippingAddress()};
		Object[] productParams = new Object[] {null, null, null};
		
		Order newOrder = new Order();
		String[] orderId = {"orderid"};
		try {
			jdbcUtil.setSqlAndParameters(omSql, omParams);
			jdbcUtil.executeUpdate(orderId);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			int oid = -1;
			if (rs.next()) {
				oid = rs.getInt(1);
			}
			newOrder.setOrderId(oid);
			
			odParams[0] = oid;
			jdbcUtil.setSqlAndParameters(odSql, odParams);
			jdbcUtil.executeUpdate();
			
			for (Item item : items) {
				itemParams[0] = oid;
				itemParams[1] = item.getProductId();
				itemParams[2] = item.getQuantity();
				jdbcUtil.setSqlAndParameters(itemSql, itemParams);
				jdbcUtil.executeUpdate();
			}
			
			sdParams[0] = oid;
			jdbcUtil.setSqlAndParameters(sdSql, sdParams);
			jdbcUtil.executeUpdate();
			
			for (Item item : items) {
				productParams[0] = item.getQuantity();
				productParams[1] = item.getQuantity();
				productParams[2] = item.getProductId();
				jdbcUtil.setSqlAndParameters(productSql, productParams);
				jdbcUtil.executeUpdate();
			}
			
			jdbcUtil.commit();
			
			return newOrder;
		} catch (Exception ex) {
			try {
				jdbcUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	/**
	 * orderid를 사용하여 ArrayList<Item>을 반환한다.
	 */
	public ArrayList<Item> findItemsByOrderId(int orderid) {
		String sql = "SELECT * "
				+ "FROM item "
				+ "WHERE orderid=?";
		Object[] params = new Object[] {orderid};
		
		ArrayList<Item> items = null;
		ResultSet rs = null;
		try {
			jdbcUtil.setSqlAndParameters(sql, params);
			rs = jdbcUtil.executeQuery();
			
			Item item = new Item();
			while (rs.next()) {
				item.setProductId(rs.getString("productid"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return items;
	}
	
	/**
	 * 비회원 사용자의 주문을 생성하고 Order DTO를 반환한다. 
	 */
	public Order createNonMemberCustomer(Order order, NonMemCustomer nmCustomer, List<Item> items, CashReceipt cr, BillingInfo bi, ShippingDetail sd) {
		String omSql = "INSERT INTO order_main "
				+ "VALUES (orderid_seq.nextval, ?, ?, null)";
		String nmcSql = "INSERT INTO non_member_customer "		// 비회원 주문자 테이블(non_member_customer)에 정보 저장
				+ "VALUES (?, ?, ?, ?)";	
		String odSql = "INSERT INTO order_detail "
				+ "VALUES (?, ?, ?, ?, ?)";
		String itemSql = "INSERT INTO item "
				+ "VALUES (?, ?, ?, lineno_seq.nextval)";
		String sdSql = "INSERT INTO shipping_detail "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String productSql = "UPDATE product "		// 재고 관리 (	product테이블의 재고 컬럼인 stock에서 item quantity만큼 차감)
				+ "SET stock=stock-?, sell=sell+? "
				+ "WHERE productid=?";	
		
		Date date = order.getOrderDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(date);
		java.sql.Date orderDate = java.sql.Date.valueOf(formattedDate);
		
		Object[] omParams = new Object[] {orderDate, order.getOrderStatus()};
		Object[] mncParams = new Object[] {null, nmCustomer.getName(), nmCustomer.getEmailaddr(), nmCustomer.getPhoneNumber()};
		Object[] odParams = new Object[] {null, bi.getAccountHolder(), bi.getBankName(), cr.getType(), cr.getPhoneNum()};
		Object[] itemParams = new Object[] {null, null, null};
		Object[] sdParams = new Object[] {null, sd.getDateShipped(), sd.getShippingCompany(), sd.getTrackingNumber(), sd.getShippingMessage(), sd.getShippingAddress()};
		Object[] productParams = new Object[] {null, null, null};
		
		Order newOrder = new Order();
		String[] orderId = {"orderid"};
		try {
			jdbcUtil.setSqlAndParameters(omSql, omParams);
			jdbcUtil.executeUpdate(orderId);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			int oid = -1;
			if (rs.next()) {
				oid = rs.getInt(1);
				newOrder.setOrderId(oid);
			}
			
			mncParams[0] = oid;
			jdbcUtil.setSqlAndParameters(nmcSql, mncParams);
			jdbcUtil.executeUpdate();
			
			odParams[0] = oid;
			jdbcUtil.setSqlAndParameters(odSql, odParams);
			jdbcUtil.executeUpdate();
			
			for (Item item : items) {
				itemParams[0] = oid;
				itemParams[1] = item.getProductId();
				itemParams[2] = item.getQuantity();
				jdbcUtil.setSqlAndParameters(itemSql, itemParams);
				jdbcUtil.executeUpdate();
			}
			
			sdParams[0] = oid;
			jdbcUtil.setSqlAndParameters(sdSql, sdParams);
			jdbcUtil.executeUpdate();
			
			for (Item item : items) {
				productParams[0] = item.getQuantity();
				productParams[1] = item.getQuantity();
				productParams[2] = item.getProductId();
				jdbcUtil.setSqlAndParameters(productSql, productParams);
				jdbcUtil.executeUpdate();
			}
			
			jdbcUtil.commit();
			
			return newOrder;
		} catch (Exception ex) {
			try {
				jdbcUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	/**
	 * orderId로 사용된 포인트 찾기. 
	 */
	public int findUsedPoint(int orderId) {
		String sql = "SELECT usedpoint "
				+ "FROM order_detail "
				+ "WHERE orderid=?";
		Object[] param = new Object[] {orderId};
		
		ResultSet rs = null;
		try {
			jdbcUtil.setSqlAndParameters(sql, param);
			rs = jdbcUtil.executeQuery();
			
			int point = 0;
			while (rs.next()) {
				point = rs.getInt("usedpoint");
			}
			
			return point;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return 0;
	}
	
	/**
	 *	customerId를 이용하여 customer의 point 정보를 업데이트한다. 
	 */
	public boolean updateCustomerPoint(String customerId, int point) {
		String sql = "UPDATE customer "
				+ "SET point = point + ? "
				+ "WHERE customerId = ?";
		
		Object[] params = new Object[] {point, customerId};
		
		try {
			jdbcUtil.setSqlAndParameters(sql, params);
			int result = jdbcUtil.executeUpdate();
			
			if (result != 1) {
				jdbcUtil.rollback();
			}
			
			jdbcUtil.commit();
			return true;
		} catch (Exception ex) {
			try {
				jdbcUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return false;
	}
	
	/**
	 *	orderId를 이용하여 주문 상태를 int 타입으로 반환한다.
	 */
	public String findOrderStatus(int orderId) {
		String sql = "SELECT orderstatus "
				+ "FROM order_main "
				+ "WHERE orderid=?";
		Object[] param = new Object[] {orderId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		ResultSet rs;
		try {
			rs = jdbcUtil.executeQuery();
			
			String status = null;
			if (rs.next()) {
				status = rs.getString("orderstatus");
			}
			
			return status;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	/**
	 * orderId를 이용하여 최종 배송 완료일을 Date 타입으로 반환한다. 
	 */
	public Date findShippedDate(int orderId) {
		String sql = "SELECT dateshipped "
				+ "FROM shipping_detail "
				+ "WHERE orderid=?";
		Object[] param = new Object[] {orderId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		ResultSet rs = null;
		try {
			rs = jdbcUtil.executeQuery();
			
			java.sql.Date date = null;
			if (rs.next()) {
				date = rs.getDate("dateshipped");
			}
			
			java.util.Date utilDate = new java.util.Date(date.getTime());
			
			return utilDate;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			jdbcUtil.close();
		}
		
		return null;
	}
	

	/**
	 * orderId를 이용하여 주문 상태를 취소 혹은 환불로 바꾸고 재고를 수정한다. 
	 */
	public boolean cancelOrRefundOrder(int orderId, String orderStatus) {
		String omSql = "UPDATE order_main "
				+ "SET orderstatus=? "
				+ "WHERE orderid=?";
		String itemSql = "SELECT productid, quantity "		//	orderid를 사용해 item 테이블에서 productid 확인.
				+ "FROM item "
				+ "WHERE orderid=?";	
		String productSql = "UPDATE product "		// productid를 사용해 재고 수정. 
				+ "SET stock=stock+? "
				+ "WHERE productid=?";	
		
		Object[] omParams = new Object[] {orderStatus, orderId};
		Object[] itemParams = new Object[] {orderId};
		Object[] productParams = new Object[] {null, null};
		
		try {
			jdbcUtil.setSqlAndParameters(omSql, omParams);
			jdbcUtil.executeUpdate();
			
			jdbcUtil.setSqlAndParameters(itemSql, itemParams);
			ResultSet rs = jdbcUtil.executeQuery();
			
			ArrayList<Item> items = new ArrayList<Item>();
			while (rs.next()) {
				String productId = rs.getString("productid");
				int quantity = rs.getInt("quantity");
				
				Item item = new Item();
				item.setProductId(productId);
				item.setQuantity(quantity);
				
				items.add(item);
			}
			
			for (Item item : items) {
				productParams[0] = item.getQuantity();
				productParams[1] = item.getProductId();
				
				jdbcUtil.setSqlAndParameters(productSql, productParams);
				jdbcUtil.executeUpdate();
			}
			
			jdbcUtil.commit();
			
			return true;
		} catch (Exception ex) {
			try {
				jdbcUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return false;
	}
	
	/*
	 * orderId를 이용하여 주문을 삭제한다. 
	 */
	public void deleteMemberOrder(int orderId) {
		String omSql = "DELETE FROM order_main "
				+ "WHERE orderid=?";
		String odSql = "DELETE FROM order_detail "
				+ "WHERE orderid=?";
		String itemSql = "DELETE FROM item "
				+ "WHERE orderid=?";
		String shSql = "DELETE FROM shipping_detail "
				+ "WHERE orderid=?";
		
		Object[] param = new Object[] {orderId};
		
		try {
			jdbcUtil.setSqlAndParameters(odSql, param);
			jdbcUtil.executeUpdate();
			
			jdbcUtil.setSqlAndParameters(itemSql, param);
			jdbcUtil.executeUpdate();
			
			jdbcUtil.setSqlAndParameters(shSql, param);
			jdbcUtil.executeUpdate();
			
			jdbcUtil.setSqlAndParameters(omSql, param);
			jdbcUtil.executeUpdate();
			
			jdbcUtil.commit();
		} catch (Exception ex) {
			try {
				jdbcUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally  {
			jdbcUtil.close();
		}
	}
}
