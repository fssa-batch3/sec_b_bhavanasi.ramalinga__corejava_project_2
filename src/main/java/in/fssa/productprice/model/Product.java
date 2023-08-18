package in.fssa.productprice.model;

 public class Product {
	 
	 int id;
	 String name;
	 int category_id;
	 boolean isActive;

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
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	 @Override
		public String toString() {
			return "Product id=" + id + ", name=" + name + ", category_id=" + category_id + ", isActive=" + isActive + "";
		}
	
}
