INSERT INTO product(id, name)
VALUES (1, 'Product 1');
INSERT INTO product(id, name)
VALUES (2, 'Product 2');
INSERT INTO product(id, name)
VALUES (3, 'Product 3');
INSERT INTO product(id, name)
VALUES (4, 'Product 4');
INSERT INTO product(id, name)
VALUES (35455, 'Product 5');

INSERT INTO brand(id, name)
VALUES (1, 'Zara');
INSERT INTO brand(id, name)
VALUES (2, 'H&M');
INSERT INTO brand(id, name)
VALUES (3, 'C&A');
INSERT INTO brand(id, name)
VALUES (4, 'Primark');
INSERT INTO brand(id, name)
VALUES (5, 'Mango');

INSERT INTO price(id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO price(id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO price(id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO price(id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');