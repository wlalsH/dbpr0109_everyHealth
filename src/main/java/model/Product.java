package model;

public class Product {
	private String productId;
	private String name;
	private int price;
	private String category;
	private int stock;
	private String description;
	private String image;
	private int sell;
	
	public Product(String productId, String name, int price, String image) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.image = image;
	}
	public Product(String productId, String name, int price, String category, String image, int sell) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = image;
		this.sell = sell;
	}
	
	public Product(String productId, String name, int price, String category, int stock, String description,
			String image, int sell) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.description = description;
		this.image = image;
		this.sell = sell;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSell() {
		return sell;
	}
	public void setSell(int sell) {
		this.sell = sell;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", stock=" + stock + ", description=" + description + ", image=" + image + ", sell=" + sell + "]";
	}
	

}
