package in.fssa.productprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;
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
				String query = "INSERT INTO products (name,categoryId,price,image_url,Details) VALUES (?,?,?,?,?)";
				conn = ConnectionUtil.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1,product.getName());
				ps.setInt(2, product.getcategoryId());
				ps.setDouble(3,product.getPrice());
				ps.setString(4,product.getImageurl());
				ps.setString(5, product.getDetails());
				
				ps.executeUpdate();
				
				System.out.println("Product created Successfully");
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException("Product is not created");
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

		public void updateProduct( int productId, String name, double price, String imageurl, String details) {
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
					product.setImageurl(rs.getNString("image_url"));
					product.setDetails(rs.getString("Details"));
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
				String query = "UPDATE products SET isActive=0 WHERE isActive=1 AND id=?";
				
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
		        e.printStackTrace();
		        throw new RuntimeException("Error fetching product by ID", e);
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		    
		    return product;
		}
		
		
		
		@Override
		public Product findProductDetailsByProductId(int id){
			
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
//				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			finally {
				ConnectionUtil.close(conn, ps, rs);
			}
			return product;
			}
		

		@Override
		public void updateProduct(int productId, String name, int categoryId, long price) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void ProductfindProductDetailsByProductId(int id) {
			// TODO Auto-generated method stub
			
		}


	public void checkProductAlreadyExist(String name) {
		
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
      	  throw new RuntimeException("product already exists");		
        }
		}catch (SQLException e) {
//        e.printStackTrace();
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    
    } finally {
        ConnectionUtil.close(con, ps);
    }
	}
	
		
public void validateproductid(int id) {
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
       	System.out.println("product exists");
       }else {
       	throw new RuntimeException("product doesn't exist");
       }		
	} catch (SQLException e) {
		
//       e.printStackTrace();
       System.out.println(e.getMessage());
       throw new RuntimeException(e);
   
   } finally {
       ConnectionUtil.close(con, ps);
   }
}
	public void validateCategoryId(int categoryId) {
		
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
           	System.out.println("category exists");
           }else {
           	throw new RuntimeException("category doesn't exist");
           }		
		} catch (SQLException e) {
			
//           e.printStackTrace();
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
         
       } finally {
           ConnectionUtil.close(con, ps);
       }
	}

		
	}



