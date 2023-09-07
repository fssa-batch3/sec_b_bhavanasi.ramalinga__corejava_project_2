package in.fssa.productprice.service;
import in.fssa.productprice.validator.CategoryValidator;
import in.fssa.productprice.validator.ProductValidator;
import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.model.ProductEntity;

import java.util.Set;
public class ProductService {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
public Set<Product> listAllProduct()throws Exception{
		
		ProductDAO product = new ProductDAO();
		Set<Product> allProducts = product.listAllProducts();
		
		return allProducts;
	}
/**
 * 
 * @param category_id
 * @return
 * @throws Exception
 */
	
	public Set<Product> findProductDetailByCategoryId(int category_id) throws Exception {
	    ProductValidator validator = new ProductValidator();
	    validator.validateCategoryId(category_id);

	    ProductDAO productDAO = new ProductDAO();
	    Set<Product> products = productDAO.listallProductsByCategoryId(category_id);
	    
	    return products;
	}
	
	/**
	 * 
	 * @param product
	 * @throws Exception
	 */
	 
	
	
	public void createProduct(Product product)throws Exception{
		
		ProductValidator validator = new ProductValidator();
		validator.validateProduct(product);
		ProductDAO productDAO = new ProductDAO();
		productDAO.create(product);
		
	}
	/**
	 * 
	 * @param productId
	 * @throws Exception
	 */
	
public void delete(int productId) throws Exception{
	
	ProductValidator productvalidate = new ProductValidator();
		
	 productvalidate.validateProductId(productId);
		
		ProductDAO categoryDao = new ProductDAO();
		
		categoryDao.delete(productId);
	}
/**
 * 
 * @param id
 * @return 
 * @throws Exception
 */

  public Product findByIdproductdetails(int id) throws Exception {
    ProductValidator validator = new ProductValidator();
    validator.validateProductId(id);
    validator.validatingproductidAlreadyExist(id);
    ProductDAO productDAO = new ProductDAO();
    Product product = productDAO.findProductsById(id);
	return product; 
    
    
     }
  
  public Product findProductDetailsByProductId(int productId)throws Exception{
		
		ProductValidator validator = new ProductValidator();
		validator.validateProductId(productId);
		
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.findProductDetailsByProductId(productId);
		
		return product;
		
	}

	/**
	 * 
	 * @param productId
	 * @throws Exception
	 */
	
	public void deleteProduct(int productId) throws Exception{
		
		ProductValidator validator = new ProductValidator();
		validator.validateProductId(productId);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);
		
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param price 
	 * @throws Exception
	 */
	

	public void updateProduct(int id, String name, double price,String image_url, String Details) throws Exception {
		
	    ProductValidator validator = new ProductValidator();
	    validator.validateProductUpdate(id, name, price, image_url,Details); 

	    ProductDAO productDAO = new ProductDAO();
	    productDAO.updateProduct(id, name, price,image_url,Details);
	}
	
	

	

}
