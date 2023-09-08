package in.fssa.productprice.interfaces;

import java.util.Set;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.model.Category;


public interface CategoryInterface {

	public abstract void create(Category category) throws PersistenceException;

	public abstract void updateName(int id, String categoryName) throws PersistenceException;

	public abstract void delete(int id) throws PersistenceException;

	public abstract Set<Category> listAllCategroyByCategoryId(int categoryId) throws PersistenceException;
	
	
}
