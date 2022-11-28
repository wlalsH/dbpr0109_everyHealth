package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Customer;
import model.service.ExistingCustomerException;
import model.service.CustomerManager;

public class RegisterCustomerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterCustomerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		return "/customer/registerForm.jsp";   // 검색한 커뮤니티 리스트를 registerForm으로 전송     	
	    }
    	
    	// POST request (회원정보가 parameter로 전송됨)
       	Customer customer = new Customer(
			request.getParameter("customerId"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("address"),
			request.getParameter("emailAddr1") + "@" + request.getParameter("emailAddr2"),
			request.getParameter("phoneNum1") + "-" +request.getParameter("phoneNum2") + "-" + request.getParameter("phoneNum3"));
		
        log.debug("Create customer : {}", customer);

		try {
			CustomerManager manager = CustomerManager.getInstance();
			manager.create(customer);
	        return "redirect:/customer/login/form";
	        
		} catch (ExistingCustomerException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("customer", customer);
			return "/customer/registerForm.jsp";
		}
    }
}
