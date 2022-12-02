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
//		List<Product> prodList = manager.findProductList(keyword);  //오늘의 best!
//		manager.findProductList(keyword); 
		
		request.setAttribute("searchList", manager.findProductList(keyword));
		
//		request.setAttribute("searchList", prodList);
//		return "/product/searhList.jsp";  
		return "/product/searchList.jsp";
	}
}
