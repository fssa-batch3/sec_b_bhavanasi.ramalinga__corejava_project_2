CREATE DATABASE IF NOT EXISTS products;

USE products;

CREATE TABLE IF NOT EXISTS Category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    isActive BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT exists product(
id INT auto_increment primary KEY,
name VARCHAR(35),
 is_active BOOLEAN DEFAULT TRUE,
 category_id INT,
 FOREIGN KEY (category_id) REFERENCES Category(id)
 );

CREATE TABLE IF NOT EXISTS product_price (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price INT,
    product_id INT,
     FOREIGN KEY (product_id) REFERENCES Product(id),
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
   
);


INSERT INTO Category (name)
VALUES('Iphone');
select * from Category;



SELECT * from product;
-- Insert product
INSERT INTO product (name, category_id)
VALUES ('Laptop', 1),('Gens', 2),
 ('Pants', 2),
  ('Del', 1);
SELECT * from product;

-- Update

UPDATE Product
SET name = 'Apple'
WHERE id = 4;

SELECT * from Category;
UPDATE Category
SET name = 'Cloths'
WHERE id = 3;
SELECT * from Category;

-- Delete
-- This statement should mark the product as not active, not set a non-existent 'isDeleted' column.
UPDATE Product
SET is_active = FALSE
WHERE id = 1;


INSERT INTO Category ( name)
VALUES
('Electronics'),
('Clothing'),
('Books');
SELECT * from Category;

---- category_id -------
SELECT * FROM
    Product p
WHERE
    p.category_id = 1;
    
   ---- price-------
 
SELECT * FROM product_price;
-- Insert data
INSERT INTO product_price (price, product_id)
VALUES 
    (1000, 1),
    (1200, 2),
    (800, 3);

-- Display updated data in product_price table
  SELECT * FROM product_price;
       
     