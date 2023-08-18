package in.fssa.productprice.interfaces;

import java.util.Set;

import in.fssa.productprice.model.CategoryEntity;


public interface Base<T> {
	public abstract Set<CategoryEntity> findAll();
	public abstract void create(T category);
	public void update(int id, T updatedCategory);
	public void delete(int id);
	
	
}