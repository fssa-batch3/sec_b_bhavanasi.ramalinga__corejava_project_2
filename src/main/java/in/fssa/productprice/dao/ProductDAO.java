package in.fssa.productprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.interfaces.ProductInterface;
	
	public class ProductDAO implements ProductInterface {

	private long price;

	/**
	 * 
	 * @param product
	 */
		public void create(Product product) {
			
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				String query = "INSERT INTO products (name,categoryId,price,image_url) VALUES (?,?,?,?)";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1,product.getName());
				ps.setInt(2, product.getcategoryId());
				ps.setDouble(3,product.getPrice());
				ps.setString(4,product.getImage_url());
				
				ps.executeUpdate();
				
				System.out.println("Product created Successfully");
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException("Product is created");
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
		 */

		public void updateProduct(int productId, String name, double price, String image_url) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		        String query = "UPDATE products SET name = ? , price=? , image_url=? WHERE id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, name);
		        ps.setDouble(2,price);
		        ps.setString(3,image_url);
		        ps.setInt(4, productId);
		      
		        int rowsAffected = ps.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            System.out.println("Product with ID " + productId + " updated successfully");
		        } else {
		            System.out.println("No product found with ID " + productId);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new RuntimeException("Error updating product");
		    } finally {
		        ConnectionUtil.close(conn, ps);
		    }
		}



		/**
		 * 
		 * @param id
		 */

	
		public void deleteProduct(int id) {
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				String query = "UPDATE products SET isActive = 0 WHERE id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				System.out.println("Product deleted Successfully");
				
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
		}

	/**
	 * 
	 */
		public Set<Product> listAllProducts() {

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
					product.setImage_url(rs.getNString("image_url"));
					product.setIsActive(rs.getBoolean("isActive"));
					
					
					allProducts.add(product);
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
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
 */
	
		public Set<Product> listallProductsByCategoryId(int categoryId) {
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Set<Product> listOfProductsByCategoryId = new HashSet<>(); 
			
			try {
				String query = "SELECT * FROM products WHERE categoryId = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1,categoryId);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategoryId(rs.getInt("categoryId"));
					product.setImage_url(rs.getString("image_url"));
					product.setPrice(rs.getDouble("price"));
					
					listOfProductsByCategoryId.add(product);
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
			finally {
				ConnectionUtil.close(conn, ps);
			}
			return listOfProductsByCategoryId;
			
		}
		@Override
		public void delete(int id) {
			
			
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				String query = "DELETE FROM products WHERE id = ?";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				System.out.println("Product deleted Successfully");
				
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
		public Set<Product> listAllProductsByCategoryId(int category_id) {
			// TODO Auto-generated method stub
			return null;
		}


		public Product findProductsById(int id) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Product product = null;
		    
		    try {
		        String query = "SELECT * FROM products WHERE id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, id);
		        rs = ps.executeQuery();
		        
		        while(rs.next()) {
					 product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setCategoryId(rs.getInt("categoryId"));
					product.setImage_url(rs.getString("image_url"));
					product.setPrice(rs.getDouble("price"));
					
				}
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new RuntimeException("Error fetching product by ID", e);
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		    
		    return product;
		}

		@Override
		public void updateProduct(int productId, String name, int categoryId, long price) {
			// TODO Auto-generated method stub
			
		}


	
		

	

		
	}



