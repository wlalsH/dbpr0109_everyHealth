package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Product;
import model.service.ProductManager;

public class ListProductController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	ProductManager manager = ProductManager.getInstance();
		List<Product> prodList = manager.findBestProductList();  //오늘의 best!
		
		String[] category = {"sight","fatigue", "joint", "digestion", "immune", "woman", "man", "kids" };
		List<Product> sightList = manager.findBestProductListByCategory(category[0]); 
		List<Product> fatigueList = manager.findBestProductListByCategory(category[1]); 
		List<Product> jointList = manager.findBestProductListByCategory(category[2]); 
		List<Product> digestionList = manager.findBestProductListByCategory(category[3]); 
		List<Product> immuneList = manager.findBestProductListByCategory(category[4]); 
		List<Product> womanList = manager.findBestProductListByCategory(category[5]); 
		List<Product> manList = manager.findBestProductListByCategory(category[6]); 
		List<Product> kidsList = manager.findBestProductListByCategory(category[7]); 
		
		request.setAttribute("prodList", prodList);		
		request.setAttribute("sightList", sightList);		
		request.setAttribute("fatigueList", fatigueList);
		request.setAttribute("jointList", jointList);
		request.setAttribute("digestionList", digestionList);
		request.setAttribute("immuneList", immuneList);
		request.setAttribute("womanList", womanList);
		request.setAttribute("manList", manList);
		request.setAttribute("kidsList", kidsList);
		return "/product/main.jsp";        
    }
}