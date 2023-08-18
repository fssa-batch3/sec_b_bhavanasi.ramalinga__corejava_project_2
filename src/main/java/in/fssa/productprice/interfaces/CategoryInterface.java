package in.fssa.productprice.interfaces;

import java.util.Set;

import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.CategoryEntity;

public interface CategoryInterface {

	void create(Category category);

	void updateName(int id, String categoryName);

	void delete(int id);

	Set<Category> listAllCategroyByCategoryId(int categoryId);
	
	
}
