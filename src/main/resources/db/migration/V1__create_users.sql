CREATE DATABASE IF NOT EXISTS productprice;
USE productprice;

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    image_url VARCHAR(255),
    isActive BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(35),
    isActive BOOLEAN DEFAULT TRUE,
     image_url VARCHAR(255),
    categoryId INT,
    price Double,
    Details varchar(500),
    userId INT,
    FOREIGN KEY (categoryId) REFERENCES categories(id),
     FOREIGN KEY (userId) REFERENCES users(id)
);

drop table products;
select * from products;
-- Add the Details column to the products table
ALTER TABLE products
ADD Details VARCHAR(500);

-- Update the Details for the row with id = 5
UPDATE products
SET Details = 'Wall Charger
Suitable For: Mobile
Universal Voltage
Output Current : 2.1 A
Services
6 Months Manufacturer Warranty
Cash on Delivery available?
Seller
BUZZINDIA5
7 Days Replacement Policy?
GST invoice available'
WHERE id = 5;


-- Step 3: Verify the changes by selecting all rows from the 'products' table
SELECT * FROM products;

CREATE TABLE IF NOT EXISTS users(
id INT auto_increment PRIMARY KEY not null,
 name VARCHAR(255) NOT NULL,
  phoneNumber BIGINT,
 email VARCHAR(255) NOT NULL,
 password VARCHAR(255) NOT NULL,
  isActive BOOLEAN DEFAULT TRUE,
  role varchar(50),
  Address varchar(300)

);

CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cancel_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    quantity INT NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    payment VARCHAR(100) DEFAULT 'Cash on Delivery',
    status ENUM('Waiting list', 'Delivered', 'On the way', 'Canceled') NOT NULL DEFAULT 'Waiting list',
    userId INT,
    price DOUBLE,
    Address VARCHAR(1000),
    pincode INT,
    name VARCHAR(100),
    pdt_id INT,
    seller_id INT,
    FOREIGN KEY (seller_id) REFERENCES products(userId),
    FOREIGN KEY (pdt_id) REFERENCES products(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

select * from orders;

INSERT INTO orders(name,Address,seller_id,pincode,quantity,phone_number,userId,pdt_id,price)
VALUES ('Soundarya','Chennai','10','600032',2,'8466009892','5','1',5000);
drop table orders;
select * from users;

INSERT INTO users (email, name, phoneNumber, password, isActive,role,Address )
VALUES ('Soundarya@example.com', 'Soundarya Reddy', 1234567890, '9876754653', TRUE, 'buyer','Gandhi St, Abith Colony, Industrial Area, Saidapet, Chennai, Tamil Nadu 600015'),
('Saranya@example.com', 'Saranya', 1234567890, '9865543424', TRUE, 'seller','Gandhi St, Abith Colony, Industrial Area, Saidapet, Chennai, Tamil Nadu 600015');


SELECT * from products;
select * from categories;

drop table categories;
drop table products;


INSERT INTO categories (name, image_url)
VALUES ('laptops & chargers', 'https://iili.io/J9pamVR.jpg'),
('Mobiles ,chargers & mobile covers','https://iili.io/J9paKGV.jpg'),
('Electric Wires','https://iili.io/J9F4ayX.jpg'),
('Camera','https://iili.io/J9FkwXV.jpg'),
('TV', 'https://iili.io/JHSpia2.png'),
('Speakers','https://iili.io/JHqSgCN.jpg'),
('Washing Machine','https://iili.io/JHqUkl9.jpg'),
('Tube Lights and Bulbs','https://iili.io/JHqSlkb.jpg'),
('Computer parts','https://iili.io/JHSmYOX.jpg'),
('Earphones','https://iili.io/J9pcRDb.jpg'),
('Fridge','https://iili.io/JHSm8qx.jpg');



INSERT INTO categories (name, image_url)
VALUES ('Irons Boxes', 'https://iili.io/JHsVBcv.png');



drop table products;
-- update
UPDATE users
SET name = 'Soundarya', phoneNumber = 9876543210
WHERE id = 1;
-- delete
DELETE FROM users
WHERE id = 1;






drop table categories;
drop table products;
drop table productprice;

SELECT * from products;
select * from categories;
-- Insert product

select * from users;

INSERT INTO products (name, categoryId, price, image_url, Details, userId)
VALUES ('DelLaptop', 1,30000,'https://iili.io/J9qyf6B.jpg', 
'4 GB RAM | 64 GB ROM | Expandable Upto 1 TB
16.56 cm (6.52 inch) HD+ Display
8MP Dual Rear Camera | 5MP Front Camera
5000 mAh Battery
Helio G36 Processor' , 10),
(' MacBook Pro Laptop', 1, 40000,'https://iili.io/J9qy1us.jpg',   '15.6 Inch Full HD OLED 16:9 aspect ratio, 60Hz refresh rate, 400nits, 600nits HDR peak brightness, 100% DCI-P3 color gamut, 1,000,000:1, VESA CERTIFIED Display HDR True Black 600, 1.07 billion colors, PANTONE Validated, Glossy display, 70% less harmful blue light
Light Laptop without Optical Disk Drive
Preloaded with MS Office', 8),
('Lenovo',1,50000,'https://iili.io/J9qyNS9.md.jpg','14 Inch HD, touch, micro-edge, BrightView, (Brightness: 220 nits, 112 ppi, Color Gamut: 45% NTSC)
Light Laptop without Optical Disk Drive  EMI starting from ₹1,794/month
Cash on Delivery
Net banking & Credit/ Debit/ ATM card', 7);

INSERT INTO products (name, categoryId, price, image_url,details, userId)
 VALUES(' one plus',4, 10000,'https://iili.io/J9YJyMv.md.webp','6 GB RAM | 128 GB ROM
16.74 cm (6.59 inch) Display
64MP Rear Camera | 16MP Front Camera
5000 mAh Battery    
EMI starting from ₹603/month
Cash on Delivery
Net banking & Credit/ Debit/ ATM card', 7);
 
 
 
 INSERT INTO products (name, price, image_url, details, categoryId)
VALUES ('New Product 1', 19.99, 'Description of the new product 1', 'Category 1');



-- Add more INSERT statements for each new product you want to add

SELECT * from products;
select * from categories;
-- Update

UPDATE categories
SET image_url = 'https://iili.io/JHsnBPs.png'
WHERE name = 'Electric Wires';






SELECT * FROM categories;
UPDATE categories
SET isActive = true
WHERE id = 1;
SELECT * from categories;

-- Delete
UPDATE products
SET isActive = false
WHERE id = 1;

select * from products;

-- pudate 
UPDATE categories
SET name = 'LapCharger'

WHERE id = 4;




ALTER TABLE categories
ADD COLUMN image_url VARCHAR(255); 
SELECT * from categories;

UPDATE categories
SET image_url = 'https://iili.io/J9FeWoN.jpg'
WHERE name = 'Laptops';

UPDATE categories
SET image_url = 'https://iili.io/J9F4ayX.jpg'
WHERE name = 'Electric Wires';

UPDATE categories
SET image_url = 'https://iili.io/J9FkwXV.jpg'
WHERE name = 'Camera';

UPDATE categories
SET image_url = 'https://iili.io/J9FSBzx.jpg'
WHERE name = 'Earphones';

UPDATE categories
SET image_url = 'https://iili.io/J9FU537.jpg'
WHERE name = 'Mobiles';

UPDATE categories
SET image_url = 'https://iili.io/J9F4A4p.jpg'
WHERE name = 'Chargers';



INSERT INTO categories ( name)
VALUES
('Chargers'),
('Electric Wires');

SELECT * from categories;

---- category_id -------
SELECT * FROM
    Product p
WHERE
    p.categoryId = 1;
    
   ---- price-------
 
SELECT * FROM productprice;
-- Insert data
INSERT INTO productprice (price, productId)
VALUES 
    (1000, 1),
    (1200, 2),
    (800, 3);

-- Display updated data in product_price table
  SELECT * FROM productprice;
  
  select * from users;
       
     