package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.CustomerManager;
import model.Customer;

public class UpdateCustomerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateCustomerController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청
    		String updateId = request.getParameter("customerId");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		CustomerManager manager = CustomerManager.getInstance();
			Customer customer = manager.findCustomer(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("customer", customer);			

			return "/customer/updateForm.jsp";
	    }	
		
    	// POST request (회원정보가 parameter로 전송됨)
		Customer updateCustomer = new Customer(
				request.getParameter("customerId"),
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("emailAddr1") + "@" + request.getParameter("emailAddr2"),
				request.getParameter("phoneNum1") + "-" +request.getParameter("phoneNum2") + "-" + request.getParameter("phoneNum3"));

    	log.debug("Update User : {}", updateCustomer);

		CustomerManager manager = CustomerManager.getInstance();
		manager.update(updateCustomer);
        return "redirect:/customer/view?customerId=" + updateCustomer.getCustomerId();
	}

}
