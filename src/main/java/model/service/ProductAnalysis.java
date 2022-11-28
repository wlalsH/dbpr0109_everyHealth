package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Product;
import model.dao.ProductDao;

//an example business class
public class ProductAnalysis {
	private ProductDao dao;
	
	public ProductAnalysis() {}
	
	public ProductAnalysis(ProductDao dao) {
		super();
		this.dao = dao;
	}

	// an example business method
}
