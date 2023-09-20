package in.fssa.productprice.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import day02.practice.Logger;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.dao.UserDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.util.StringUtil;

public class ProductValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";

	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */
	 public void validateProduct(Product product) throws ValidationException, PersistenceException{
		
		if(product == null) {
			throw new ValidationException("Invalid Product object input");
		}
		validateName(product.getName());
		validateCategoryId(product.getcategoryId());
		validateprice(product.getPrice());
		validateDetails(product.getDetails());
		validateImageUrl(product.getImageurl());
		validateUserId(product.getUserId());
		
	}
	
	 private void validateImageUrl(String imageUrl) {
		    if (imageUrl == null || imageUrl.isEmpty()) {
		       throw new IllegalArgumentException("Image URL is empty or null.");
		    }
		    
		  		   
		}
	 
	 private boolean validateDetails(String details) {
		    if (details == null || details.isEmpty()) {
		        return false;
		    }
		    return true;
		}
	 
	 
	  /**
	   * 
	   * @param name 
	   * @throws ValidationException
	   */
	 
	
	public  void validateName(String name) throws ValidationException {
        
        StringUtil.rejectIfInvalidString(name, "Name");
        
        if (!Pattern.matches(NAME_PATTERN, name)) {
            throw new ValidationException("Name does not match the pattern");
        }
    
    }
	
	 
	
      public static  void validateprice (double price) throws ValidationException, PersistenceException {
		
		if(price <=0) {
			
			throw new ValidationException("Price cannot be Zero or empty");
			
		}
				
	}
	

	public void validateProductId(int productId)throws ValidationException{
		
		if(productId < 1) {
			throw new ValidationException("Id cannot be negative or zero");
		}
	}
	 
	  public void validatingproductidAlreadyExist(int id)throws ValidationException, PersistenceException {
	    ProductDAO pr = new ProductDAO();
	    pr.validateProductId(id);
		
	}
	/**
	 * 
	 * @param categoryId
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */
	  public void validateCategoryId(int categoryId)throws ValidationException, PersistenceException{
		
		 if (categoryId <= 0) {
		        throw new ValidationException("Category ID cannot be negative or zero");
		    }
		 ProductDAO valide = new ProductDAO();
		 valide.validateCategoryId(categoryId);
	}
	
	
	public void validateUserId(int userId)throws ValidationException, PersistenceException{
		
		 if (userId <= 0) {
		        throw new ValidationException("userId ID cannot be negative or zero");
		    }
		 ProductDAO valide = new ProductDAO();
		 valide.validateuserId(userId);
	}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param price 
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */

	public void validateProductUpdate(int id, String name, double price,String image_url, String Details) throws ValidationException, PersistenceException {
	    if (id <= 0) {
	        throw new ValidationException(" id not be zero");
	    }

	    if (name == null || name.trim().isEmpty()) {
	        throw new ValidationException("Product name and price  cannot be null or empty");
	    }
	    
	    

	}
	
        public static void validateSellerId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Seller id connot be zero or in negative");
	      	}
		
		UserDAO userDAO = new UserDAO();
		userDAO.checkUserIsSeller(id);
		
	    }
	


}
