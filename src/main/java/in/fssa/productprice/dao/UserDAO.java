package in.fssa.productprice.dao;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.interfaces.UserInterface;
import in.fssa.productprice.model.UserEntity;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAO implements UserInterface<UserEntity> {
	/**
	 * @return
	 */
	   
	@Override 
	public Set<UserEntity> findAll() throws PersistenceException {
		
		Set<UserEntity> userList = new HashSet<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			
			String query = "SELECT id, email, name,  phoneNumber, password, role, Address isActive FROM users WHERE isActive=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				UserEntity user = new UserEntity();
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getLong("phoneNumber"));
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setAddress(rs.getString("Address"));
				userList.add(user);
				
			}
			
		} catch (SQLException e) {
			
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;
		
	}
	
	/**
	 * @param newUser
	 */

	@Override
	public void create(UserEntity newuser) throws PersistenceException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO users (email, name, phoneNumber, password, role , Address) VALUES (?, ?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1,  newuser.getEmail());
			ps.setString(2,  newuser.getName());
			ps.setLong(3, newuser.getPhoneNumber());
			ps.setString(4, newuser.getPassword());
			ps.setString(5, newuser.getRole());
			ps.setString(6, newuser.getAddress());
		
			ps.executeUpdate();
			System.out.println("User has been created successfully");
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(connection, ps);
		}

		
	}
	
	/**
	 *  @param id, updatedUser
	 */

	@Override
	public void update(int id, UserEntity updatedUser) throws PersistenceException {
		
		    Connection conn = null;
		    PreparedStatement ps = null;
		    
		    try {
		    
		    	StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
		        List<Object> values = new ArrayList<>();
		        
		        if (updatedUser.getName() != null) {
		            queryBuilder.append("name = ?, ");
		            values.add(updatedUser.getName());
		        }
		        
		        if (updatedUser.getPassword() != null) {
		            queryBuilder.append("password = ?, ");
		            values.add(updatedUser.getPassword());
		        }
		        
		        if (updatedUser.getEmail() != null) {
		            queryBuilder.append("email = ?, ");
		            values.add(updatedUser.getEmail());
		        }
		       
		        queryBuilder.setLength(queryBuilder.length() - 2);
		        queryBuilder.append(" WHERE isActive = 1 AND id = ?");
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(queryBuilder.toString());
		       
		        for (int i = 0; i < values.size(); i++) {
		            ps.setObject(i + 1, values.get(i));
		        }
		        ps.setInt(values.size() + 1, id);
		        ps.executeUpdate();
		        System.out.println("User has been updated successfully");
		   
		    } catch (SQLException e) {
		       Logger.error(e);
		        throw new PersistenceException(e.getMessage());
		   
		    } finally {
		   
		    	ConnectionUtil.close(conn, ps);
		    
		    }
		    
		}
	
	/**
	 * @param id
	 */
		

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE users SET isActive = 0 WHERE isActive = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("User has been deleted successfully");
			
		} catch(SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}
	
	/**
	 * @param id
	 */

	@Override
	public UserEntity findById(int id) throws PersistenceException {

		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;
		
		try {
			
			String query = "SELECT id, email, name,  phoneNumber, password, role, Address, isActive FROM users WHERE isActive = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getLong("phoneNumber"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setAddress(rs.getString("Address"));
			}
			
		} catch (SQLException e) {
		    Logger.error(e);
	        throw new PersistenceException(e.getMessage());
	   
	    } finally {
	   
	    	ConnectionUtil.close(con, ps, rs);
	   
	    }
		
		return user;
		
	}

	public void checkUserExists(String email) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity user= null;
		
		try {
			
			String query = "SELECT email ,id FROM users WHERE isActive=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				throw new ValidationException("This user is already exist");
			}
		
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public void checkUserExistsForUpdate(String email) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity user= null;
		
		try {
			
			String query = "SELECT email,id FROM users WHERE isActive=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(!rs.next()) {
				throw new ValidationException("User does not exist");
			}
		
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public void checkUserExistsWithId(int id) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity user= null;
		
		try {
			
			String query = "SELECT id, name, phoneNumber, email,password, role, Address, isActive FROM users WHERE isActive=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getLong("phoneNumber"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setAddress(rs.getString("Address"));
				
			} else {
				throw new ValidationException("User does not exist");
			}
		
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public void checkUserExistsWithPhoneNumberForUpdate(long phoneNumber) throws PersistenceException, ValidationException {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	UserEntity user= null;
	
	try {
		
		String query = "SELECT id,  name, phoneNumber, email, password, role, Address, isActive FROM users WHERE isActive=1 AND phoneNumber=?";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			user = new UserEntity();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPhoneNumber(rs.getLong("phoneNumber"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setAddress(rs.getString("Address"));
			
		} else {
			throw new ValidationException("User does not exist");
		}
	
	} catch (SQLException e) {
		Logger.error(e);
		throw new PersistenceException(e.getMessage());
	
	} finally {
		
		ConnectionUtil.close(con, ps, rs);
		
	}
	
	}
	
	public void checkUserExistsWithPhoneNumber(long phoneNumber) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity user= null;
		
		try {
			
			String query = "SELECT phoneNumber FROM users WHERE isActive=1 AND phoneNumber=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNumber);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				throw new ValidationException("This user is already exist");
			}
		
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	public void checkUserIsSeller(int id) throws PersistenceException, ValidationException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UserEntity user= null;
		
		try {
			
			String query = "SELECT role FROM users WHERE isActive=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new UserEntity();
				user.setRole(rs.getString("role"));
				
			}
			
			if("buyer".equalsIgnoreCase(user.getRole())) {
				throw new ValidationException("Seller does not exist");
			}
		
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());
		
		} finally {
			
			ConnectionUtil.close(con, ps, rs);
			
		}
		
	}
	
	
	public UserEntity checkUserExistsWithPhoneNumberForLogin(long phoneNumber) throws PersistenceException, ValidationException {
		
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	UserEntity user= null;
	
	try {
		
		String query = "SELECT id, phoneNumber, password, role , Address FROM users WHERE isActive=1 AND phoneNumber=?";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			user = new UserEntity();
			user.setId(rs.getInt("id"));
			user.setPhoneNumber(rs.getLong("phoneNumber"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setAddress(rs.getString("Address"));
		} else {
			throw new ValidationException("User does not exist");
		}
	
	} catch (SQLException e) {
		Logger.error(e);
		throw new PersistenceException(e.getMessage());
	
	} finally {
		
		ConnectionUtil.close(con, ps, rs);
		
	}
	return user;
	
	}
	
}
	
	
	


