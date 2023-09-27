package in.fssa.productprice.service;

import java.util.List;
import java.util.Set;


import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.validator.CategoryValidator;



public class CategoryService {
/**
 * 
 * @param newcategory
 * @throws PersistenceException 
 * @throws Exception
 */
	public void create (Category newcategory) throws ValidationException, PersistenceException{
		
		CategoryValidator.validateCategory(newcategory);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.create(newcategory);
	}
	/**
	 * 
	 * @param categoryId
	 * @throws PersistenceException 
	 * @throws Exception
	 */
	public void delete(int categoryId) throws ValidationException, PersistenceException{
		
		CategoryValidator.validateId(categoryId);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.delete(categoryId);
	}
	/**
	 * 
	 * @param id
	 * @param newCategoryName
	 * @throws PersistenceException 
	 * @throws Exception
	 */
	
	public void updateCategoryName(int id, String newCategoryName)throws ValidationException, PersistenceException{
		
		CategoryValidator.validateId(id);
		CategoryValidator.validateName(newCategoryName);
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.updateName(id, newCategoryName);
		
	}
	/**
	 * 
	 * @return
	 * @throws PersistenceException 
	 * @throws ValidationException
	 */
	

public static  List<Category> listAllCategory() throws PersistenceException{
		
		CategoryDAO cateogry = new CategoryDAO();
		return cateogry.listAllCategory();
	}
	
}




