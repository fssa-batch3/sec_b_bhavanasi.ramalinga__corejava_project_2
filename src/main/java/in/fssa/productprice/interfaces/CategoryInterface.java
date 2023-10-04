package in.fssa.productprice.interfaces;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.model.Category;


public interface CategoryInterface {

	public abstract void create(Category category) throws PersistenceException;

	public abstract void updateName(int id, String categoryName) throws PersistenceException;

	public abstract void delete(int id) throws PersistenceException;
	public abstract List<Category>  listAllCategory() throws PersistenceException;
		
	
	
}
