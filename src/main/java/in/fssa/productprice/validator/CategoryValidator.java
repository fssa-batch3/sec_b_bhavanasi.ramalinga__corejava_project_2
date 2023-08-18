package in.fssa.productprice.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.CategoryEntity;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.util.StringUtil;

public class CategoryValidator {

	public static void validateCategory(Category category) throws ValidationException{
		
		if (category == null) {
			throw new ValidationException("Invalid category input");
		}
		if(category.getId() < 0) {
			throw new ValidationException("Invalid category id");
		}
		
		//Business validation
		
		 Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
		try {
			String query = "SELECT * FROM category WHERE name = ?";
			con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, category.getName());
            rs = ps.executeQuery();
            
            if(rs.next()) {
            	throw new ValidationException("Category name already exist");
            }
            
		} catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        
        } finally {
            ConnectionUtil.close(con, ps);
        }

		validateName(category.getName());
	}
	
	public static void validateName(String Categoryname) throws ValidationException{
		
		if(Categoryname == null || "".equals(Categoryname.trim())) {
			throw new ValidationException("Name cannot be null or empty");
		}
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			String query = "SELECT * FROM category WHERE name = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setString(1, Categoryname);
           rs = ps.executeQuery();
           
           if(rs.next()) {
           	throw new ValidationException("Category name already exist");
           }
           
		} catch (SQLException e) {
           
           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       
       } finally {
           ConnectionUtil.close(con, ps);
       }
		
		
		String regexp = "^[A-Za-z][A-Za-z\\\\\\\\s]*$";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(Categoryname);
		
		if(!matcher.matches()) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	}
	
	public static void validateId(int categoryId) throws ValidationException{
		
		if(categoryId < 0) {
			throw new ValidationException("Invalid Id");
		}
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			String query = "SELECT * FROM category WHERE id = ?";
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