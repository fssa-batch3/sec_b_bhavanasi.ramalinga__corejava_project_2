package in.fssa.productprice.service;
import in.fssa.productprice.validator.ProductValidator;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.model.Product;

import java.util.Set;
public class ProductService {
	
public Set<Product> listAllProduct()throws Exception{
		
		ProductDAO product = new ProductDAO();
		Set<Product> allProducts = product.listAllProducts();
		
		return allProducts;
	}
	
	public Set<Product> findProductDetailByCategoryId(int category_id) throws Exception {
	    ProductValidator validator = new ProductValidator();
	    validator.validateCategoryId(category_id);

	    ProductDAO productDAO = new ProductDAO();
	    Set<Product> products = productDAO.listallProductsByCategoryId(category_id);
	    
	    return products;
	}
	
	
	
	
	
	public void createProduct(Product product)throws Exception{
		
		ProductValidator validator = new ProductValidator();
		validator.validateProduct(product);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.create(product);
		
	}
	
	public void deleteProduct(int productId) throws Exception{
		
		ProductValidator validator = new ProductValidator();
		validator.validateProductId(productId);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);
		
	}

	

	public void updateProduct(int id, String name) throws Exception {
	    ProductValidator validator = new ProductValidator();
	    validator.validateProductUpdate(id, name); // You might need to create this validation method

	    ProductDAO productDAO = new ProductDAO();
	    productDAO.updateProduct(id, name);
	}

	

}
