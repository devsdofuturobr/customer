CREATE TABLE orders (
                        order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        order_date DATE NOT NULL,
                        cust_id INT NOT NULL,
                        FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);

CREATE TABLE products (
                          prod_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          prod_name VARCHAR(100) NOT NULL,
                          prod_price DECIMAL(10, 2) NOT NULL,
                          prod_desc TEXT
);

CREATE TABLE order_items (
                             order_id INT NOT NULL,
                             order_item INT NOT NULL,
                             prod_id INT NOT NULL,
                             quantity INT NOT NULL,
                             item_price DECIMAL(10, 2) NOT NULL,
                             item_price_total DECIMAL(10, 2) NOT NULL,
                             UNIQUE KEY (order_id, order_item, prod_id),
                             FOREIGN KEY (order_id) REFERENCES orders(order_id),
                             FOREIGN KEY (prod_id) REFERENCES products(prod_id)
);