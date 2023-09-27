package in.fssa.productprice.service;

import java.util.List;
import java.util.Set;

import in.fssa.productprice.dao.OrderDAO;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ServiceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Order;
import in.fssa.productprice.model.OrderEntity;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.validator.OrderValidator;
import in.fssa.productprice.validator.ProductValidator;
import in.fssa.productprice.validator.UserValidator;


public class OrderService {
	
	 public void create(OrderEntity newOrder) throws ServiceException, ValidationException {

		try {
			OrderValidator.validateOrder(newOrder);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.create(newOrder);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	 public static void orderDelivered(int id) throws ServiceException, ValidationException {

			try {

				OrderValidator.validateOrderId(id);

				OrderDAO orderDAO = new OrderDAO();
				orderDAO.orderDelivered(id);

			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		}
	 
	
	public static void acceptOrder(int id) throws ServiceException, ValidationException {

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.acceptOrder(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	
	public static void cancelOrder(int id) throws ServiceException, ValidationException {

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.cancelOrder(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	

	
	public static OrderEntity findOrderByOrderId(int id) throws ServiceException, ValidationException {

		OrderEntity order = null;

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			order = orderDAO.findByOrderId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return order;

	}
	
	
	
	
	
	public static List<OrderEntity> findOrdersByUserId(int id) throws ServiceException, ValidationException {

		List<OrderEntity> orderList = null;

		try {

			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findOrdersByUserId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderList;

	} 
	
	
	public static List<OrderEntity> findOrdersBySellerId(int id) throws ServiceException, ValidationException {

		List<OrderEntity> orderList = null;

		try {

			ProductValidator.validateSellerId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findOrdersBySellerId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderList;

	}
}
