package in.fssa.productprice.interfaces;
import java.util.Set;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.model.Product;

public interface ProductInterface {
    Set<Product> listAllProducts() throws PersistenceException;
    Set<Product> listAllProductsByCategoryId(int categoryId);
   void updateProduct(int productId, String name, int categoryId, long price);
   void delete(int id) throws PersistenceException;
Product findProductDetailsByProductId(int id) throws PersistenceException;
}
