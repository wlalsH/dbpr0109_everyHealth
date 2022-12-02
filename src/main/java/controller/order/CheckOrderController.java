package controller.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Item;
import model.Order;
import model.Product;
import model.service.OrderManager;
import model.service.ProductManager;

public class CheckOrderController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateOrderController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sOrderId = request.getParameter("orderid");
		int orderId = Integer.parseInt(sOrderId);
		
		OrderManager orderManager = OrderManager.getInstance();
		ProductManager productManager = ProductManager.getInstance();
		
		ArrayList<Item> items = orderManager.findItemsByOrderId(orderId);
		for (Item item : items) {
			Product product = productManager.findProduct(item.getProductId());
			item.setImage(product.getImage());
			item.setProductName(product.getName());
		}
		
		request.setAttribute("items", items);
		
		return "/order/orderCheck.jsp";
	}

}
