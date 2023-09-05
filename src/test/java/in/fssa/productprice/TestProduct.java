package in.fssa.productprice;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Set;

import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.model.ProductEntity;
import in.fssa.productprice.service.ProductService;

public class TestProduct {
	
	
	@Test
	public void getAllProductsByCategoryId() {
	    ProductService productService = new ProductService();
	    Set<Product> products;
	    try {
	        products = productService.findProductDetailByCategoryId(1);
	        System.out.println(products);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	@Test
	public void getAll() {
		ProductService productService = new ProductService();
		Set<Product> AllProducts;
		try {
			AllProducts = productService.listAllProduct();
			System.out.print(AllProducts);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public  void testCreateProductWithValidInput() {
		
		ProductService productService = new ProductService();
		
		Product product = new Product();
		product.setName("Chairs");
		product.setPrice(100000);
		product.setCategoryId(1);
		
		assertDoesNotThrow(() ->{
			productService.createProduct(product);
		});
	}
	
	@Test    
	public void testCreateProductWithInvalidInput() {
		
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () ->{
			productService.createProduct(null);
		});
		String expectedMessage = "Invalid Product object input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		System.out.println(actualMessage);
	}
	
	@Test
	public void testCreateProductWithNameNull() {
		
		ProductService productService = new ProductService();
		
		Product product = new Product();
		
		product.setName(null);
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	@Test
	public void testCreateProductWithNameEmpty() {
		
		ProductService productService = new ProductService();
		
		Product product = new Product();
		
		product.setName(" ");
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	@Test
	public void testCreateProductWithInvalidName() {
		
		ProductService productService = new ProductService();
		
		Product product = new Product();
		
		product.setName("ni@34524fd7365");
		
		
		product.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "Name does not match the pattern";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	//delete//
	@Test
	public  void testDeleteProduct() {
		
		ProductService productService = new ProductService();

		Product newProduct = new Product();
		newProduct.setId(2);
		assertDoesNotThrow(() ->{
			productService.deleteProduct(newProduct.getId());
		});
	}
	
	
	
	// update//

	@Test
    public void testUpdateProduct() {
        ProductService productService = new ProductService();
        Product updateProduct = new Product();
        updateProduct.setId(2);
        updateProduct.setName("Del");
        updateProduct.setPrice(7000);
        
        assertDoesNotThrow(() -> {
            productService.updateProduct(updateProduct.getId(), updateProduct.getName(), updateProduct.getPrice(),updateProduct.getImage_url());
        });
    }
    
    @Test
    public void testUpdateProductInvalidName() {
        ProductService productService = new ProductService();
        Product Invalid = new Product();
       
        
        Invalid.setName("S399@kjdf45");
		
          Invalid.toString();
			
			Exception exception = assertThrows(ValidationException.class, () ->{
				productService. createProduct(Invalid);
	});
	String expectedMessage = "Name does not match the pattern";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
    }
	

 @Test
public void getProductDetailsByProductId() {
	
	 ProductService productService = new ProductService();
	 
	 try {
		 Product product = productService.findById(2);
		 System.out.println(product);
		 
	 }catch (Exception e) {
	        e.printStackTrace();
	    }
}
 

 
}

