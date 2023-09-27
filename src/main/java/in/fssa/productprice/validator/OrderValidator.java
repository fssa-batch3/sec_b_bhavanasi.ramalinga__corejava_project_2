package in.fssa.productprice.validator;

import java.util.regex.Pattern;

import in.fssa.productprice.dao.OrderDAO;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.dao.UserDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Order;
import in.fssa.productprice.model.OrderEntity;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.util.StringUtil;

public class OrderValidator {
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";
	  private static final String ADDRESS_PATTERN = "^[A-Za-z0-9\\s.,'-]*$"; 
	  
	
	     public static void validateOrder(OrderEntity order) throws ValidationException, PersistenceException{
			

				if (order == null) {
					throw new ValidationException("Invalid order input");
				}
				
				
				UserValidator.validatePhoneNumber(order.getPhoneNumber());

				validateQuantity(order.getQuantity());
				validatepincode(order.getPincode());
				
				UserValidator.CheckUserExistsWithId(order.getUserId());
				
				ProductValidator.validateprice(order.getPrice());
				
			 	validateAddress(order.getAddress());
			 	
				 validateUserName(order.getUserName());
				ProductValidator.validateSellerId(order.getSellerId());
				
			}
	 
	 
	 
	 
	 
	       private static void validateUserName(String userName) throws ValidationException {
		      if(userName == null) {
		    	  throw new ValidationException("Name can not be empty");
		      }
		      if (userName.trim().isEmpty()) {
			        throw new ValidationException("Name cannot be empty");
			    }
		
	}
	       
	       
	       
	       
	    public static void validatepincode(int i) throws ValidationException {
	    String pincodeStr = String.valueOf(i);
	    String regex = "\\d{6}";

	    if (!pincodeStr.matches(regex) || pincodeStr.length() != 6) {
	        throw new ValidationException("PIN code should be exactly 6 digits and contain only numbers");
	    }
	    
	    if (pincodeStr.trim().isEmpty()) {
	        throw new ValidationException("pincode cannot be empty");
	    }
	}
	       
	  
	    

		  
	 private static void validateAddress(String address) throws ValidationException {
		    if (address == null) {
		        throw new ValidationException("Address cannot be null");
		    }

		    if (address.trim().isEmpty()) {
		        throw new ValidationException("Address cannot be empty");
		    }
	 }
	 
		

			public static void validateQuantity(int quantity) throws ValidationException {

				if (quantity <= 0 || quantity > 10) {
					throw new ValidationException("Quantity cannot be less than or equal to zero or greater than 10");
				}

			}
			
			
			
			
			public static void validateOrderId(int id) throws ValidationException, PersistenceException {

				if (id <= 0) {
					throw new ValidationException("Order id cannot be zero or in negative");
				}

				OrderDAO orderDAO = new OrderDAO();
				orderDAO.checkorderExistWithOrderId(id);

			}





			
		 
		 

}
