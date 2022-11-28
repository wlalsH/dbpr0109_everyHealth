package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.CustomerManager;
import model.service.CustomerNotFoundException;
import model.Customer;
import model.Order;

public class MyPageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!CustomerSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/customer/login/form";
		}
		
		CustomerManager manager = CustomerManager.getInstance();
		String customerId = request.getParameter("customerId");
		
		Customer customer = null;
		try {
			customer = manager.findCustomer(customerId);
			
			for(Order order : customer.getOrders()) {
				order.setItems(manager.findItemList(order.getOrderId()));
			}
			
		} catch (CustomerNotFoundException ex) {
			return "redirect:/main";
		}
		
		request.setAttribute("customer", customer);
		return "/customer/myPage.jsp";
	}
	
}
