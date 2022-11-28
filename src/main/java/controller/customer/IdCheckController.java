package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.CustomerManager;

public class IdCheckController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String customerId = request.getParameter("customerId");
		System.out.println("id를 가져옴: " + customerId);
		CustomerManager manager = CustomerManager.getInstance();
		boolean result = manager.idCheck(customerId);
		request.setAttribute("idCheckResult", result);
		
		return "/customer/idCheck.jsp";	
	}

}
