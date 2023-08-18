package in.fssa.productprice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import in.fssa.productprice.service.CategoryService;
import org.junit.jupiter.api.Test;

import in.fssa.productprice.dao.CategoryDAO;
import in.fssa.productprice.model.Category;

public class AppTest {
	 @Test

	public void testCreateCategoryWithValidInput() {
			
			CategoryService categoryservice = new CategoryService();
			
			Category ca = new Category();
			ca.setName("Watch");
			
			assertDoesNotThrow(()->{
				categoryservice.create(ca);
			});
		}
	 
//	 @Test
//	    public void testDeleteCategoryById()  {
//	        CategoryService categoryService = new CategoryService();
//	        CategoryDAO categoryDAO = mock(CategoryDAO.class);
//	        categoryService.setCategoryDAO(categoryDAO);
//
//	        int categoryIdToDelete = 1; // Replace with the actual ID
//	        assertDoesNotThrow(() -> {
//	            categoryService.delete(categoryIdToDelete);
//	        });
//
//	       
//	    }

	    private CategoryDAO mock(Class<CategoryDAO> class1) {
		// TODO Auto-generated method stub
		return null;
	}

		private CategoryService verify(CategoryDAO categoryDAO, Object times) {
		// TODO Auto-generated method stub
		return null;
	}

		private Object times(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	}
	







