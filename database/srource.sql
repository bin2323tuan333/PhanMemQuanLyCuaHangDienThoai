DROP
DATABASE IF EXISTS sales_management;

CREATE
DATABASE sales_management;
USE
sales_management;

CREATE TABLE role
(
    role_id   int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE supplier
(
    supplier_id int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        varchar(100) NOT NULL,
    address     varchar(255) DEFAULT NULL,
    phone       varchar(20)  DEFAULT NULL,
    email       varchar(100) DEFAULT NULL
);

CREATE TABLE category
(
    category_id   int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE brand
(
    brand_id   int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand_name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE customer
(
    customer_id  int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    full_name    varchar(100) NOT NULL,
    gender       tinyint(1)      DEFAULT NULL,
    birthday     date         DEFAULT NULL,
    address      varchar(255) DEFAULT NULL,
    phone_number varchar(20)  DEFAULT NULL
);

CREATE TABLE employee
(
    employee_id   int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_name varchar(100) NOT NULL,
    gender        tinyint(1)      DEFAULT NULL,
    birthday      date         DEFAULT NULL,
    address       varchar(255) DEFAULT NULL,
    phone_number  varchar(20)  DEFAULT NULL,
    salary double(15,2)    DEFAULT NULL,
    status        tinyint(1)      DEFAULT 1
);

CREATE TABLE account
(
    account_id  int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username    varchar(50)  NOT NULL UNIQUE,
    password    varchar(255) NOT NULL,
    role_id     int,
    employee_id int UNIQUE,
    FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE SET NULL
);

CREATE TABLE bill
(
    bill_id      int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    invoice_date datetime DEFAULT CURRENT_TIMESTAMP,
    total_amount double DEFAULT 0,
    employee_id  int,
    customer_id  int,
    status       tinyint(1)      DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE SET NULL
);

CREATE TABLE importbill
(
    import_id   int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    import_date datetime DEFAULT CURRENT_TIMESTAMP,
    total_amount double DEFAULT 0,
    employee_id int,
    supplier_id int,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id) ON DELETE SET NULL
);

CREATE TABLE product
(
    product_id   int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(200) NOT NULL,
    quantity     int DEFAULT 0,
    description  text,
    price double NOT NULL,
    stock        int DEFAULT 0,
    category_id  int,
    brand_id     int,
    status       tinyint(1)      DEFAULT 1,
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE SET NULL,
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id) ON DELETE SET NULL
);

CREATE TABLE billdetail
(
    bill_detail_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bill_id        int NOT NULL,
    product_id     int NOT NULL,
    quantity       int NOT NULL,
    unit_price double NOT NULL,
    FOREIGN KEY (bill_id) REFERENCES bill (bill_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE importbilldetail
(
    import_detail_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    import_id        int NOT NULL,
    product_id       int NOT NULL,
    quantity         int NOT NULL,
    unit_price double NOT NULL,
    FOREIGN KEY (import_id) REFERENCES importbill (import_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE system_setting
(
    id           int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop_name    varchar(255) NOT NULL,
    shop_address varchar(255) DEFAULT NULL,
    shop_phone   varchar(20)  DEFAULT NULL,
    tax_code     varchar(50)  DEFAULT NULL
);



-- ==========================================================
-- 1. BẢNG DANH MỤC GỐC (ROLE, CATEGORY, BRAND, SUPPLIER)
-- ==========================================================

-- Bảng quyền (Chỉ gồm 2 nhóm quyền)
INSERT INTO role (role_name)
VALUES ('ADMIN'),
       ('EMPLOYEE');

-- Bảng danh mục sản phẩm
INSERT INTO category (category_name)
VALUES ('Điện thoại'),
       ('Laptop'),
       ('Phụ kiện'),
       ('Máy tính bảng');

-- Bảng thương hiệu
INSERT INTO brand (brand_name)
VALUES
-- Nhóm Điện thoại & Laptop chính
('Xiaomi'),
('Samsung'),
('Apple'),
('Oppo'),
('Vivo'),
('Realme'),
('Huawei'),
('Asus'),
('Acer'),
('MSI'),
('Lenovo'),
('HP'),
('Dell');

-- Bảng nhà cung cấp
INSERT INTO supplier (name, address, phone, email)
VALUES ('Công ty TNHH Digiworld', '72 Trần Hưng Đạo, Quận 1, TP.HCM', '02873088888', 'contact@dgw.com.vn'),
       ('Nhà phân phối FPT Synnex', 'Tòa nhà FPT Massda, KCN Đà Nẵng', '02363958686', 'synnex@fpt.com'),
       ('Tổng kho Linh kiện Phúc Anh', '152 Xã Đàn, Đống Đa, Hà Nội', '02438573737', 'sale@phucanh.vn');


-- ==========================================================
-- 2. CON NGƯỜI & HỆ THỐNG (CUSTOMER, EMPLOYEE, ACCOUNT)
-- ==========================================================

INSERT INTO customer (full_name, gender, birthday, address, phone_number)
VALUES ('Nguyễn Văn Ngân', 1, '2000-05-15', '123 Hải Phòng, Hải Châu, Đà Nẵng', '0905123456'),
       ('Trần Thị Thảo', 0, '1998-11-20', '45 Lê Duẩn, Thanh Khê, Đà Nẵng', '0914987654'),
       ('Phạm Minh Hoàng', 1, '2002-02-02', '78 Điện Biên Phủ, Thanh Khê, Đà Nẵng', '0935111222'),
       ('Lê Thị Mai', 0, '1995-08-14', '12 Ngô Quyền, Sơn Trà, Đà Nẵng', '0905222333'),
       ('Nguyễn Hoàng Long', 1, '1993-12-25', '56 Nguyễn Văn Linh, Hải Châu, Đà Nẵng', '0913444555'),
       ('Vũ Thị Hồng', 0, '2001-04-30', '88 Lê Lợi, Hải Châu, Đà Nẵng', '0945666777'),
       ('Phan Văn Đức', 1, '1997-09-18', '215 Trường Chinh, Thanh Khê, Đà Nẵng', '0988888999'),
       ('Đặng Thị Lan', 0, '1999-01-05', '04 Trần Hưng Đạo, Sơn Trà, Đà Nẵng', '0905111999'),
       ('Bùi Minh Quân', 1, '2003-07-22', '452 Lê Văn Hiến, Ngũ Hành Sơn, Đà Nẵng', '0934222888'),
       ('Hoàng Thu Trang', 0, '1996-10-12', '93 Cách Mạng Tháng 8, Cẩm Lệ, Đà Nẵng', '0915333777'),
       ('Đỗ Anh Tuấn', 1, '1994-03-03', '17 Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng', '0905444666'),
       ('Ngô Bích Thủy', 0, '2000-11-11', '62 Tôn Đức Thắng, Liên Chiểu, Đà Nẵng', '0977555333'),
       ('Dương Quốc Bảo', 1, '1992-05-20', '310 Núi Thành, Hải Châu, Đà Nẵng', '0905123789'),
       ('Lý Thị Hòa', 0, '1995-02-28', '15 Âu Cơ, Liên Chiểu, Đà Nẵng', '0914789123'),
       ('Trịnh Đình Khang', 1, '1998-06-15', '77 Nguyễn Hữu Thọ, Cẩm Lệ, Đà Nẵng', '0935891234'),
       ('Võ Như Quỳnh', 0, '2004-09-09', '102 Ngũ Hành Sơn, Ngũ Hành Sơn, Đà Nẵng', '0981234567'),
       ('Phùng Tiến Đạt', 1, '1991-08-24', '19 Hoàng Diệu, Hải Châu, Đà Nẵng', '0905654321'),
       ('Đoàn Minh Tuyết', 0, '1997-12-03', '54 Phan Thanh, Thanh Khê, Đà Nẵng', '0914321654'),
       ('Tạ Văn Hùng', 1, '1993-04-17', '83 Hàm Nghi, Thanh Khê, Đà Nẵng', '0935432165'),
       ('Nguyễn Thị Diễm', 0, '2002-10-31', '28 Trần Cao Vân, Thanh Khê, Đà Nẵng', '0976111222');

-- 1. Chèn dữ liệu vào bảng employee trước (để sinh ra id 1 và 2)
INSERT INTO employee (employee_name, gender, birthday, address, phone_number, salary, status) VALUES
('Lê Hoàng Nam', 1, '1995-03-12', '12 Nguyễn Văn Linh, Hải Châu, Đà Nẵng', '0905999888', 15000000.00, 1),
('Nguyễn Mai Chi', 0, '1997-08-25', '89 Quang Trung, Thanh Khê, Đà Nẵng', '0905777666', 10000000.00,1),
('Phạm Quốc Tuấn', 1, '2006-07-08', '99 Tô Hiến Thành, Sơn Trà, Đà Nẵng', '0905111222', 12000000.00,1),
('Trần Minh Quang', 1, '1999-11-03', '45 Lê Duẩn, Thanh Khê, Đà Nẵng', '0935333444', 11500000.00,1),
('Nguyễn Hà Vi', 0, '2001-05-22', '120 Ngũ Hành Sơn, Ngũ Hành Sơn, Đà Nẵng', '0914555666', 10500000.00, 1);


-- 2. Chèn dữ liệu vào bảng account với username theo format Admin_id hoặc Employee_id
INSERT INTO account (username, password, role_id, employee_id)
VALUES ('Admin_1', 'admin123', 1, 1),
       ('Employee_2', 'password123', 2, 2),
       ('Employee_3', 'password123', 2, 3),
       ('Employee_4', 'password123', 2, 4),
       ('Employee_5', 'password123', 2, 5);


-- ==========================================================
-- 3. HÀNG HÓA (PRODUCT)
-- ==========================================================

-- Thêm nhiều dữ liệu mẫu cho bảng product
INSERT INTO product (product_name, quantity, description, price, stock, category_id, brand_id, status)
VALUES
-- Nhóm 1: Điện thoại (category_id = 1)
('Redmi Note 13 Pro', 50, 'Điện thoại Xiaomi tầm trung cấu hình mạnh', 6500000, 50, 1, 1, 1),
('Galaxy S24 Ultra', 15, 'Flagship cao cấp nhất của Samsung', 28000000, 15, 1, 2, 1),
('iPhone 15 Pro Max 256GB', 12, 'Flagship cao cấp nhất từ nhà Apple', 30990000, 12, 1, 3, 1),
('Xiaomi 14 Ultra', 8, 'Camera Leica đỉnh cao, cấu hình khủng', 24990000, 8, 1, 1, 1),
('Galaxy A55 5G', 30, 'Điện thoại tầm trung bán chạy của Samsung', 9890000, 30, 1, 2, 1),
('iPhone 13 128GB', 0, 'Dòng iPhone tiêu chuẩn ngon bổ rẻ', 13500000, 0, 1, 3, 0),

-- Nhóm 2: Laptop (category_id = 2)
('MacBook Air M3', 10, 'Laptop mỏng nhẹ hiệu năng cao từ Apple', 26500000, 10, 2, 3, 1),
('Asus ROG Strix G16', 5, 'Laptop gaming cấu hình mạnh cho game thủ', 34500000, 5, 2, 8, 1),
('Acer Aspire 5', 25, 'Laptop văn phòng, học tập giá sinh viên', 12300000, 25, 2, 9, 1),
('MSI Cyborg 15', 7, 'Laptop gaming phân khúc tầm trung giá tốt', 21990000, 7, 2, 10, 1),
('Dell Inspiron 5430', 14, 'Laptop Dell bền bỉ mượt mà cho dân văn phòng', 17500000, 14, 2, 13, 1),
('HP Pavilion 14', 18, 'Thiết kế thời trang vỏ nhôm sang trọng', 15200000, 18, 2, 12, 1),
('MacBook Pro M3 Pro', 4, 'Dòng máy chuyên nghiệp cho dân đồ họa/code', 49990000, 4, 2, 3, 1),

-- Nhóm 3: Bàn phím cơ (Chuyển đổi thương hiệu về nhóm Asus/Xiaomi/Apple để test)
('Bàn phím cơ Asus ROG Strix', 20, 'Bàn phím cơ gaming switch ROG mượt mà', 1800000, 20, 3, 8, 1),
('Bàn phím không dây Xiaomi MIIIW', 15, 'Bàn phím layout đẹp hoàn hảo cho Mac/Win', 1950000, 15, 3, 1, 1),
('Bàn phím Apple Magic Keyboard', 6, 'Bàn phím nguyên khối Apple cực sang chảnh', 4500000, 6, 3, 3, 1),
('Bàn phím cơ Lenovo Legion', 8, 'Bàn phím cơ gaming gõ êm ái phản hồi nhanh', 3890000, 8, 3, 11, 1),
('Bàn phím cơ Acer Predator', 45, 'Bàn phím cơ phân khúc giá sinh viên', 550000, 45, 3, 9, 1),
('Bàn phím cơ Dell Alienware', 0, 'Bàn phím cơ cao cấp led RGB rực rỡ', 2350000, 0, 3, 13, 0),

-- Nhóm 4: Chuột máy tính (Chuyển đổi thương hiệu về nhóm Asus/Xiaomi/Samsung/Apple)
('Chuột Gaming Asus TUF M3', 0, 'Chuột gaming siêu nhẹ cho game thủ', 3100000, 0, 4, 8, 0),
('Chuột không dây Xiaomi Silent', 60, 'Chuột văn phòng silent giá rẻ quốc dân', 450000, 60, 4, 1, 1),
('Chuột Gaming MSI Clutch', 11, 'Chuột gaming có dây dáng công thái học', 3490000, 11, 4, 10, 1),
('Chuột không dây HP Z3700', 16, 'Chuột không dây gọn nhẹ phân khúc tầm trung', 1150000, 16, 4, 12, 1),
('Chuột Văn Phòng Lenovo 130', 40, 'Chuột văn phòng giá rẻ bền bỉ', 290000, 40, 4, 11, 1),
('Chuột Apple Magic Mouse 2', 12, 'Chuột cảm ứng đỉnh cao tối ưu riêng cho Mac', 2450000, 12, 4, 3, 1);



-- ==========================================================
-- 5. CẤU HÌNH HỆ THỐNG (SYSTEM SETTING)
-- ==========================================================

INSERT INTO system_setting (shop_name, shop_address, shop_phone, tax_code)
VALUES ('Cửa hàng Công nghệ Digital Shop',
        '99 Tô Hiến Thành, Phước Mỹ, Sơn Trà, Đà Nẵng',
        '0905111222',
        '0401234567-001'
       );