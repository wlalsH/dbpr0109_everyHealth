package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.CustomerManager;

public class DeleteCustomerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteCustomerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("customerId");
    	log.debug("Delete User : {}", deleteId);

		CustomerManager manager = CustomerManager.getInstance();	
		manager.remove(deleteId);				// 사용자 정보 삭제									// 로그인한 사용자는 이미 삭제됨
		return "redirect:/customer/logout";		// logout 처리
	}
}
