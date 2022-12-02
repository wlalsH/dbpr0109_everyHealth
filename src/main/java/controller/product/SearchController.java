package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Product;
import model.service.ProductManager;

public class SearchController implements Controller{
	@Override
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = request.getParameter("keyword");
		
		ProductManager manager = ProductManager.getInstance();
		request.setAttribute("searchList", manager.findProductList(keyword));

		return "/product/searchList.jsp";
	}
}
