import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Product;
import model.dao.ProductDao;

public class Test {

	private static ProductDao productDao = new ProductDao();
	
	public static void main(String[] args) {
	
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.print("입력하시오: ");
//		String keyword = scanner.next();
//		
//		List<Product> productList = null;
//		try {
//			productList = productDao.findCategoryList();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	    
//		Iterator<Product> itr = productList.iterator();
//		
//		while(itr.hasNext()) {
//		    Product p = itr.next();
//		    System.out.printf("%s %s %d %s\n", p.getCategory());
//		}
//		
//		List<Product> productList = null;
//		try {
//			productList = productDao.findProductList(keyword);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	    
//		Iterator<Product> itr = productList.iterator();
//		
//		while(itr.hasNext()) {
//		    Product p = itr.next();
//		    System.out.printf("%s %s %d %s\n", p.getProductId(), p.getName(), p.getPrice(), p.getImage());
//		}

		
//		List<Product> productList = null;
//		try {
//			productList = productDao.findProductList();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	    
//		Iterator<Product> itr = productList.iterator();
//		
//		while(itr.hasNext()) {
//		    Product p = itr.next();
//		    System.out.printf("%s %s %d %s\n", p.getProductId(), p.getName(), p.getPrice(), p.getImage());
//		}
//	    
//		scanner.close();
	}
}
