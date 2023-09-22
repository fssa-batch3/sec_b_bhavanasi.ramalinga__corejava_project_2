package in.fssa.productprice.model;

import java.time.LocalDate;

public class User  implements Comparable<User>{
	private int id;
	private String email;
	private String name;
	private long phoneNumber;
	private String password;
	private boolean isActive;
	private String role;
	private String address;
	private int pincode;
	 

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}	
	
	
	 
	@Override
	public int compareTo(User u) {
			
		if (this.getId() == u.getId()) {
			return 0;
		} else {
			if (this.id>getId()) {
				return 1;
			} else {
				return -1;
			}
			
		} 
		
	}
	
	
	@Override
	public String toString() {
	    return "User [id=" + id + ", email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber +
	    		 ", Address=" + address   + ", password=" + password  +" ]";
	}


	

}
