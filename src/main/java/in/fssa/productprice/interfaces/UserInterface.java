package in.fssa.productprice.interfaces;
import java.util.*;
import in.fssa.productprice.exception.PersistenceException;

public interface UserInterface<T> {
	public abstract Set<T> findAll() throws PersistenceException;
	public abstract void create(T newObject) throws PersistenceException;
	public abstract void update(int id, T updatedObject) throws PersistenceException;
	public abstract void delete(int id) throws PersistenceException;
	public abstract T findById(int id) throws PersistenceException; 

}
