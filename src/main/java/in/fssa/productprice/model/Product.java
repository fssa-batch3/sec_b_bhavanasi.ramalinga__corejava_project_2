package in.fssa.productprice.model;

 public class Product {
	 
	 int id;
	 String name;
	 int categoryId;
	 
	 public int getCategoryId() {
		return categoryId;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	boolean isActive;
	 double price;
	 
	 public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	String image_url;

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
			return "ProductId=" + id + ", name=" + name + ", categoryId=" + +categoryId + ",image_url=" + image_url +", Price=" + price + ", isActive=" + isActive + "";
		}

	

	
	
}
