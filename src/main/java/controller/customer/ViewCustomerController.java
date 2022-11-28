package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.CustomerManager;
import model.Customer;

public class ViewCustomerController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerManager manager = CustomerManager.getInstance();
		String customerId = request.getParameter("customerId");
		Customer customer = manager.findCustomer(customerId);
		request.setAttribute("customer", customer);
		
		return "/customer/view.jsp";
	}
	
}
