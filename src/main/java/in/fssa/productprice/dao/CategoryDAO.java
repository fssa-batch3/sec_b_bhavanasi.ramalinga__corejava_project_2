package in.fssa.productprice.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.productprice.util.Logger;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.interfaces.CategoryInterface;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.model.Category;
	
public class CategoryDAO implements CategoryInterface{

	private String imageURL;

	@Override
	public void create(Category category) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO categories (name,image_url) values(?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());
			ps.setString(2, category.getImageURL());
			
			ps.executeUpdate();
			
			Logger.info("Category created Successfully");
			
		}catch(SQLException e) {
			Logger.error(e);
			
			throw new PersistenceException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	@Override
	public void updateName(int id, String categoryName) throws PersistenceException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE categories set name = ?, image_url=? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setString(2, imageURL);
			ps.setInt(3, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				Logger.info("category name updated successfully");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);	
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	@Override
	public void delete(int id) throws PersistenceException {
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE categories SET isActive = 0 WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
          Logger.info("Category deleted Successfully");
			
		}catch(SQLException e) {
			Logger.error(e);
			
			throw new PersistenceException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}

	@Override
	public Set<Category> listAllCategroyByCategoryId(int categoryId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<Category> allcategory = new HashSet<>(); 
		
		try {
			String query = "SELECT * FROM categories where isActive = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setImageURL("image_url");
				
				
				
				allcategory.add(category);
			}
		}catch(SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return allcategory ;
	}

	public Set<Category> listAllCategory() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<Category> allCategory = new HashSet<>(); 
		
		try {
			String query = "SELECT * FROM categories where isActive = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setImageURL(rs.getString("image_url"));
				category.setActive(rs.getBoolean("isActive"));
				
				
				allCategory.add(category);
			}
		}catch(SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return allCategory;
	}
	
	public void checkNameAlreadyExist(String name) throws PersistenceException, ValidationException {
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
		try {
			String query = "SELECT name FROM categories WHERE name = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setString(1, name);
           rs = ps.executeQuery();
           
           if(rs.next()) {
        	   throw new ValidationException("Asset Id doesn't exist");
           }
           
		} catch (SQLException e) {
           
			Logger.error(e);
			throw new PersistenceException(e);
       
       } finally {
           ConnectionUtil.close(con, ps);
       }
	}

}









