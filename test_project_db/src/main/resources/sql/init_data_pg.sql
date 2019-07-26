CREATE SCHEMA IF NOT EXISTS test;
GRANT ALL ON SCHEMA test TO acsuser;
GRANT ALL ON SCHEMA test TO postgres;

CREATE TABLE IF NOT EXISTS test.order_head(id SERIAL PRIMARY KEY, client_id INTEGER, date TIMESTAMPTZ, address VARCHAR);
GRANT ALL ON test.order_head TO acsuser;
GRANT ALL ON test.order_head TO postgres;

CREATE TABLE IF NOT EXISTS test.order_line(id SERIAL PRIMARY KEY, order_id INTEGER, goods_id INTEGER, item_count INTEGER);
GRANT ALL ON test.order_line TO acsuser;
GRANT ALL ON test.order_line TO postgres;

CREATE TABLE IF NOT EXISTS test.client(id SERIAL PRIMARY KEY, name VARCHAR);
GRANT ALL ON test.client TO acsuser;
GRANT ALL ON test.order_head TO postgres;

CREATE TABLE IF NOT EXISTS test.goods(id SERIAL PRIMARY KEY, price NUMERIC(20,5), name VARCHAR);
GRANT ALL ON test.goods TO acsuser;
GRANT ALL ON test.goods TO postgres;


INSERT INTO test.client(id, name)
SELECT
    1, 'Ли Куан Ю'

WHERE NOT EXISTS (
    SELECT id FROM test.client WHERE id =1
);

INSERT INTO test.client(id, name)
SELECT
    2, 'Сидоров Сидор Сидорович'

WHERE NOT EXISTS (
    SELECT id FROM test.client WHERE id =2
);

INSERT INTO test.client(id, name)
SELECT
    3, 'Кириллов Кирилл Кириллович'

WHERE NOT EXISTS (
    SELECT id FROM test.client WHERE id =3
);

INSERT INTO test.goods(id, price, name)
SELECT
    1, 2.1234567, 'яСепульки жареные 5x100г'

WHERE NOT EXISTS (
    SELECT id FROM test.goods WHERE id =1
);

INSERT INTO test.goods(id, price, name)
SELECT
    2, 3.1234567, 'яКартофельная оболочка 10х1000г'

WHERE NOT EXISTS (
    SELECT id FROM test.goods WHERE id =2
);

INSERT INTO test.goods(id, price, name)
SELECT
    3, 4.1234567, 'Говяжьи пирамидки 1кг'

WHERE NOT EXISTS (
    SELECT id FROM test.goods WHERE id =3
);


INSERT INTO test.order_head (id, client_id, date, address)
SELECT
    1, 1, '01.07.2019 00:00:00', 'Республика Беларусь, г. Минск, ул. Картофельная, д. 148, кв. 42'

WHERE NOT EXISTS (
    SELECT id FROM test.order_head WHERE id = 1
);

INSERT INTO test.order_head (id, client_id, date, address)
SELECT
    3, 2, '01.07.2019 00:00:01', 'РФ, г. Москва, ул. Красная, д. 1, кв. 15'

WHERE NOT EXISTS (
        SELECT id FROM test.order_head WHERE id = 3
);

INSERT INTO test.order_head (id, client_id, date, address)
SELECT
    2, 3, '01.07.2019 00:00:02', 'Республика Казахстан, г. Кокшетау, ул. Елбасы, д. 404, кв. -1'

WHERE NOT EXISTS (
        SELECT id FROM test.order_head WHERE id = 2
);


INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    1, 1, 1, 5

WHERE NOT EXISTS (
    SELECT id FROM test.order_line WHERE id = 1
);

INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    2, 1, 2, 6

WHERE NOT EXISTS (
        SELECT id FROM test.order_line WHERE id = 2
);

INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    3, 1, 3, 10

WHERE NOT EXISTS (
        SELECT id FROM test.order_line WHERE id = 3
);

INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    4, 2, 1, 5

WHERE NOT EXISTS (
        SELECT id FROM test.order_line WHERE id =4
);

INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    5, 2, 2, 6

WHERE NOT EXISTS (
        SELECT id FROM test.order_line WHERE id =5
);

INSERT INTO test.order_line (id, order_id, goods_id, item_count)
SELECT
    6, 3, 1, 15

WHERE NOT EXISTS (
        SELECT id FROM test.order_line WHERE id =6
);

ALTER TABLE test.order_head DROP CONSTRAINT IF EXISTS oreder_head_client_id_fk;

ALTER TABLE test.order_head
    ADD CONSTRAINT oreder_head_client_id_fk
        FOREIGN KEY (client_id)
            REFERENCES test.client(id)
            ON DELETE CASCADE;

ALTER TABLE test.order_line DROP CONSTRAINT IF EXISTS order_line_order_id_fk;

ALTER TABLE test.order_line
    ADD CONSTRAINT order_line_order_id_fk
        FOREIGN KEY (order_id)
            REFERENCES test.order_head(id)
            ON DELETE CASCADE;

ALTER TABLE test.order_line DROP CONSTRAINT IF EXISTS order_line_goods_id_fk;

ALTER TABLE test.order_line
    ADD CONSTRAINT order_line_goods_id_fk
        FOREIGN KEY (goods_id)
            REFERENCES test.goods(id)
            ON DELETE CASCADE;