 
package in.fssa.productprice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import in.fssa.productprice.service.CategoryService;
import in.fssa.productprice.service.ProductService;
import in.fssa.productprice.model.Category;
import in.fssa.productprice.model.Product;
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
	public void getAll() {
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
    public void testCreateCategoryWithInvalidInput() {
        CategoryService categoryService = new CategoryService();
        Exception exception = assertThrows(ValidationException.class, () -> {
            categoryService.create(null);
        });
        String expectedMessage = "Category object can not be null";
        String actualMessage = exception.getMessage();
        System.out.println(expectedMessage);
        assertTrue(actualMessage.contains(expectedMessage)); 
        
    }
    
    @Test
	public void testDeleteCategory() {
		
		CategoryService categoryService = new CategoryService();

		Category newCategory = new Category();
		newCategory.setId(1);
		assertDoesNotThrow(() ->{
			categoryService.delete(newCategory.getId());
		});
    }
		
		@Test
		public void testUpdateCategoryName() {
			
			CategoryService categoryService = new CategoryService();

			Category updateCategory = new Category();
			updateCategory.setId(1);
			updateCategory.setName("Mobiles");
			assertDoesNotThrow(() ->{
				categoryService.updateCategoryName(updateCategory.getId(),updateCategory.getName());
			});
		
	}
		
		@Test
		public void testCreateUserWithNameNull() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName(null);
			
			newCategory.toString();

				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
		
		@Test
		public void testCreateUserWithNameEmpty() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName(" ");
			
			newCategory.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

		@Test
		public void testCreateUserWithInvalidName() {
			
			CategoryService categoryService = new CategoryService();
			
			Category newCategory = new Category();
			
			newCategory.setName("9987@9@@@sj");
			
			newCategory.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
					categoryService.create(newCategory);
		});
		String expectedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
		
	}
		
		

   

