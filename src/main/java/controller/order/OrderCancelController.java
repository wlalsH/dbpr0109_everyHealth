package controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.OrderManager;
import model.service.UnavailableCancelException;
import controller.customer.CustomerSessionUtils;

public class OrderCancelController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(OrderCancelController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderId = request.getParameter("orderId");
		log.debug("Cancel order : {}", orderId);
		
		OrderManager manager = OrderManager.getInstance();
		
		boolean result = false;
		HttpSession session = request.getSession();
		
		if (CustomerSessionUtils.hasLogined(session)) {		// 회원 주문 취소 요청 처리. 
			String id = CustomerSessionUtils.getLoginUserId(session);
			try {
				result = manager.cancelOrder(id, Integer.parseInt(orderId));
				if (!result) {
					request.setAttribute("refundFailed", true);
				}
			} catch (UnavailableCancelException e) {
				request.setAttribute("cancelFailed", true);
				request.setAttribute("exception", e);
			}
			
			return null; 		// 마이페이지. 
		} 				
			
		try {	// 비회원 주문 취소 요청 처리. 
			result = manager.cancelOrder(null, Integer.parseInt(orderId));
			if (!result) {
				request.setAttribute("refundFailed", true);
			}
		} catch (UnavailableCancelException e) {
			request.setAttribute("cancelFailed", true);
			request.setAttribute("exception", e);
		}
		
		return null;		// 메인으로.
	}
}
