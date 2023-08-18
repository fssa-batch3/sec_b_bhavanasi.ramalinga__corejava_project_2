package in.fssa.productprice.interfaces;

public abstract class productBase <T>{
	public abstract void create(T product);
	public abstract void updatePrice(int id, int price);
	public abstract void deleteProduct(int id);

}
