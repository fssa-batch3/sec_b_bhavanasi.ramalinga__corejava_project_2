package in.fssa.productprice.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.interfaces.CategoryInterface;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.model.Category;
	
public class CategoryDAO implements CategoryInterface{

	private String imageURL;

	@Override
	public void create(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO categories (name,image_url) values(?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());
			ps.setString(2, category.getImageURL());
			
			ps.executeUpdate();
			
			System.out.println("Category created Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	@Override
	public void updateName(int id, String categoryName) {
		
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
				System.out.println("category name updated successfully");
			}
		} catch (SQLException e) {
			throw new RuntimeException();	
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	@Override
	public void delete(int id) {
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE categories SET isActive = 0 WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Category deleted Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}

	@Override
	public Set<Category> listAllCategroyByCategoryId(int categoryId) {
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return allcategory ;
	}

	public Set<Category> listAllCategory() {
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return allCategory;
	}
	
	public void checkNameAlreadyExist(String name) {
		
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
           	throw new RuntimeException("Category name already exist");
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









