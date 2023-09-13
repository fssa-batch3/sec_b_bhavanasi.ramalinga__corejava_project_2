 
package in.fssa.productprice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;

import in.fssa.productprice.service.CategoryService;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.exception.ValidationException;
import org.junit.jupiter.api.Test;



public class TestCategory {
	
    @Test
    public void testCreateCategoryWithValidInput() {
        CategoryService categoryService = new CategoryService();

        Category newCategory = new Category();
        newCategory.setName("PhoneCover");
        assertDoesNotThrow(() -> {
            categoryService.create(newCategory);
        });
    }
    
    

	@Test
	 void getAll() {
	CategoryService categpryService = new CategoryService();
		Set<Category> AllCategory;
		try {
			AllCategory = categpryService.listAllCategory();
			System.out.print(AllCategory);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    @Test
     void testCreateCategoryWithInvalidInput() {
        CategoryService categoryService = new CategoryService();
        Exception exception = assertThrows(ValidationException.class, () -> {
            categoryService.create(null);
        });
        String expectedMessage = "Category object can't be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
	 void testDeleteCategory() {
		
		CategoryService categoryService = new CategoryService();

		Category newCategory = new Category();
		newCategory.setId(1);
		assertDoesNotThrow(() ->{
			categoryService.delete(newCategory.getId());
		});
    }
		
		@Test
		 void testUpdateCategoryName() {
			
			CategoryService categoryService = new CategoryService();

			Category updateCategory = new Category();
			updateCategory.setId(1);
			updateCategory.setName("Mobiles");
			assertDoesNotThrow(() ->{
				categoryService.updateCategoryName(updateCategory.getId(),updateCategory.getName());
			});
		
	}
		
		@Test
		 void testCreateUserWithNameNull() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName(null);
			
			newCategory.toString();

				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
				String expectedMessage = "Name Can not be null";
				String actualMessage = exception.getMessage();

				assertEquals(expectedMessage, actualMessage);
	}
		
		@Test
		 void testCreateUserWithNameEmpty() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName(" ");
			
			newCategory.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
				String expectedMessage = "Name can not be null";
				String actualMessage = exception.getMessage();

				assertEquals(expectedMessage, actualMessage);
	}

		@Test
		 void testCreateUserWithInvalidName() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName("9987@9@@@sj");
			
			newCategory.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
				String expectedMessage = "Name Does not match pattern";
				String actualMessage = exception.getMessage();

				assertEquals(expectedMessage, actualMessage);
	}
		
		
		
		
	}
		
		 

   

