package controller.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Item;
import model.Order;
import model.service.OrderManager;

public class CheckOrderController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		OrderManager manager = OrderManager.getInstance();
//		ProductManager productManager = ProductManager.getInstane();
		
		ArrayList<Item> items = manager.findItemsByOrderId(order.getOrderId());
		
		for (Item item : items) {
//			Product product = 
		}
		
		request.setAttribute("items", items);
		session.removeAttribute("order");
		
		return "/order/orderCheck.jsp";
	}

}
