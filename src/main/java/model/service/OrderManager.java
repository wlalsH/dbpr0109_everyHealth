package model.service;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.BillingInfo;
import model.CashReceipt;
import model.Item;
import model.NonMemCustomer;
import model.Order;
import model.Product;
import model.ShippingDetail;
import model.dao.OrderDAO;
import model.dao.ProductDAO;

public class OrderManager {
	private static OrderManager orderMan = new OrderManager();
	private OrderDAO orderDAO;
	private ProductDAO productDAO;
	
	private OrderManager() {
		try {
			orderDAO = new OrderDAO();
			productDAO = new ProductDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static OrderManager getInstance() {
		return orderMan;
	}
	
	/**
	 * 총 가격 계산하기.
	 */
	public int calcTotalPrice(ArrayList<Item> items) throws SQLException {
		Product product;
		int totalPrice = 0;
		for (Item item : items) {
			product = productDAO.findProduct(item.getProductId());
			totalPrice += product.getPrice();
		}
		
		return totalPrice;
	}
	
	/**
	 * orderId로 item 반환. 
	 */
	public ArrayList<Item> findItemsByOrderId(int orderid) {
		ArrayList<Item> items = orderDAO.findItemsByOrderId(orderid);
		return items;
	}
	
	/**
	 * 주문 생성하기. 
	 */
	public Order createOrder(Order order, NonMemCustomer nmCustomer, List<Item> items, CashReceipt cr, BillingInfo bi, ShippingDetail sd, int finalPrice) throws OutOfStockException, SQLException {
		ArrayList<Item> soldOutItems = new ArrayList<Item>();
		Product product;
		for (int i = 0; i < items.size(); i++) {		// 주문 전 재고 확인. 
			Item item = items.get(i);
			product = productDAO.findProduct(item.getProductId());
			if (product.getStock() < item.getQuantity()) {
				soldOutItems.add(item);		
				items.remove(i);						// 품절된 상품은 items에서 삭제한다. 
			}

		}
		
		// items 중 품절된 상품이 하나라도 있는 경우. 
		if (soldOutItems.size() > 0) {					
			createOrder(order, nmCustomer, items, cr, bi, sd, finalPrice);	
			
			StringBuffer sb = new StringBuffer("");
			for (Item item : soldOutItems) {
				product = productDAO.findProduct(item.getProductId());
				sb.append(product.getName() + ", ");
				
			}
			sb.deleteCharAt(sb.length() - 2);
			
			throw new OutOfStockException(sb + "이 품절입니다. 다시 주문해주세요.");
		} 
		
		// items 중 품절된 상품이 없어 바로 주문이 가능한 경우. 
		if (nmCustomer != null) {
			return orderDAO.createNonMemberCustomer(order, nmCustomer, items, cr, bi, sd);
		}
		
		Order newOrder = orderDAO.createMemberOrder(order, items, cr, bi, sd);
		orderDAO.updateCustomerPoint(order.getCustomerId(), (-order.getUsedPoint() + (int)(finalPrice * 0.01)));
		
		return newOrder;
	}
	
	/**
	 * 주문 취소하기. 
	 */
	public boolean cancelOrder(String customerId, int orderId) throws UnavailableCancelException {
		String orderStatus = orderDAO.findOrderStatus(orderId);
		
		if (orderStatus.equals("입금 전") == false) {
			throw new UnavailableCancelException(orderStatus + " 단계에서는 주문 취소가 불가능합니다.");
		} 
		
		if (customerId != null) {			// 회원의 주문 취소인 경우 
			int usedPoint = orderDAO.findUsedPoint(orderId);
			orderDAO.updateCustomerPoint(customerId, usedPoint);
		}
		
		return orderDAO.cancelOrRefundOrder(orderId, "취소");
	}
	
	/**
	 * 환불하기. 
	 */
	public boolean refund(String customerId, int orderId) throws UnavailableRefundException {
		Date shippedDate = new Date();
		shippedDate = orderDAO.findShippedDate(orderId);

		Calendar availableDate = Calendar.getInstance();
		availableDate.setTime(shippedDate);
		availableDate.add(Calendar.DATE, 7);

		if (Calendar.getInstance().after(availableDate)) {
			throw new UnavailableRefundException("최종 배송 완료일로부터 7일 이후로는 환불이 불가능합니다.");
		}
		
		if (customerId != null) {
			int usedPoint = orderDAO.findUsedPoint(orderId);
			orderDAO.updateCustomerPoint(customerId, usedPoint);
		}
		
		return orderDAO.cancelOrRefundOrder(orderId, "환불");
	}
}
