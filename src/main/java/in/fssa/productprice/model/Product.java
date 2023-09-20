package in.fssa.productprice.model;

 public class Product {
	 
	 int id;
	 String name;
	 int categoryId;
	 boolean isActive;
	 double price;
	 String imageurl;
	 int userId;
	 String details;
	 
	 public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}




	
	 public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
	 

	
	 
	 public int getCategoryId() {
		return categoryId;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	 
	 public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	

	 public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getcategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	 @Override
		public String toString() {
			return "ProductId=" + id + ", name=" + name  +" ,Details" + details + " , categoryId="  +categoryId + ",image_url=" + imageurl +", Price=" + price + ", isActive=" + isActive + "";
		}

	

	
	
}
