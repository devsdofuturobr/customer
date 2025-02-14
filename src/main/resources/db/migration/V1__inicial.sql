CREATE TABLE customers (
                           cust_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           cust_name VARCHAR(100) NOT NULL,
                           cust_address VARCHAR(255),
                           cust_city VARCHAR(100),
                           cust_state VARCHAR(50),
                           cust_zip VARCHAR(20),
                           cust_country VARCHAR(50),
                           cust_contact VARCHAR(100),
                           cust_email VARCHAR(150)
);

