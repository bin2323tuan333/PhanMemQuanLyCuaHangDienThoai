DROP DATABASE IF EXISTS sales_management;

CREATE DATABASE sales_management;
USE sales_management;



-- 1. Bảng Role
CREATE TABLE Role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Bảng Employee
CREATE TABLE Employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(100) NOT NULL,
    gender Bool,
    birthday DATE,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    salary Double(15,2),
    status Bool default 1
);

-- 3. Bảng Account
CREATE TABLE Account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    employee_id INT UNIQUE,
    FOREIGN KEY (role_id) REFERENCES Role(role_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE SET NULL
);

-- 4. Bảng Category
CREATE TABLE Category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

-- 5. Bảng Brand
CREATE TABLE Brand (
    brand_id INT PRIMARY KEY AUTO_INCREMENT,
    brand_name VARCHAR(100) NOT NULL UNIQUE
);

-- 6. Bảng Supplier
CREATE TABLE Supplier (
    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(100)
);

-- 7. Bảng Product
CREATE TABLE Product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(200) NOT NULL,
    description TEXT,
    price Double NOT NULL,
    stock INT DEFAULT 0,
    category_id INT,
    brand_id INT,
    supplier_id INT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE SET NULL,
    FOREIGN KEY (brand_id) REFERENCES Brand(brand_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id) ON DELETE SET NULL
);

-- 8. Bảng Customer
CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    gender Bool,
    birthday DATE,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

-- 9. Bảng Bill (Đã sửa DECIMAL)
CREATE TABLE Bill (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    invoice_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount Double DEFAULT 0,
    employee_id INT,
    customer_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE SET NULL
);

-- 10. Bảng BillDetail
CREATE TABLE BillDetail (
    bill_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price Double NOT NULL,
    FOREIGN KEY (bill_id) REFERENCES Bill(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE
);

-- 11. Bảng ImportBill
CREATE TABLE ImportBill (
    import_id INT PRIMARY KEY AUTO_INCREMENT,
    import_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount Double DEFAULT 0,
    employee_id INT,
    supplier_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id) ON DELETE SET NULL
);

-- 12. Bảng ImportBillDetail
CREATE TABLE ImportBillDetail (
    import_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price Double NOT NULL,
    PRIMARY KEY (import_id, product_id),
    FOREIGN KEY (import_id) REFERENCES ImportBill(import_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE
);

