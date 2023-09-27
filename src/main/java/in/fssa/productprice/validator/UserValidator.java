package in.fssa.productprice.validator;

import in.fssa.productprice.dao.UserDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.UserEntity;
import java.util.regex.Pattern;
import in.fssa.productprice.util.StringUtil;

public class UserValidator {

	private static final String NAME_PATTERN = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
	
	
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	
	public static void validatePincode(int i) throws ValidationException {
	    String pincodeStr = String.valueOf(i);
	    String regex = "\\d{6}";

	    if (!pincodeStr.matches(regex) || pincodeStr.length() != 6) {
	        throw new ValidationException("PIN code should be exactly 6 digits and contain only numbers");
	    }
	    
	    
	    if (pincodeStr.trim().isEmpty()) {
	        throw new ValidationException("Pincode cannot be empty");
	    }
	    
	}



	       public static void validate(UserEntity user) throws ValidationException {

		    if (user == null) { 
			throw new ValidationException("Invalid user input");
		     }  
			
			validateName(user.getName());
			validateEmail(user.getEmail());
			validatePhoneNumber(user.getPhoneNumber());
			validatePassword(user.getPassword()); 
			validatePincode(user.getPincode());
			validateAddress(user.getAddress());  
			
		} 
		
	/**  
	 * 
	 * @param phoneNumber
	 * @throws ValidationException
	 */
	
		 static void validatePhoneNumber(long phoneNumber) throws ValidationException{
			
			String phno = String.valueOf(phoneNumber);
			
			if(phno.length()!=10) { 
				throw new ValidationException("Invalid phone number");
			}
			
			if(phoneNumber <= 6000000000l || phoneNumber >= 9999999999l) {
				throw new ValidationException("Invalid phone number");
			} 
	  }
		 
		 
		 
		
		/**
		 * 
		 * @param name
		 * @throws ValidationException
		 */
		
		   public static void validateName(String name) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(name, "Name");
			
			if (!Pattern.matches(NAME_PATTERN, name)) {
				throw new ValidationException("Name doesn't match the pattern");
			}
			 if (name.trim().isEmpty()) {
		            throw new ValidationException("Name cannot be empty");
		        }
		
		    }
		
		/**
		 * 
		 * @param email
		 * @throws ValidationException
		 */
		
		   public static void validateEmail(String email) throws ValidationException {
			    StringUtil.rejectIfInvalidString(email, "Email");

			    if (!Pattern.matches(EMAIL_PATTERN, email)) {
			        throw new ValidationException("Email doesn't match the pattern");
			    }

			    if (email.toUpperCase().equals(email)) {
			        throw new ValidationException("Email should not be fully uppercase");
			    }
			}


          public static void CheckUserExists(String email) throws ValidationException, PersistenceException {
			
			UserDAO userdao = new UserDAO();
			userdao.checkUserExists(email);
			
		    }
          
		
		  public static void CheckUserExistsWithPhoneNumber(long phoneNumber) throws ValidationException, PersistenceException {
			
			UserDAO userdao = new UserDAO();
			userdao.checkUserExistsWithPhoneNumber(phoneNumber);
			
	    	}
		
		   public static void CheckUserExistsWithId(int id) throws ValidationException, PersistenceException {
			
			if(id<=0) {
				throw new ValidationException("Invalid user id");
			}
			
			UserDAO userdao = new UserDAO();
			userdao.checkUserExistsWithId(id); 
			
	    	}
		
		  public static void CheckUserExistsForUpdate(String email) throws ValidationException, PersistenceException {
			
			UserDAO userdao = new UserDAO();
			userdao.checkUserExistsForUpdate(email);
			
	     	}
		
		  public static void CheckUserExistsWithPhoneNumberForUpdate(long phoneNumber) throws ValidationException, PersistenceException {
			
			UserDAO userdao = new UserDAO();
			userdao.checkUserExistsWithPhoneNumberForUpdate(phoneNumber);
			
		}
		  
		  
		
		/**
		 * 
		 * @param password
		 * @throws ValidationException
		 */
		
		    public static void validatePassword(String password) throws ValidationException {
			
			StringUtil.rejectIfInvalidString(password, "Password");
			
			if(password.length()<8) {
				throw new ValidationException("Password must contain atleast 8 characters");
			}
			
			if (!Pattern.matches(PASSWORD_PATTERN, password)) {
		        throw new ValidationException("Password must match the pattern: " + PASSWORD_PATTERN);
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

	 
	}

		

