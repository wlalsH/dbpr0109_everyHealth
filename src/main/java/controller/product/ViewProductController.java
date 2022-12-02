package controller.product;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Product;
import model.service.ProductManager;

public class ViewProductController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	
		ProductManager manager = ProductManager.getInstance();
		String productId = request.getParameter("productId");
		
		Product prod = null;
		prod = manager.findProduct(productId);

		request.setAttribute("product", prod);				
		return "/product/view.jsp";				
    }

}
