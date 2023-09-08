package in.fssa.productprice.service;

import java.util.Set;


import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.validator.CategoryValidator;



public class CategoryService {
/**
 * 
 * @param newcategory
 * @throws Exception
 */
	public void create (Category newcategory) throws ValidationException{
		
		CategoryValidator.validateCategory(newcategory);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.create(newcategory);
	}
	/**
	 * 
	 * @param categoryId
	 * @throws Exception
	 */
	public void delete(int categoryId) throws ValidationException{
		
		CategoryValidator.validateId(categoryId);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.delete(categoryId);
	}
	/**
	 * 
	 * @param id
	 * @param newCategoryName
	 * @throws Exception
	 */
	
	public void updateCategoryName(int id, String newCategoryName)throws ValidationException{
		
		CategoryValidator.validateId(id);
		CategoryValidator.validateName(newCategoryName);
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.updateName(id, newCategoryName);
		
	}
	/**
	 * 
	 * @return
	 * @throws ValidationException
	 */
	

public static  Set<Category> listAllCategory(){
		
		CategoryDAO cateogry = new CategoryDAO();
		return cateogry.listAllCategory();
	}
	
}




