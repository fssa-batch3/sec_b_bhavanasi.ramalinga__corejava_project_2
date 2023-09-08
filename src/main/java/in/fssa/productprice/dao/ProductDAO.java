package in.fssa.productprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import day02.practice.Logger;

import java.util.HashSet;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.interfaces.ProductInterface;
	
	public class ProductDAO implements ProductInterface {

 
	/**
	 * 
	 * @param product
	 * @throws PersistenceException 
	 */
		public void create(Product product) throws PersistenceException {
			
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				String query = "INSERT INTO products (name,categoryId,price,image_url,Details) VALUES (?,?,?,?,?)";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1,product.getName());
				ps.setInt(2, product.getcategoryId());
				ps.setDouble(3,product.getPrice());
				ps.setString(4,product.getImageurl());
				ps.setString(5, product.getDetails());
				
				ps.executeUpdate();
				
				Logger.info("Product created Successfully");
				
			}catch(SQLException e) {
				Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
		}
		
		/**
		 * 
		 * @param productId
		 * @param name
		 * @param image_url 
		 * @throws PersistenceException 
		 */

		public void updateProduct( int productId, String name, double price, String imageurl, String details) throws PersistenceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		        String query = "UPDATE products SET name = ? , price=? , image_url=?, Details=? WHERE id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, name);
		        ps.setDouble(2,price);
		        ps.setString(3,imageurl);
		        ps.setString(4, details);
		        ps.setInt(5, productId);
		       
		      
		        int rowsAffected = ps.executeUpdate();
		        
		        if (rowsAffected > 0) {
		           Logger.info("Product with ID " + productId + " updated successfully");
		        } else {
		           Logger.info("No product found with ID " + productId);
		        }
		    } catch (SQLException e) {
		    	Logger.error(e);
				throw new PersistenceException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps);
		    }
		}



		/**
		 * 
		 * @param id
		 * @throws PersistenceException 
		 */

	
		public void deleteProduct(int id) throws PersistenceException {
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				String query = "UPDATE products SET isActive = 0 WHERE id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				Logger.info("Product deleted Successfully");
				
			}catch(SQLException e) {
				
				Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
		}

	/**
	 * @throws PersistenceException 
	 * 
	 */
		public Set<Product> listAllProducts() throws PersistenceException {

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Set<Product> allProducts = new HashSet<>(); 
			
			try {
				String query = "SELECT * FROM products WHERE isActive = 1" ;
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategoryId(rs.getInt("categoryId"));
					product.setPrice(rs.getDouble("price"));
					product.setImageurl(rs.getNString("image_url"));
					product.setDetails(rs.getString("Details"));
					product.setIsActive(rs.getBoolean("isActive"));
					
					
					allProducts.add(product);
				}
			}catch(SQLException e) {
				Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			return allProducts;
		}
/**
 * 
 * @param category_id
 * @return
 * @throws PersistenceException 
 */
	
		public Set<Product> listallProductsByCategoryId(int categoryId) throws PersistenceException {
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Set<Product> listOfProductsByCategoryId = new HashSet<>(); 
			
			try {
				String query = "SELECT * FROM products WHERE isActive=1 and categoryId = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1,categoryId);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategoryId(rs.getInt("categoryId"));
					product.setImageurl(rs.getString("image_url"));
					product.setPrice(rs.getDouble("price"));
					product.setDetails(rs.getString("Details"));
					
					listOfProductsByCategoryId.add(product);
				}
			}catch(SQLException e) {
				Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			return listOfProductsByCategoryId;
			
		}
		@Override
		public void delete(int id) throws PersistenceException {
			
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				String query = "UPDATE products SET isActive=0 WHERE isActive=1 AND id=?";
				
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				Logger.info("Product deleted Successfully");
				
			}catch(SQLException e) {
				Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			
		}

		


		public Product findProductsById(int id) throws PersistenceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Product product = null;
		    
		    try {
		        String query = "SELECT * FROM products WHERE isActive = 1 AND id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, id);
		        rs = ps.executeQuery();
		        
		        while(rs.next()) {
					 product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategoryId(rs.getInt("categoryId"));
					product.setImageurl(rs.getString("image_url"));
					product.setPrice(rs.getDouble("price"));
					product.setDetails(rs.getString("Details"));
					
				}
		    } catch (SQLException e) {
		    	Logger.error(e);
				throw new PersistenceException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		    
		    return product;
		}
		
		
		
		@Override
		public Product findProductDetailsByProductId(int id) throws PersistenceException{
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Product product = null;
			
			try {
				String query = "SELECT * FROM products WHERE isActive = 1 AND id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, id);
		        rs = ps.executeQuery();
				
			} catch(SQLException e) {
         		Logger.error(e);
				throw new PersistenceException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps, rs);
			}
			return product;
			}
		

	


	public void checkProductAlreadyExist(String name) throws PersistenceException, ValidationException {
		
		Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     
		try {
			String query = "SELECT * FROM products WHERE name = ?";
			con = ConnectionUtil.getConnection();
        ps = con.prepareStatement(query);
        ps.setString(1, name);
        rs = ps.executeQuery();
        
        if(rs.next()) {
      	  throw new ValidationException("product already exists");		
        }
		}catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
    
    } finally {
        ConnectionUtil.close(con, ps);
    }
	}
	
		
public void validateproductid(int id) throws PersistenceException, ValidationException {
	 Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     
	try {
		String query = "SELECT * FROM products WHERE id = ?";
		con = ConnectionUtil.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, id);
       rs = ps.executeQuery();
       
       if(rs.next()) {
       Logger.info("product exists");
       }else {
       	throw new ValidationException("product doesn't exist");
       }		
	} catch (SQLException e) {
		Logger.error(e);
		throw new PersistenceException(e);
   
   } finally {
       ConnectionUtil.close(con, ps);
   }
}
	public void validateCategoryId(int categoryId) throws PersistenceException, ValidationException {
		
		 Connection con = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     
		try {
			String query = "SELECT * FROM categories WHERE id = ?";
			con = ConnectionUtil.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, categoryId);
           rs = ps.executeQuery(); 
           
           if(rs.next()) {
           Logger.info("category exists");
           }else {
           	throw new ValidationException("category doesn't exist");
           }		
		} catch (SQLException e) {
			
			Logger.error(e);
			throw new PersistenceException(e);
         
       } finally {
           ConnectionUtil.close(con, ps);
       }
	}

	@Override
	public Set<Product> listAllProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(int productId, String name, int categoryId, long price) {
		// TODO Auto-generated method stub
		
	}

	

		
	}



