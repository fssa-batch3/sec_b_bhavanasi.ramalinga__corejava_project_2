package in.fssa.productprice.model;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
	int orderId;
	int quantity;
	Long phoneNumber;
	String payment;
	OrderStatus status;
	int userId;
	int pdtId;
	boolean isActive;
	LocalDate orderDate; 
	LocalDate  cancelDate; 
	String name;
	String Address;
	int pincode;
	int sellerId;
	Double price;
	String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	LocalDate  DeliveryDate;
	 
	public LocalDate getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.DeliveryDate = deliveryDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	 
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrdereDate() {
		return orderDate;
	}

	public void setOrdereDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate  getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(LocalDate  cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	
	public boolean getIsActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPdtId() {
		return pdtId;
	}
	public void setPdtId(int pdtId) {
		this.pdtId = pdtId;
	}
	 @Override
	    public String toString() {
	        return "Quantity=" + quantity + ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + ", payment=" + payment + ",status= " + status+ 
	        		",userId="+userId +", pdtId=" + pdtId;
	    }
}
