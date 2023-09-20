package in.fssa.productprice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.Order;
import in.fssa.productprice.model.OrderEntity;
import in.fssa.productprice.model.OrderStatus;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.util.ConnectionUtil;
import in.fssa.productprice.util.Logger;

public class OrderDAO {
	
	public void create(Order newOrder) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO orders (quantity,phone_number,userId,price,Address,pincode,name,pdt_id,seller_id) "
					+ "VALUES (?, ?,'Cash_on_delivery','WAITING_LIST', ?,  ?, ?, ?, ?, ?,?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1,newOrder.getQuantity());
			ps.setLong(2, newOrder.getPhoneNumber());
			ps.setInt(3, newOrder.getUserId());
			ps.setDouble(4,newOrder.getPrice());
			ps.setString(5, newOrder.getAddress());
			ps.setInt(6, newOrder.getPincode());
			ps.setString(7,newOrder.getName());
			ps.setInt(8, newOrder.getPdtId());
		    ps.setInt(9, newOrder.getSellerId());
			
	        LocalDate orderDate = LocalDate.now(); 
	        int daysToAdd = 3; 
	        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);

	        ps.setDate(6, Date.valueOf(deliveryDate));

			ps.executeUpdate();

			System.out.println("Address has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}
	
	public void acceptOrder(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE orders SET status = 'ON_THE_WAY' WHERE is_active=1 AND order_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Order has been delivered within 3 days");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}
	
	public void cancelOrder(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE orders SET status = 'CANCELLED', is_active=0 WHERE is_active=1 AND order_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Order has been cancelled successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}
	
	public OrderEntity findByOrderId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderEntity order = null;

		try {

			String query = "SELECT quantity,phone_number, status, userId, price, Address, pincode,name,pdt_id,seller_id, delivery_date FROM orders WHERE is_active = 1 AND order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				order.setQuantity(rs.getInt("quantity"));
				order.setPhoneNumber(rs.getLong("phone_number"));

				order.setOrderId(rs.getInt("order_id"));
				String status = rs.getString("status");
				order.setStatus(status);
				order.setPrice(rs.getDouble("price"));
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				java.sql.Date orderedDateSQL = rs.getDate("ordered_date");
				
				order.setOrdereDate(orderedDateSQL.toLocalDate());
				
				java.sql.Date deliveryDateSQL = rs.getDate("delivery_date");
				
				order.setDeliveryDate(deliveryDateSQL.toLocalDate());
				
				
				order.setActive(rs.getBoolean("is_active"));
				
				order.setUserId(rs.getInt("user_id"));


			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return order;

	}
	
	
	
	public Set<OrderEntity> findOrdersByUserId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<OrderEntity> orders = new HashSet<>();

		try {

			String query = "SELECT order_id, order_date, delivery_date, phone_number, is_active, quantity, status, userId, Address, price  FROM orders WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				OrderEntity order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("order_date");
				
				order.setOrdereDate(orderedDateSQL.toLocalDate());
				
				java.sql.Date deliveryDateSQL = rs.getDate("delivery_date");
				
				order.setDeliveryDate(deliveryDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setStatus(status);
				order.setUserId(rs.getInt("userId"));
				order.setAddress(rs.getString("Address"));
				order.setPrice(rs.getDouble("price"));
				
				orders.add(order);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return orders;

	}

	
	public Set<OrderEntity> findOrdersBySellerId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<OrderEntity> orders = null;

		try {

			String query = "SELECT order_id, order_date, delivery_date, phone_number, is_active, quantity, status, userId, Address, price FROM orders WHERE seller_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				OrderEntity order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("order_date");
				
				order.setOrdereDate(orderedDateSQL.toLocalDate());
				
				java.sql.Date deliveryDateSQL = rs.getDate("delivery_date");
				
				order.setDeliveryDate(deliveryDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setStatus(status);
				order.setUserId(rs.getInt("userId"));
				order.setAddress(rs.getString("address"));
				order.setPrice(rs.getDouble("price"));
				
				orders.add(order);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return orders;

	}
	
	public void checkorderExistWithOrderId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		

		try {

			String query = "SELECT order_id, order_date, delivery_date, phone_number, is_active, quantity, status, userId, Address, price FROM orders WHERE order_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Order does not exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}
	
}
