package controller.order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.customer.CustomerSessionUtils;
import model.BillingInfo;
import model.CashReceipt;
import model.Customer;
import model.Item;
import model.NonMemCustomer;
import model.Order;
import model.ShippingDetail;
import model.service.OrderManager;
import model.service.OutOfStockException;

public class CreateOrderController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateOrderController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String customerId = null;
		Customer customer = null;
		if (CustomerSessionUtils.hasLogined(session)) {
			customerId = CustomerSessionUtils.getLoginUserId(session);
//			CustomerManager에서 id를 통해 CustomerDTO 가져오기. 
		} 
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: order form 요청
    		log.debug("OrderForm Request");
    		
    		// orderForm에 회원 정보를 자동으로 기입하기 위해. 
    		if (CustomerSessionUtils.hasLogined(session)) {
//	    		request.setAttribute("customer", c);
    		}
    		
    		request.setAttribute("totalPrice", 30000);
    		
    		// 장바구니에서 넘어온 item 정리해서 "items"로 넘김. 
//    		request.setAttribute("items", );
    		
    		// items 정리해서 totalPrice 계산 후 넘김. 
    		request.setAttribute("totalPrice", 10000);
		
			return "/order/orderForm.jsp";   // orderForm으로 이동.     	
	    }
		
//		order form에서 입력받은 값 가져오기
		String name = request.getParameter("name");							// 주문자 이름
		String email = request.getParameter("email");						// 주문자 이메일
		String phone = request.getParameter("phone");						// 주문자 전화번호
		String usedPoint = request.getParameter("usedPoint");				// 사용한 포인트 (회원인 경우에만)
		String address = request.getParameter("address");					// 배송 주소
		String crType = request.getParameter("cashReceiptType");			// 현금 영수증 (개인/사업자)
		String crPhone = request.getParameter("cashRecepitPhone");			// 현금 영수증 번호
		String accountHolder = request.getParameter("accountHoler");		// 입금 이름
		String bankName = request.getParameter("bankName");					// 입금 은행명
		String shippingMessage = request.getParameter("shippingMessage");	// 배송 메세지
		ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");				// item
		
		ShippingDetail sd = new ShippingDetail(address, shippingMessage);	
		CashReceipt cr = new CashReceipt(crType, crPhone);
		BillingInfo bi = new BillingInfo(accountHolder, bankName);
		
		Order order = null;
		NonMemCustomer nmCustomer = null;
		if (customerId != null) {
			order = new Order(-1, new Date(), "입금 전", customerId, Integer.parseInt(usedPoint), null);
		} else {
			order = new Order(-1, new Date(), "입금 전", null, -1, null);
			nmCustomer = new NonMemCustomer(name, email, phone);
		}
		
		OrderManager manager = OrderManager.getInstance();
		int totalPrice = manager.calcTotalPrice(items);
		
		try {
			Order newOrder = manager.createOrder(order, nmCustomer, items, cr, bi, sd, totalPrice);
			session.setAttribute("order", newOrder);
			return "redirection:/order/orderCheck";
		} catch (OutOfStockException e) {
			request.setAttribute("exception", e);
			return "/order/orderForm.jsp";
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.setAttribute("orderFailed", "true");
			return "/order/orderForm.jsp";
		} 
	}

}
