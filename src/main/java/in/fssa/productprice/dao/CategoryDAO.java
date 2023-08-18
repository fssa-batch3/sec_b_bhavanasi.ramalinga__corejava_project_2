package in.fssa.productprice.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


import in.fssa.productprice.interfaces.CategoryInterface;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.CategoryEntity;
import in.fssa.productprice.model.Product;




	
public class CategoryDAO implements CategoryInterface{

	@Override
	public void create(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO category (name) values(?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());
			
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
		  // TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE category set name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setInt(2, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("category name updated successfully");
			}
		} catch (SQLException e) {
			throw new RuntimeException();	
		}
	}

	@Override
	public void delete(int id) {
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "DELETE FROM category WHERE id = ?";
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
			String query = "SELECT * FROM product";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				
				
				
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
			String query = "SELECT * FROM category";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
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

}









