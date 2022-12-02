package model.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDao {
	private JDBCUtil jdbcUtil = null;

	public ProductDao() { 
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	/**
	 * 상위 6개 상품 출력 (오늘의 best)
	 */
	public List<Product> findBestProductList() throws SQLException {
		String sql = "SELECT productId, name, price, image, sell "
				+ "FROM (\r\n"
				+ "    SELECT productId, name, price, image, sell\r\n"
				+ "    FROM product\r\n"
				+ "    ORDER BY sell DESC)\r\n"
				+ "WHERE ROWNUM <= 6 ";
		jdbcUtil.setSqlAndParameters(sql, null); 

		List<Product> prodList = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행 
			prodList = new ArrayList<Product>(); // Product 리스트 생성
			while (rs.next()) {
				Product prod = new Product( // Product 객체를 생성하여 정보 저장
						rs.getString("productId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"));
				prodList.add(prod); // List 에 Product 객체 저장
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return prodList; 
	}
	
	/**
	 * category별로 product 정보를 검색하여 List 에 저장 및 반환
	 */
	public List<Product> findProductListByCategory(String category) throws SQLException {
		String sql = "SELECT productId, name, price, image, category, sell\r\n"
				+ "FROM product "
				+ "WHERE category = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {category});

		List<Product> prodList = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행 
			prodList = new ArrayList<Product>(); // Product 리스트 생성
			while (rs.next()) {
				Product prod = new Product( // Product 객체를 생성하여 정보 저장
						rs.getString("productId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("category"),
						rs.getInt("sell"));
				prodList.add(prod); // List 에 Product 객체 저장
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return prodList; 
	}
	
	/**
	 * category별 랭킹(top 5)
	 */
	public List<Product> findBestProductListByCategory(String category) throws SQLException {
		String sql = "SELECT productId, name, price, image, category, sell\r\n"
				+ "FROM (\r\n"
				+ "    SELECT productId, name, price, image, category, sell\r\n"
				+ "    FROM product\r\n"
				+ "    WHERE category=?\r\n"
				+ "    ORDER BY sell DESC)\r\n"
				+ "WHERE ROWNUM <= 5";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {category});

		List<Product> prodList = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행 
			prodList = new ArrayList<Product>(); // Product 리스트 생성
			while (rs.next()) {
				Product prod = new Product( // Product 객체를 생성하여 정보 저장
						rs.getString("productId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("category"),
						rs.getInt("sell"));
				prodList.add(prod); // List 에 Product 객체 저장
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return prodList; 
	}
	
	/**
	 * 제품을 입력받은 keyword 로 검색하여 List에 저장 및 반환
	 */
	public List<Product> findProductList(String keyword) throws SQLException {
		String sql = "SELECT productId, name, price, category, stock, description, image, sell\r\n"
				+ "FROM product\r\n"
				+ "WHERE UPPER(name) LIKE UPPER('%' || ? || '%')";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword});

		List<Product> prodList = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행 
			prodList = new ArrayList<Product>(); // Product 리스트 생성
			while (rs.next()) {
				Product prod = new Product( // Product 객체를 생성하여 정보 저장
						rs.getString("productId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("category"),
						rs.getInt("sell"));
				prodList.add(prod); // List 에 Product 객체 저장
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return prodList; 
	}

	/**
	 * 주어진 productId 에 해당하는 제품 정보를 데이터베이스에서 찾아 Product 도메인 클래스에
	 * 저장하여 반환.
	 */
	public Product findProduct(String productId) throws SQLException {
		String sql = "SELECT productId, name, price, category, stock, description, image, sell "
				+ "FROM product "
				+ "WHERE productid=? "; 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {productId});
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { 
				Product Product = new Product( // Product 객체를 생성하여
						productId,
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("category"),
						rs.getInt("stock"),
						rs.getString("description"),
						rs.getString("image"),
						rs.getInt("sell"));
				return Product;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
}