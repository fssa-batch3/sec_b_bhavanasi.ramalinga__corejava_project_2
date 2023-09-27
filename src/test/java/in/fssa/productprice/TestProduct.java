package in.fssa.productprice;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.Product;
import in.fssa.productprice.model.ProductEntity;
import in.fssa.productprice.service.ProductService;

public class TestProduct {
	
	
	@Test
	public void getAllProductsByCategoryId() {
	    ProductService productService = new ProductService();
	    List<Product> products;
	    try {
	        products = productService.findProductDetailByCategoryId(34);
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
		product.setName("Compact cameras");
		product.setCategoryId(2); 
		product.setPrice(4000000);
		product.setImageurl("https://iili.io/J9Yhl0F.md.webp");
		product.setDetails("Designed For: Medium Format Camera, DSLR/SLR Camera, Video Camera, Spotting Scope, Telescope, Mobile, Point & Shoot Camera, Advanced Point & Shoot Camera");
		
		assertDoesNotThrow(() ->{
			productService.createProduct(product);
		});
	}
	
@Test
public void testproductbysellerId() {
	ProductService product = new ProductService();
	List<Product> products;
	try {
		products = product.findAllProductsBySellerId(10) ;
		System.out.print(products);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
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
        updateProduct.setName("SAM");
        updateProduct.setPrice(8000);
        updateProduct.setImageurl("https://iili.io/J9728oG.jpg");
        updateProduct.setDetails("adukfhwswiaheioqhediwjasdbfjw");
        
        assertDoesNotThrow(() -> {
            productService.updateProduct(updateProduct.getId(), updateProduct.getName(), updateProduct.getPrice(),updateProduct.getImageurl(),updateProduct.getDetails());
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
	    Product products;
	    try {
	        products = productService.findProductDetailsByProductId(1);
	        System.out.println(products);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

 

 
}

