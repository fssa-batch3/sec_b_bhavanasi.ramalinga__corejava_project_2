package in.fssa.productprice.interfaces;
import java.util.List;
import java.util.Set;

import in.fssa.productprice.exception.PersistenceException;
import in.fssa.productprice.model.Product;

public interface ProductInterface {
    Set<Product> listAllProducts() throws PersistenceException;
    public List<Product> findAllProductsBySellerId(int id) throws PersistenceException;

   public void validateProductUpdate(int productId, String name, int categoryId, long price, int id, int userId) throws PersistenceException;
   void delete(int id) throws PersistenceException;
Product findProductDetailsByProductId(int id) throws PersistenceException;
}
