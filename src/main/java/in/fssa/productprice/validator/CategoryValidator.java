package in.fssa.productprice.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.CategoryEntity;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.util.StringUtil;

public class CategoryValidator {
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */

	public static void validateCategory(Category category) throws ValidationException, PersistenceException{
		
		if (category == null) {
			throw new ValidationException("Category object can not be null");
		}
		if(category.getId() < 0) {
			throw new ValidationException("Invalid category id");
		}

		validateName(category.getName());
	}
	/**
	 * 
	 * @param Categoryname
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */
	
	public static void validateName(String Categoryname) throws ValidationException, PersistenceException{
		
		if(Categoryname == null || "".equals(Categoryname.trim())) {
			throw new ValidationException("Name cannot be null or empty");
		}
		
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.checkNameAlreadyExist(Categoryname);
		
		String regexp = "^[A-Za-z][A-Za-z\\\\\\\\s]*$";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(Categoryname);
		
		if(!matcher.matches()) {
			throw new ValidationException("Name doesn't match the pattern");
		}
		
	}
	/**
	 * 
	 * @param categoryId
	 * @throws ValidationException
	 */
	
	public static void validateId(int categoryId) throws ValidationException{
		
		if(categoryId < 0) {
			throw new ValidationException("Invalid Id");
		}
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			String query = "SELECT * FROM categories WHERE id = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, categoryId);
           rs = ps.executeQuery();
           
           if(!rs.next()) {
           	throw new ValidationException("Category id doesn't exist");
           }
           
		} catch (SQLException e) {
            
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       
       } finally {
           ConnectionUtil.close(con, ps);
       }
		
	}
	
}