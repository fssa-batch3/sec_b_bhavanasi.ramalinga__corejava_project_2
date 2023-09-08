package in.fssa.productprice.interfaces;
import java.util.Set;


import in.fssa.productprice.model.Product;

public interface ProductInterface {
    Set<Product> listAllProducts();
    Set<Product> listAllProductsByCategoryId(int categoryId);
   void updateProduct(int productId, String name, int categoryId, long price);
   void delete(int id);
   void ProductfindProductDetailsByProductId(int id);
Product findProductDetailsByProductId(int id);
}
