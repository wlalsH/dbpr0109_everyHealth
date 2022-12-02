package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.dao.ProductDao;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * ProductDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class ProductManager {
	private static ProductManager prodMan = new ProductManager();
	private ProductDao productDao;
	
	private ProductManager() {
		try {
			productDao = new ProductDao();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ProductManager getInstance() {
		return prodMan;
	}

	public List<Product> findProductList(String keyword) throws SQLException {
		return productDao.findProductList(keyword);
	}
	
	public List<Product> findBestProductList() throws SQLException {
		return productDao.findBestProductList();
	}
	
	public List<Product> findProductListByCategory(String category) throws Exception {
		return productDao.findProductListByCategory(category);
	}
	
	public List<Product> findBestProductListByCategory(String category) throws Exception {
		return productDao.findBestProductListByCategory(category);
	}
	
	public ProductDao getProductDAO() {
		return this.productDao;
	}

	public Product findProduct(String productId) {
		// TODO Auto-generated method stub
		Product prod = null;
		try {
			prod = productDao.findProduct(productId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return prod;
	}
}
