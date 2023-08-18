package in.fssa.productprice.service;

import java.util.Set;

import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.dao.ProductDAO;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.validator.CategoryValidator;



public class CategoryService {

	public void create (Category newcategory) throws Exception{
		
		CategoryValidator.validateCategory(newcategory);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.create(newcategory);
	}
	
	public void delete(int categoryId) throws Exception{
		
		CategoryValidator.validateId(categoryId);
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		categoryDao.delete(categoryId);
	}
	
	public void updateCategoryName(int id, String newCategoryName)throws Exception{
		
		CategoryValidator.validateId(id);
		CategoryValidator.validateName(newCategoryName);
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.updateName(id, newCategoryName);
		
	}

public Set<Category> listAllCategory()throws Exception{
		
		CategoryDAO cateogry = new CategoryDAO();
		Set<Category> allCategory = cateogry.listAllCategory();
		
		return allCategory;
	}
}




