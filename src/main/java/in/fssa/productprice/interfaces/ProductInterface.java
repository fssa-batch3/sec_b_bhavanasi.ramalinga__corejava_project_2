package in.fssa.productprice.interfaces;
import java.util.Set;

import in.fssa.productprice.model.Product;

import java.util.Set;

public interface ProductInterface {
    Set<Product> listAllProducts();
    Set<Product> listAllProductsByCategoryId(int category_id);
    Product findProductDetailsByProductId(int productId);
    void updateProduct(int productId, String name, int categoryId);
}
